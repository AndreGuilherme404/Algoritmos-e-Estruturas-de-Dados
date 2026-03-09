package Main;

public class MatrizEsparsa {
	Celula head;
	int linhas, colunas;
	public MatrizEsparsa(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		Celula temp1;
		head = new Celula(-1, -1);
		head.linha = -1;
		head.coluna = -1;
		temp1 = head;
		Celula temp2;
		for(int i = 0; i < colunas; i++) {
			temp2 = new Celula(-1, i);
			temp1.direita = temp2;
			temp1 = temp2;
		}
		temp1 = head;
		for(int i = 0; i < linhas; i++) {
			temp2 = new Celula(i, -1);
			temp1.abaixo = temp2;
			temp1 = temp2;
		}
	}
	public void set(int linha, int coluna, int valor) {
		if(linha < linhas && coluna < colunas) {
		Celula atual = head;
		Celula anterior;
		Celula temp = new Celula(linha,coluna,valor);
		Celula auxiliar;
		for(int i = 0; i <= linha; i++) {
//			System.out.print("Movendo de ");
//			atual.showCoords();
			
			atual = atual.abaixo;
			
//			System.out.print(" para ");
//			atual.showCoords();
//			System.out.println();
		
		}
		for(int i = 0; i <= coluna; i++) {
			if(atual.direita == null) {
				
//				System.out.print("Celula a direita de ");
//				atual.showCoords();
//				System.out.println(" é nulo, inserindo nova celula");
				
				atual.direita = temp;
				break;
			}else {
				if(atual.direita.coluna == coluna) {
					auxiliar = atual.direita.direita;
					atual.direita = temp;
					temp.direita = auxiliar;
					break;
				}
//				System.out.print("Celula a direita de ");
//				atual.showCoords();
//				System.out.println(" não é nulo");
				
				anterior = atual;
				atual = atual.direita;
				
//				System.out.print("Movendo atual para ");
//				atual.showCoords();
//				System.out.print(" ,anterior recebe ");
//				anterior.showCoords();
//				System.out.println();
				
				if(atual.coluna > coluna) {
//					System.out.println(atual.coluna + " e maior que  " + coluna);
					
					anterior.direita = temp;
					anterior.direita.direita = atual;
					break;
				}
			}
		}
		atual = head;
		anterior = null;
		for(int i = 0; i <= coluna; i++) {
//			System.out.print("Movendo de ");
//			atual.showCoords();
			
			atual = atual.direita;
			
//			System.out.print(" para ");
//			atual.showCoords();
//			System.out.println();
		}
		for(int i = 0; i <= linha; i++) {
			if(atual.abaixo == null) {
//				System.out.print("Celula abaixo de ");
//				atual.showCoords();
//				System.out.println(" é nulo, inserindo nova celula");
				
				atual.abaixo = temp;
			}else {
				if(atual.abaixo.linha == linha) {
					auxiliar = atual.abaixo.abaixo;
					atual.abaixo = temp;
					temp.abaixo = auxiliar;
					break;
				}
//				System.out.print("Celula abaixo de ");
//				atual.showCoords();
//				System.out.println(" não é nulo");
				
				anterior = atual;
				atual = atual.abaixo;
				
//				System.out.print("Movendo atual para ");
//				atual.showCoords();
//				System.out.print(" ,anterior recebe");
//				anterior.showCoords();
				
				if(atual.linha > linha) {
//					System.out.println(atual.linha + "e maior que" + linha);
					
					anterior.abaixo = temp;
					anterior.abaixo.abaixo = atual;
					break;
				}
			}
		}
		}else {
			throw new NullPointerException("Valor fora dos limites da Matriz");
		}
//		System.out.println("Celula inserida");
	}
	public Celula get(int linha, int coluna) {
		Celula pointer = null;
		for(int i = -1; i < linha; i++) {
			if(i == -1) {
				pointer = head.abaixo;
			}else {
				pointer = pointer.abaixo;
			}
		}
		for(int i = -1; i < coluna; i++) {
			if(pointer == null) {
				return null;
			}
			if(pointer.coluna > i) {
				continue;
			}
			pointer = pointer.direita;
		}
		if(pointer == null || pointer.linha != linha || pointer.coluna != coluna) {
			return null;
		}
		return pointer;
	}
	public void remove(Celula celula) {
		    Celula anterior;
		    //Removendo da coluna
		    anterior = head;
		    for (int i = -1; i < celula.linha; i++) {
		        anterior = anterior.abaixo;
		    }
		    while (anterior.direita != null && anterior.direita.coluna < celula.coluna) {
		        anterior = anterior.direita;
		    }
		    if (anterior.direita != null && anterior.direita.coluna == celula.coluna) {
		        anterior.direita = anterior.direita.direita;
		    }
		    //Removendo da linha
		    anterior = head;
		    for (int i = -1; i < celula.coluna; i++) {
		        anterior = anterior.direita;
		    }
		    while (anterior.abaixo != null && anterior.abaixo.linha < celula.linha) {
		        anterior = anterior.abaixo;
		    }
		    if (anterior.abaixo != null && anterior.abaixo.linha == celula.linha) {
		        anterior.abaixo = anterior.abaixo.abaixo;
		    }
		}
	
//	public void remove(Celula celula) {
//		Celula pointer = null;
//		for(int i = -1; i < celula.linha; i++) {
//			if(i == -1) {
//				pointer = head.abaixo;
//			}else {
//				pointer = pointer.abaixo;
//			}
//		}
//		for(int i = 0; i < celula.coluna - 1; i++) {
//			pointer = pointer.direita;
//		}
//		pointer.direita = pointer.direita.direita;
//		pointer = head;
//		for(int i = -1; i < celula.coluna; i++) {
//				pointer = pointer.direita;
//			
//		}
//		for(int i = -1; i < celula.linha - 1; i++) {
//			pointer = pointer.abaixo;
//		}
//		if(pointer.abaixo == null) {
//			pointer = null;
//		}else {
//		pointer.abaixo = pointer.abaixo.abaixo;
//		}
//		}
	@Override
	public String toString() {
		Celula pointerC = null;
		Celula pointerL = null;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < linhas; i++) {
			if(i == 0) {
				pointerL = head.abaixo;
			}else {
				pointerL = pointerL.abaixo;
			}
			for(int j = 0; j <= colunas; j++) {
				if(j == 0) {
					pointerC = pointerL.direita;
				}else {
					if(pointerC == null) {
						sb.append("0\t");
						continue;
					}else {
						if(pointerC.coluna > j - 1) {
							sb.append("0\t");
							continue;
						}
						sb.append(pointerC + "\t");
						pointerC = pointerC.direita;
						
					}
					
				}
				
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
	
	

