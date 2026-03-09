package Main;
//Celula não é uma classe privada dentro da matriz esparsa porque precisei criar uma referência de celula no inverter, para usar o get
public class Celula {
		Celula direita, abaixo;
		int linha, coluna, valor;
		public Celula(int linha, int coluna, int valor) {
			this.linha = linha;
			this.coluna = coluna;
			this.valor = valor;
		}
		public Celula(int linha, int coluna) {
			this.linha = linha;
			this.coluna = coluna;
		}
		@Override
		public String toString() {
			return Integer.toString(valor);
		}
		public void showCoords() {
			System.out.print(linha + " " + coluna);
		}
	}