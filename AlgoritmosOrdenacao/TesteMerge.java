package main;


public class TesteMerge {
	public static void main(String[] args) {
		// Item[] vetorAleat = new Item[8];
		// vetorAleat[0] = new MeuItem(25);
		// vetorAleat[1] = new MeuItem(57);
		// vetorAleat[2] = new MeuItem(48);
		// vetorAleat[3] = new MeuItem(37);
		// vetorAleat[4] = new MeuItem(12);
		// vetorAleat[5] = new MeuItem(92);
		// vetorAleat[6] = new MeuItem(86);
		// vetorAleat[7] = new MeuItem(33);

		// int[] vetorAleat = { 57, 25, 48, 37, 92, 12, 86, 33, 10};
		int[] vetorAleat = { 47, 26, 33, 5, 99, 38, 64, 15 };

		long ti, tf, tempo;

		System.out.println("vetor aleatório:");
		for (int i = 0; i < vetorAleat.length; i++) {
			// System.out.print(vetorAleat[i].recuperaChave() + "\t");
			System.out.print(vetorAleat[i] + "\t");
		}
		System.out.println("\n-----");
		mergesort(vetorAleat);
		// mergesort1(0, vetorAleat.length, vetorAleat);
		System.out.println("\n-----");

		System.out.println("vetor ordenado:");
		for (int i = 0; i < vetorAleat.length; i++) {
			// System.out.print(vetorAleat[i].recuperaChave() + "\t");
			System.out.print(vetorAleat[i] + "\t");
		}
	}

	// algoritmo do Paulo Feofiloff
	// https://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
	public static void mergesort1(int v[], int[] aux, int p, int r) {
		if (p < r - 1) { // 1
			int q = (p + r) / 2; // 2
			mergesort1(v, aux, p, q); // 3
			mergesort1(v, aux, q, r); // 4
			intercala1(v, aux, p, q, r); // 5
			System.out.println();
			for (int i = 0; i < v.length; i++)
				if (i >= p && i <= r)
					// System.out.print(a[i].recuperaChave() + "\t");
					System.out.print(v[i] + "\t");
				else
					System.out.print("\t");

		}
	}

	private static void intercala1(int v[], int[] aux, int p, int q, int r) {
		// int[] aux = new int[r - p];
		int i = p, j = q;
		int k = 0;

		while (i < q && j < r) {
			if (v[i] <= v[j])
				aux[k++] = v[i++];
			else
				aux[k++] = v[j++];
		}
		while (i < q)
			aux[k++] = v[i++];
		while (j < r)
			aux[k++] = v[j++];
		for (i = p; i < r; ++i)
			v[i] = aux[i - p];
	}

	public static void mergesort(int[] vetor) {
		int[] aux = new int[vetor.length];
		merge(vetor, aux, 0, vetor.length - 1);
		// mergesort1(a, aux, 0, a.length);
	}

	public static void merge(int[] v, int[] aux, int inicio, int fim) {
		int meio;
		if (inicio < fim) {
			meio = (inicio + fim) / 2;
			merge(v, aux, inicio, meio);
			merge(v, aux, meio + 1, fim);
			intercala(v, aux, inicio, meio, fim);

			System.out.println();
			for (int i = 0; i < v.length; i++) {
				if (i >= inicio && i <= fim)
					// System.out.print(a[i].recuperaChave() + "\t");
					System.out.print(v[i] + "\t");
				else
					System.out.print("\t");
			}
		}
	}

	private static void intercala(int[] v, int[] aux, int inicio, int meio, int fim) {
		// Copiando o trecho da lista que vai ser ordenada
		for (int i = inicio; i <= fim; i++) {
			aux[i] = v[i];
		}

		int i = inicio;
		int j = meio + 1;
		int k = inicio;

		// Junção das listas ordenadas
		while (i <= meio && j <= fim) {
			if (aux[i] < aux[j])
				v[k++] = aux[i++];
			else
				v[k++] = aux[j++];
		}

		// acrescenta de itens que não foram usados na junção
		while (i <= meio)
			v[k++] = aux[i++];

		while (j <= fim)
			v[k++] = aux[j++];
	}
}
