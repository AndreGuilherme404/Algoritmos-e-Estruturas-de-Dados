package main;

import java.util.Random;

//Os prints comentaram foram de debug, descomente eles para ver o que aconteceu exatamente cada minuto,
//como um box abrindo, um cliente normal ou vip chegando, um atendente indo almoçar etc
public class Controle {
	Random random = new Random();
	// Temp simulacao é horas por dia que o call center fica aberto
	int TEMPSIMULACAO, TEMPESPERAMAX, TEMPATENDIMENTOMIN, TEMPATENDIMENTOMAX;
	int QTDBOXMIN, QTDBOXMAX, TEMPTRABALHANDOMAX, TEMPALMOCO, QTDATD,

			QTDATDMIN, QTDATDMAX, VIPSSEQUENCIAMAX;

	// Variaveis extras de controle;
	// Chance de um cliente aparecer em um minuto (100 é 100% de um cliente entrar
	// na fila cada minuto)
	int chanceCliente;

	// Ultima box atendida (distribuir igualmente nos boxes os clientes
	int ultimaBox;

	// Quantidade de boxes ativas no momento
	int qtdBoxAtiva;

	// Chance de abrir uma box em dado minuto
	double chanceBox;
	// Chance de fechar uma box em dado minuto
	double chanceFecharBox;

	// Dados dos vips
	int vipsAtendidos;
	int vipsAtendidosSequencia;
	int chanceVIP;

	// DADOS PARA O RELATORIO
	// Clientes que sairam por demora na fila
	int clientesInsatisfeitos;
	int clientesAtendidos;
	int boxesFechados;
	int boxesAbertos;

	FilaDinamica<Cliente> filaClientes;
	FilaDinamica<Cliente> filaClientesVIP;
	FilaDinamica<Atendente> filaEsperaAtendente;
	FilaDinamica<Atendente> filaAlmocoAtendente;
	int minutos = 1;
	int tempoFechamento = 0; // Quando o call center fechar e os ultimos clientes estiverem sendo atendidos,
								// isso vai garantir que a simulação não acabe com um cliente sendo atendido
	Box[] boxes;

	public Controle() {
		filaClientes = new FilaDinamica<Cliente>();
		filaClientesVIP = new FilaDinamica<Cliente>();
		filaEsperaAtendente = new FilaDinamica<Atendente>();
		filaAlmocoAtendente = new FilaDinamica<Atendente>();

	}

	public void inicializar() {
		boxes = new Box[QTDBOXMAX];
		qtdBoxAtiva = QTDBOXMIN;
		for (int i = 0; i < QTDBOXMIN; i++) {
			boxes[i] = new Box();
			boxes[i].ativa = true;
			boxes[i].atendente = new Atendente();
			boxes[i].atendente.tempoInicioAlmoco = random.nextInt(1, TEMPSIMULACAO * 60 - TEMPALMOCO);
		}

		for (int i = 0; i < QTDATD - QTDBOXMIN; i++) {
			Atendente a1 = new Atendente();
			a1.tempoInicioAlmoco = random.nextInt(1, TEMPSIMULACAO * 60 - TEMPALMOCO);
			filaEsperaAtendente.enfileira(a1);
		}
	}

	public void iniciarSimulacao() {

		while (minutos < TEMPSIMULACAO * 60) {
			//
//			System.out.print(minutos);
			// Se existe clientes na fila e o na primeira posição ter passado mais tempo do
			// que o TEMPESPERAMAX, remover ele da fila
			saidaPorDemora();
			// Entrada de clientes
			entradaClientes();

			// Verificar se algum atendente tem que almocar
			evitarTrabalhoNoHorarioDeAlmoco();
			// Verificar se algum atendente ja trabalho mais que o max
			evitarTrabalhoIntenso();
			// Checar se algum atendente terminou o almoco
			verificarFilaAlmoco();
			// Ver se tem algum box livre para um atendente na fila entrar nele
			for (int i = 0; i < qtdBoxAtiva; i++) {
				if (boxes[i] == null) {
					break;
				}
				if (boxes[i].ativa) {
					if (boxes[i].atendente == null) {
						if (filaEsperaAtendente.peek() != null) {
							boxes[i].atendente = filaEsperaAtendente.desenfileira();
							boxes[i].atendente.tempoInicio = minutos;
//							System.out.print("\tUm atendente saiu da lista de espera e entrou em um box\t");
						}
					}
				}

			}

			// Iniciar atendimento
			iniciarAtendimento();
			// Abrir uma box extra baseado na chance
			double chance = random.nextDouble(0, 1);
			if (chance < chanceBox) {
				abrirBox();
			} // Se as filas estiverem vazias o "gerente" pode querer fechar as boxes
			if (filaClientes.peek() == null && filaClientesVIP.peek() == null) {
				chance = random.nextDouble(0, 1);
				if (chance < chanceFecharBox) {
					fecharBox();
				}
			}
//			System.out.println();
			minutos++;
		}
		while (filaClientes.peek() != null || filaClientesVIP.peek() != null) {
//			System.out.print(minutos);
			evitarTrabalhoNoHorarioDeAlmoco();
			evitarTrabalhoIntenso();
			verificarFilaAlmoco();
			for (int i = 0; i < qtdBoxAtiva; i++) {
				if (boxes[i] == null) {
					break;
				}
				if (boxes[i].ativa) {
					if (boxes[i].atendente == null) {
						if (filaEsperaAtendente.peek() != null) {
							boxes[i].atendente = filaEsperaAtendente.desenfileira();
							boxes[i].atendente.tempoInicio = minutos;
//							System.out.print("\tUm atendente saiu da lista de espera e entrou em um box\t");
						}
					}
				}

			}
			iniciarAtendimento(); // Atender todos os clientes até que a fila esteja vazia
			minutos++;
//			System.out.println();
		}
		while (minutos < tempoFechamento) {
//			System.out.print(minutos + "\t" + tempoFechamento);
			minutos++;
//			System.out.println();
		}
	}

