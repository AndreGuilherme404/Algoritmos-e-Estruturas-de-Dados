package main;

public class TesteShell {
	public static void main(String[] args) {
		// int[] v = { 12, 9, 4, 1, 2, 0, 5, 23, 10 };
		//int[] v = { 25, 57, 48, 37, 12, 92, 86, 33 };
		//int[] v = { 7, 18, 10, 9, 8, 27, 1, 0, 2 };
//		int[] v = { 4, 8, 6, 9, 7, 1, 0, 2 };
		int[] v = { 3, 4, 9, 2, 5, 8, 2, 1, 7, 4, 6, 2, 9, 8, 5, 1 };
		imprimir(v);
		shellsort(v);
	}

	public static void shellsort(int[] v) {
		int i, j, n = v.length;
		int[] hs = {5, 3, 1 };
		int h = 1;
		int aux;
		// do {
		// h = h * 3 + 1;
		// } while (h < n);
		int k = 0;
		do {
			// h /= 3;
			h = hs[k];
			System.out.println("\n\nh= " + h);
			for (i = h; i < n; i++) {
				aux = v[i];
				j = i;
				// while ((j >= h) && aux.compara(v[j - h]) < 0){
				while ((j >= h) && (aux < v[j - h])) {
					v[j] = v[j - h];
					j -= h;
				}
				v[j] = aux;
				imprimir(v);
			}
			k++;
		} while (h != 1);
	}

	private static void imprimir(int[] v) {
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + " ");
		System.out.println();
	}

}
