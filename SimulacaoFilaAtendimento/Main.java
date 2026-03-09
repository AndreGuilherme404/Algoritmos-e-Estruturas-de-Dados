package main;

public class Main {
	
	public static void main(String[] args) {
		//Obs: Atendentes so tem um horário de almoco, então se o tempo de simulaçao for 100 horas, eles vão almoçar apenas 1 vez
		Controle controle = new Controle();
		controle.chanceCliente = 30; //30 = 30% de um cliente em um minuto
		controle.TEMPSIMULACAO = 12; //Em horas
		controle.TEMPESPERAMAX = 30; //Em minutos
		controle.QTDATD = 25; //Quantidade de atendentes, não é mudado durante a simulação
		controle.QTDBOXMIN = 5; //Boxes abertas inicialmente
		controle.QTDBOXMAX = 25; //Maximo de boxes abertas ate o final da simulação (pode ser que nem chegue nesse valor)
		controle.TEMPATENDIMENTOMIN = 5; //Em minutos
		controle.TEMPATENDIMENTOMAX = 30; //Em minutos
		controle.TEMPALMOCO = 60; //Em minutos
		controle.TEMPTRABALHANDOMAX = 180; //Em minutos
		controle.chanceBox = 0.02; //0.05 = 5%
		controle.chanceFecharBox = 0.005; //Chance de fechar uma box em um minuto
		controle.VIPSSEQUENCIAMAX = 2; //Maximo de vips atendidos em sequencia antes de atender um normal, ignorado se a fila de clientes normais estiver vazia
		controle.chanceVIP = 3; //1 = 1% de um vip por minuto
		
		controle.inicializar(); //Inicializa o call center, abrindo os boxes minimos, colocando os atendentes que sobraram na lista de espera etc
		controle.iniciarSimulacao(); //Roda a simulação pelo tempo expecificado
		controle.mostrarRelatorio(); //Mostra o relatorio da simulação no console
		
	}
}
