package main;

public class FilaDinamica<T> {
	T fila[];
	int inicio;
	int fim;

	@SuppressWarnings("unchecked")
	public FilaDinamica() {
		fila = (T[]) new Object[25];
	}
	public T peek() {
		if(inicio >= fim) {
			return null;
		}
		return fila[inicio];		
	}

	public void enfileira(T item) {
		if(fim >= fila.length) {
			aumentarFila();
		}
		fila[fim++] = item;
	}

	public T desenfileira() {
		if(fim == 0) {
			throw new NullPointerException("Fila Vazia");
		}
		if(inicio >= fim) {
			throw new NullPointerException("Fila Vazia");
		}
		if(inicio > 23){
			flushFila();
		}
		return fila[inicio++];
	}

	public void aumentarFila() {
		T[] temp = (T[]) new Object[fila.length + 25];
		for (int i = 0; i < fila.length; i++) {
			temp[i] = fila[i];
		}
		fila = temp;
	}
	public void flushFila() {
		T[] temp = (T[]) new Object[fila.length];
		int fimTemp = fim - inicio;
		for(int i = 0; i < fim - inicio; i++) {
			temp[i] = fila[inicio + i];
		}
		inicio = 0;
		fim = fimTemp;
		fila = temp;
	}
	public int size() {
		return fim;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < fim; i++) {
			sb.append(fila[i] + "\n");
		}
		return sb.toString();
	}
}