	public void iniciarAtendimento() {
		for (int i = 0; i < qtdBoxAtiva; i++) {
			int lookupPos = (i + ultimaBox) % qtdBoxAtiva;
			if (boxes[lookupPos] == null) {
				break;
			}

			if (boxes[lookupPos].ativa) {
				if (boxes[lookupPos].atendente == null) {
					continue;
				}
				// Nenhum cliente está sendo atendido
				if (boxes[lookupPos].finalAtendimento < minutos && boxes[lookupPos].atendente != null) {
					if ((vipsAtendidosSequencia < VIPSSEQUENCIAMAX || filaClientes.peek() == null) && filaClientesVIP.peek() != null) {
						ultimaBox = lookupPos;
						boxes[lookupPos].clientesAtendidos++;
						boxes[lookupPos].atendente.clientesAtendidos++;
						Cliente c = filaClientesVIP.desenfileira();
						boxes[lookupPos].finalAtendimento = random.nextInt(TEMPATENDIMENTOMIN, TEMPATENDIMENTOMAX) + minutos;
						boxes[lookupPos].atendente.tempoTrabalhado += boxes[lookupPos].finalAtendimento - minutos;
						boxes[lookupPos].tempoAtendendo += boxes[lookupPos].finalAtendimento - minutos;
						if (tempoFechamento < boxes[lookupPos].finalAtendimento) {
							// Garante que o tempo de fechamento vai ser exatamente no tempo que o ultimo
							// cliente for terminado de ser atendido
							tempoFechamento = boxes[lookupPos].finalAtendimento;

						}
//						System.out.print("\tUm cliente VIP está sendo atendido, ira demorar " + (boxes[lookupPos].finalAtendimento - minutos) + " minutos\t");
						vipsAtendidosSequencia++;
						vipsAtendidos++;
						continue;
					}
					if (filaClientes.peek() != null) {
						// Iniciar atendimento
						vipsAtendidosSequencia = 0;
						ultimaBox = lookupPos;
						boxes[lookupPos].clientesAtendidos++;
						boxes[lookupPos].atendente.clientesAtendidos++;
						Cliente c = filaClientes.desenfileira();
						boxes[lookupPos].finalAtendimento = random.nextInt(TEMPATENDIMENTOMIN, TEMPATENDIMENTOMAX) + minutos;
						boxes[lookupPos].atendente.tempoTrabalhado += boxes[lookupPos].finalAtendimento - minutos;
						boxes[lookupPos].tempoAtendendo += boxes[lookupPos].finalAtendimento - minutos;
						if (tempoFechamento < boxes[lookupPos].finalAtendimento) {
							// Garante que o tempo de fechamento vai ser exatamente no tempo que o ultimo
							// cliente for terminado de ser atendido
							tempoFechamento = boxes[lookupPos].finalAtendimento;

						}
						clientesAtendidos++;
//						System.out.print("\tUm cliente está sendo atendido, ira demorar " + (boxes[lookupPos].finalAtendimento - minutos) + " minutos\t");
					}
				}
			}
		}
	}

	public void abrirBox() {
		if (qtdBoxAtiva != boxes.length - 1) {
			boxes[qtdBoxAtiva] = new Box();
			boxes[qtdBoxAtiva].ativa = true;
			qtdBoxAtiva++;
			boxesAbertos++;
//			System.out.print("\tNova box aberta\t");
		}
	}

	public void fecharBox() {
		if (qtdBoxAtiva > QTDBOXMIN) {
			for (int i = 0; i < boxes.length - 1; i++) {
				if (boxes[i + 1] == null) {
					if (boxes[i].atendente != null) {
						filaEsperaAtendente.enfileira(boxes[i].atendente);
					}
					boxes[i] = null;
					qtdBoxAtiva--;
					boxesFechados++;
//					System.out.println("Box fechada");
					break;

				}
			}

		}
	}

