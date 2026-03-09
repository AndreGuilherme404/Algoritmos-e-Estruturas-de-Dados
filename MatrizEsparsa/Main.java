package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	// Coloque o path do arquivo pgm aqui
	static File arquivoBGM = new File("pgm/Trabalho1.pgm");
	//static File arquivoBGM = new File("pgm/Trabalho1_bandeira.pgm");
	//static File arquivoBGM = new File("pgm/Trabalho1_HomerSimpson.pgm");
	static int valorMax = 0;
	static int linhas = 0;
	static int colunas = 0;
	static MatrizEsparsa pgm;

	public static void main(String[] args) throws IOException {
		ler();
		inserirBorda();
		inverter();		
		rotacionar();
		escrever();



	}

	public static void ler() throws IOException {
		Scanner leitor = new Scanner(arquivoBGM);
		leitor.nextLine();
		colunas = leitor.nextInt();
		linhas = leitor.nextInt();
		valorMax = leitor.nextInt();
		int temp;
		pgm = new MatrizEsparsa(linhas, colunas);
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				temp = leitor.nextInt();
				if (temp != 0) {
					pgm.set(i, j, temp);
				}
			}
		}
		leitor.close();

	}

	public static void inserirBorda() {
		// Setando borda de cima
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < colunas; j++) {
				pgm.set(i, j, 255);
			}
		}

		// Setando borda de baixo
		for (int i = linhas - 3; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				pgm.set(i, j, 255);
			}
		}

		// Setando borda da esquerda
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < 3; j++) {
				pgm.set(i, j, 255);
			}
		}

		// Setando borda da direita
		for (int i = 0; i < linhas; i++) {
			for (int j = colunas - 3; j < colunas; j++) {
				pgm.set(i, j, 255);
			}
		}
	}

	public static void inverter() {
		Celula temp;
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				temp = pgm.get(i, j);
				if (temp == null) {
					pgm.set(i, j, 255);
				} else {
					if(temp.valor == 255) {
						pgm.remove(temp);
					}else {
						pgm.set(i, j, 255 - temp.valor);
					}
				}
			}

		}
	}
	public static void rotacionar() {
		MatrizEsparsa bgmCopia = new MatrizEsparsa(colunas, linhas);
		Celula temp = null;
		for(int i = 0; i < linhas; i++) {
			for(int j = 0; j < colunas; j++) {
				temp = pgm.get(i, j);
				if(temp != null) {
					bgmCopia.set(temp.coluna, (linhas - 1) - temp.linha,temp.valor);
				}
			}
		}
		int a = colunas;
		colunas = linhas;
		linhas = a;
		pgm = bgmCopia;
	}
	public static void escrever() throws IOException {
//		Caminho da saida vem aqui
		FileWriter arquivo = new FileWriter("saida.pgm");
		arquivo.write("P2\n");
		arquivo.write(colunas + " " + linhas + "\n");
		arquivo.write(valorMax + "\n");
		//Por algum motivo não estava dando certo
//		arquivo.write(bgm.toString());
		Celula temp;
		for(int i =  0; i < linhas; i++) {
			for(int j = 0; j < colunas; j++) {
				temp = pgm.get(i, j);
				if(temp == null) {
					arquivo.write("0\t");
				}else {
					arquivo.write(Integer.toString(temp.valor) + "\t");
				}
			}
			arquivo.write("\n");
		}
		arquivo.close();
		System.out.println("Arquivo criado!");
	}
}
