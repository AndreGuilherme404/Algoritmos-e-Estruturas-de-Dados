package main;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ProgOrdenacao {

	public static void main(String[] args) {

//		int[] v = { 0, 7, 3, 9, 5, 6, 1, 4, 2 };
//		heapsort(v);
//		imprimir(v);

		Scanner scn = new Scanner(System.in);
		System.out.println(
				"Mudar a prioridade da Thread/Processo para o maximo\nEnter para continuar...");
		scn.nextLine();

		Thread t = Thread.currentThread();
		t.setPriority(Thread.MAX_PRIORITY);

		int tamMax = 1000000;
		int varTamanho = 100000;
		int numRepeticoes = 100;
		long ti, tf, soma;

		// gerar o vetor desordenado (original)
		int[] vetOriginal = gerarVetor(tamMax);
		//Desorganiza o vetor (0 é vetor crescente ordenado)(100 é 100% aleatorio)
		vetOriginal = aleatorizar(100, vetOriginal);

//		imprimir(vetOriginal);
		int[] vetCopia;// = Arrays.copyOf(vetOriginal, 100);
//		imprimir(vetCopia);
//		System.out.println("Enter para continuar...");
//		scn.next();

//		System.out.println("TAM\tBolha\tB.Adap\tSel\tIns\tShell\tMerge");
//		System.out.println("TAM\tIns\tShell\tMerge\tMerge2");
		System.out.println("TAM\tShell\tMerge\tQuick\tQuicksort3\tHeap");
		// for para variar o tamanho
		for (int tam = varTamanho; tam <= tamMax; tam += varTamanho) {
			System.out.print(tam);

			// BOLHA NAO ADAPTAVEL
			// copiar pedaco do vetor orginal
//			soma = 0;
//			for (int r = 1; r <= numRepeticoes; r++) {
//
//				vetCopia = Arrays.copyOf(vetOriginal, tam);
//
//				ti = System.nanoTime(); // pegar a hora inicial
//				bolha(vetCopia); // ordenar o vetor copia
//				tf = System.nanoTime(); // pegar a hora final
//				soma += (tf - ti);
//			}
//			// calcular e imprimir o tempo
//			System.out.print("\t" + (soma / numRepeticoes));

			// BOLHA ADAPTAVEL
//			soma = 0;
//			for (int r = 1; r <= numRepeticoes; r++) {
//
//				vetCopia = Arrays.copyOf(vetOriginal, tam);
//
//				ti = System.nanoTime(); // pegar a hora inicial
//				bolhaAdap(vetCopia); // ordenar o vetor copia
//				tf = System.nanoTime(); // pegar a hora final
//				soma += (tf - ti);
//			}
//			// calcular e imprimir o tempo
//			System.out.print("\t" + (soma / numRepeticoes));

			// SELECAO
//			soma = 0;
//			for (int r = 1; r <= numRepeticoes; r++) {
//
//				vetCopia = Arrays.copyOf(vetOriginal, tam);
//
//				ti = System.nanoTime(); // pegar a hora inicial
//				selecao(vetCopia); // ordenar o vetor copia
//				tf = System.nanoTime(); // pegar a hora final
//				soma += (tf - ti);
//			}
//			// calcular e imprimir o tempo
//			System.out.print("\t" + (soma / numRepeticoes));

//			// INSERCAO
//			soma = 0;
//			for (int r = 1; r <= numRepeticoes; r++) {
//
//				vetCopia = Arrays.copyOf(vetOriginal, tam);
//
//				ti = System.nanoTime(); // pegar a hora inicial
////				ti = System.currentTimeMillis();
//				insercao(vetCopia); // ordenar o vetor copia
//				tf = System.nanoTime(); // pegar a hora final
////				tf = System.currentTimeMillis();
//				soma += (tf - ti);
//			}
//			// calcular e imprimir o tempo
//			System.out.print("\t" + (soma / numRepeticoes));

			// SHELLSORT
			soma = 0;
			for (int r = 1; r <= numRepeticoes; r++) {

				vetCopia = Arrays.copyOf(vetOriginal, tam);

				ti = System.nanoTime(); // pegar a hora inicial
//				ti = System.currentTimeMillis();
				shellsort(vetCopia); // ordenar o vetor copia
				tf = System.nanoTime(); // pegar a hora final
//				tf = System.currentTimeMillis();
				soma += (tf - ti);
			}
			// calcular e imprimir o tempo
			System.out.print("\t" + (soma / numRepeticoes));

			// MERGESORT
			soma = 0;
			int[] aux = new int[vetOriginal.length];
			for (int r = 1; r <= numRepeticoes; r++) {

				vetCopia = Arrays.copyOf(vetOriginal, tam);
//				int[] aux = new int[vetCopia.length];
//				ti = System.currentTimeMillis();
				ti = System.nanoTime(); // pegar a hora inicial
				mergesort2(vetCopia, aux); // ordenar o vetor copia
				tf = System.nanoTime(); // pegar a hora final
//				tf = System.currentTimeMillis();
				soma += (tf - ti);
			}
			// calcular e imprimir o tempo
			System.out.print("\t" + (soma / numRepeticoes));

			// QUICKSORT
			soma = 0;
			for (int r = 1; r <= numRepeticoes; r++) {

				vetCopia = Arrays.copyOf(vetOriginal, tam);
//				ti = System.currentTimeMillis();
				ti = System.nanoTime(); // pegar a hora inicial
				quicksort(vetCopia); // ordenar o vetor copia
				tf = System.nanoTime(); // pegar a hora final
//				tf = System.currentTimeMillis();
				soma += (tf - ti);
			}
			// calcular e imprimir o tempo
			System.out.print("\t" + (soma / numRepeticoes));
			// QUICKSORT3
			soma = 0;
			for (int r = 1; r <= numRepeticoes; r++) {

				vetCopia = Arrays.copyOf(vetOriginal, tam);
//				ti = System.currentTimeMillis();
				ti = System.nanoTime(); // pegar a hora inicial
				quicksort3(vetCopia); // ordenar o vetor copia
				tf = System.nanoTime(); // pegar a hora final
//				tf = System.currentTimeMillis();
				soma += (tf - ti);
			}
			// calcular e imprimir o tempo
			System.out.print("\t" + (soma / numRepeticoes));

			// HEAPSORT
			soma = 0;
			for (int r = 1; r <= numRepeticoes; r++) {

				vetCopia = Arrays.copyOf(vetOriginal, tam);
//							ti = System.currentTimeMillis();
				ti = System.nanoTime(); // pegar a hora inicial
				heapsort(vetCopia); // ordenar o vetor copia
				tf = System.nanoTime(); // pegar a hora final
//							tf = System.currentTimeMillis();
				soma += (tf - ti);
			}
			// calcular e imprimir o tempo
			System.out.println("\t" + (soma / numRepeticoes));

		}
	}

	// gera o vetor
	static int[] gerarVetor(int tamanho) {
		Random rnd = new Random();
		int[] v = new int[tamanho];
		for (int i = 0; i < v.length; i++) {
//			v[i] = rnd.nextInt(Integer.MAX_VALUE);
			v[i] = i; // CRESCENTE
//			v[i] = Integer.MAX_VALUE - i; // DECRESCENTE/REVERSO
		}

		return v;
	}
	static int[] aleatorizar(int porcentagem,int[] v) {
		Random rnd = new Random();
		for(int i = 0; i < v.length; i++) {
			if(rnd.nextDouble(1,100) < porcentagem) {
				v[i] = rnd.nextInt(1,v.length);
			}
		}
		return v;
	}

	static void imprimir(int[] vetor) {
		System.out.print("[ ");
		for (int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.println("]");
	}

	// Nao adaptavel
	public static void bolha(int vetor[]) {
		int n = vetor.length;
		int aux;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 1; j < n - i; j++) {
				if (vetor[j] < vetor[j - 1]) {
					aux = vetor[j];
					vetor[j] = vetor[j - 1];
					vetor[j - 1] = aux;
				}
			}
		}
	}

	public static void bolhaAdap(int vetor[]) {
		int n = vetor.length;
		int aux;
		boolean troca;
		for (int i = 0; i < n - 1; i++) {
			troca = false;
			for (int j = 1; j < n - i; j++) {
				if (vetor[j] < vetor[j - 1]) {
					aux = vetor[j];
					vetor[j] = vetor[j - 1];
					vetor[j - 1] = aux;
					// ocorreu troca
					troca = true;
				}
			}

			// Ponto de Adaptacao:
			// Se nenhuma troca ocorreu na passagem, a lista esta totalmente
			// ordenada.
			if (troca == false) {
				// System.out.println("A lista esta ordenada! Parando apos a
				// passagem " + (i + 1));
				break;
			}
		}
	}

	public static void selecao(int vetor[]) {
		int n = vetor.length;
		int min;
		int aux;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++) {
				if (vetor[j] < vetor[min])
//				if (vetor[j].compara(vetor[min]) < 0)
					min = j;
			}
			aux = vetor[min];
			vetor[min] = vetor[i];
			vetor[i] = aux;
		}
	}

	public static void insercao(int vetor[]) {
		int i, j, n = vetor.length;
		int aux;
		for (i = 1; i < n; i++) {
			aux = vetor[i];
			j = i - 1;
//			while ((j >= 0) && (aux.compara(vetor[j]) < 0)) {
			while ((j >= 0) && (aux < vetor[j])) {
				vetor[j + 1] = vetor[j];
				j--;
			}
			vetor[j + 1] = aux;
		}
	}

	public static void shellsort(int[] vetor) {
		int i, j, n = vetor.length, h = 1;
		int aux;
		do {
			h = h * 3 + 1;
		} while (h < n);
		do {
			h /= 3;
			for (i = h; i < n; i++) {
				aux = vetor[i];
				j = i;
				while ((j >= h) && (aux < vetor[j - h])) {
					vetor[j] = vetor[j - h];
					j -= h;
				}
				vetor[j] = aux;
			}
		} while (h != 1);
	}

	// -------------- MERGE
	public static void mergesort(int[] vetor) {
		int[] aux = new int[vetor.length];
		merge(vetor, aux, 0, vetor.length - 1);
	}

	public static void mergesort2(int[] vetor, int[] aux) {
		merge(vetor, aux, 0, vetor.length - 1);
	}

	public static void merge(int[] v, int[] aux, int inicio, int fim) {
		int meio;
		if (inicio < fim) {
			meio = (inicio + fim) / 2;
			merge(v, aux, inicio, meio);
			merge(v, aux, meio + 1, fim);
			intercala(v, aux, inicio, meio, fim);
		}
	}

	private static void intercala(int[] v, int[] aux, int inicio, int meio,
			int fim) {
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

	// --------------- QUICK
	private static void quicksort(int[] v) {
		ordena(v, 0, v.length - 1);
	}

	private static void ordena(int[] vetor, int esq, int dir) {
		if (esq < dir) {
			LimiteParticoes p = particiona(vetor, esq, dir);

//			for (int i = 0; i < vetor.length; i++) {
//				if (i >= esq && i <= dir)
//					System.out.print(vetor[i] + "\t");
//				else
//					System.out.print("\t");
//			}
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

//		System.out.println("\n\npivo: " + pivo);

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
	public static LimiteParticoes particiona3(int[] v, int esq, int dir) {
		LimiteParticoes p = new LimiteParticoes();
		p.i = esq;
		p.j = dir;
		int media = (p.i + p.j)/2;
		int aux, pivo = v[(p.i+p.j+media)/3];

//		System.out.println("\n\npivo: " + pivo);

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

	private static void quicksort3(int[] v) {
		ordena3(v, 0, v.length - 1);
	}
	private static void ordena3(int[] vetor, int esq, int dir) {
		if (esq < dir) {
			LimiteParticoes p = particiona3(vetor, esq, dir);

//			for (int i = 0; i < vetor.length; i++) {
//				if (i >= esq && i <= dir)
//					System.out.print(vetor[i] + "\t");
//				else
//					System.out.print("\t");
//			}
			if (esq < p.j)
				ordena(vetor, esq, p.j);
			if (p.i < dir)
				ordena(vetor, p.i, dir);
		}
	}

	// ---------------- HEAPSORT
	public static void heapsort(int v[]) {
		int n = v.length - 1;
		FPHeapMax fpHeap = new FPHeapMax(v);
		int dir = n;
		fpHeap.constroi(); // constroi o heap
//		imprimir(v);
//		System.out.println("\n----");
		while (dir > 1) { // ordena o vetor
			int x = v[1];
			v[1] = v[dir];
			v[dir] = x;
			dir--;
			fpHeap.refaz(1, dir);
//			imprimir(v);
		}
	}

	private static class FPHeapMax {
		private int[] v;
		private int n;

		public FPHeapMax(int[] v) {
			this.v = v;
			n = v.length - 1;
		}

		public void constroi() {
			int esq = n / 2 + 1;
			while (esq > 1) {
				esq--;
				this.refaz(esq, this.n);
			}
		}

		public void refaz(int esq, int dir) {
			int j = esq * 2;
			int x = this.v[esq];
			while (j <= dir) {
				if ((j < dir) && (v[j] < v[j + 1]))
					j++;
				if (x >= v[j])
					break;
				this.v[esq] = this.v[j];
				esq = j;
				j = esq * 2;
			}
			this.v[esq] = x;
		}

	}
}