	public void verificarFilaAlmoco() {
		if (filaAlmocoAtendente.peek() != null && minutos - filaAlmocoAtendente.peek().tempoInicioAlmoco >= TEMPALMOCO) {
//			System.out.print("\tAtendente terminou o almoço\t");
			Atendente temp1 = filaAlmocoAtendente.desenfileira();
			temp1.tempoInicioAlmoco = Integer.MAX_VALUE;
			filaEsperaAtendente.enfileira(temp1);
		}
	}

	public void evitarTrabalhoIntenso() {
		for (int i = 0; i < qtdBoxAtiva; i++) {
			if (boxes[i] == null) {
				break;
			}
			if (boxes[i].ativa) {
				if (boxes[i].atendente != null) {
					if (boxes[i].atendente.tempoInicio + TEMPTRABALHANDOMAX < minutos && boxes[i].finalAtendimento < minutos) {

//						System.out.print("\tUm atendente trabalhou demais\t");

						filaEsperaAtendente.enfileira(boxes[i].atendente);
						boxes[i].atendente = null; // Adicionar outro atendente em outro loop, pode ser que o
													// atendente
													// no topo da fila de espera ainda não tenha descansado o minimo
					}
				}
			}
		}
	}

	public void evitarTrabalhoNoHorarioDeAlmoco() {
		for (int i = 0; i < qtdBoxAtiva; i++) {
			if (boxes[i] == null) {
				break;
			}
			if (boxes[i].atendente != null) {
				if (boxes[i].finalAtendimento >= minutos && boxes[i].atendente.tempoInicioAlmoco <= minutos) {
					filaAlmocoAtendente.enfileira(boxes[i].atendente);
					boxes[i].atendente = null;
//					System.out.print("Atendente foi almocar\t");
				}
			}
		}
	}

	public void entradaClientes() {
		// Entrada de cliente
		if (random.nextInt(1, 100) < chanceCliente) {
			Cliente c = new Cliente();
			c.tempoEntradaFila = minutos;
			filaClientes.enfileira(c);
//						System.out.print("\tNovo cliente\t");
		}
		// Entrada de cliente vip
		if (random.nextInt(1, 100) < chanceVIP) {
			Cliente c = new Cliente();
			c.tempoEntradaFila = minutos;
			filaClientesVIP.enfileira(c);
//						System.out.print("\tNovo cliente VIP\t");
		}
	}

	public void saidaPorDemora() {
		if (filaClientes.peek() != null && (minutos - filaClientes.peek().tempoEntradaFila) > TEMPESPERAMAX) {
			clientesInsatisfeitos++;
			filaClientes.desenfileira();
//			System.out.print("\tCliente saiu da fila por demora\t");
		}
		if (filaClientesVIP.peek() != null && (minutos - filaClientesVIP.peek().tempoEntradaFila) > TEMPESPERAMAX) {
			clientesInsatisfeitos++;
			filaClientesVIP.desenfileira();
//			System.out.print("\tCliente VIP saiu da fila por demora\t");
		}
	}

	public void mostrarRelatorio() {
		FilaDinamica<Atendente> temp = new FilaDinamica<Atendente>();
		System.out.println("N°Box\tClientes Atendidos\tTempo médio por Cliente");
		for (int i = 0; i < boxes.length; i++) {
			if (boxes[i] == null) {
				break;
			}
			try {
				System.out.println(i + "\t" + boxes[i].clientesAtendidos + "\t\t\t" + boxes[i].tempoAtendendo / boxes[i].clientesAtendidos);
			} catch (Exception e) {
				System.out.println(i + "\tNenhum cliente foi atendido");
			}
		}
		System.out.println("Dados dos atendentes:");
		for (int i = 0; i < qtdBoxAtiva; i++) {
			if (boxes[i] == null) {
				break;
			}
			if (boxes[i].atendente != null) {
				System.out.println(boxes[i].atendente);
			}
			while (filaEsperaAtendente.peek() != null) {
				System.out.println(filaEsperaAtendente.desenfileira());
			}
			while (filaAlmocoAtendente.peek() != null) {
				System.out.println(filaAlmocoAtendente.desenfileira());
			}

		}
		System.out.println("Clientes que sairam da fila:" + clientesInsatisfeitos);
		System.out.println("--------------------------Informaçoes extras--------------------------");
		System.out.println("\t\t\tBoxes que abriram: " + (boxesAbertos));
		System.out.println("\t\t\tBoxes que fecharam: " + boxesFechados);
		System.out.println("\t\t\tVips atendidos: " + vipsAtendidos);
		System.out.println("\t\t\tClientes normais atendidos: " + clientesAtendidos);

	}
}
