package main;
import java.util.Random;


public class TesteQuickZiviani {
	public static void main(String[] args) {
		Random rnd = new Random();
		// int[] v = { 7, 18, 10, 9, 8, 27, 1, 0, 2 };
//		 int[] vetorAleat = {8, 0, 1, 3, 9, 9, 0, 8, 3, 0};
		// int[] vetorAleat = {9, 3, 2, 6, 4, 1, 3, 7, 9};
		int[] vetorAleat = { 25, 57, 48, 37, 12, 92, 86, 33 };

		long ti, tf, tempo;

		System.out.println("vetor aleatório:");
		for (int i = 0; i < vetorAleat.length; i++) {
//			vetorAleat[i] = rnd.nextInt(100);
			// vetorAleat[i] = new MeuItem(rnd.nextInt(10));
//			System.out.print(vetorAleat[i].recuperaChave() + "\t");
			System.out.print(vetorAleat[i] + " ");
		}

		quicksort(vetorAleat);

		System.out.println("\n\nvetor ordenado:");
		for (int i = 0; i < vetorAleat.length; i++) {
//			System.out.print(vetorAleat[i].recuperaChave() + "\t");
			System.out.print(vetorAleat[i] + " ");
		}
	}

	private static void quicksort(int[] v) {
		ordena(v, 0, v.length - 1);
	}

	private static void ordena(int[] vetor, int esq, int dir) {
		if (esq < dir) {
			LimiteParticoes p = particiona(vetor, esq, dir);

			for (int i = 0; i < vetor.length; i++) {
				if (i >= esq && i <= dir)
					System.out.print(vetor[i] + "\t");
				else
					System.out.print("\t");
			}
			if (esq < p.j)
				ordena(vetor, esq, p.j);
			if (p.i < dir)
				ordena(vetor, p.i, dir);
		}
	}

	private static class LimiteParticoes {
		int i;
		int j;
	}

	public static LimiteParticoes particiona(int[] v, int esq, int dir) {
		LimiteParticoes p = new LimiteParticoes();
		p.i = esq;
		p.j = dir;
		int aux, pivo = v[(p.i + p.j) / 2];

		System.out.println("\n\npivo: " + pivo);

		do {
			while ((pivo > v[p.i]))
				p.i++;
			while ((pivo < v[p.j]))
				p.j--;
			if (p.i <= p.j) {
				aux = v[p.i];
				v[p.i] = v[p.j];
				v[p.j] = aux;
				p.i++;
				p.j--;
			}
		} while (p.i <= p.j);
		return p;
	}
}
