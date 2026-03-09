package main;

public class Atendente {

	int tempoInicioAlmoco;
	int clientesAtendidos;
	int tempoTrabalhado;
	int tempoInicio;
	int tempoInicioDescanso;

	@Override
	public String toString() {
		try {
			return "Tempo Trabalhado:" + tempoTrabalhado + "\tClientes:"  + clientesAtendidos + "\tTempo médio por cliente: " + tempoTrabalhado/clientesAtendidos;
		}catch(Exception e) {
			return "Tempo Trabalhado:" + tempoTrabalhado + "\tClientes:"  + clientesAtendidos + "\tTempo médio por cliente: 0";
		}
	}
}
