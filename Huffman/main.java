import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Teste {
	public static void main(String[] args) throws IOException {
		byte[] palavra = System.in.readAllBytes();
        if (palavra.length == 0) return;

        // Determinando o comprimento real (removendo quebra de linha final se houver)
        int len = palavra.length;
        if (len >= 2 && palavra[len-2] == '\r' && palavra[len-1] == '\n') {
            len -= 2;
        } else if (len >= 1 && palavra[len-1] == '\n') {
            len -= 1;
        }
        
        if (len <= 0) return;
		HashMap<Character, Integer> frequencia = new HashMap<>();
		PriorityQueue<No> tries = new PriorityQueue<>(Comparator.comparing(no -> no.getFrequencia()));
		for (int i = 0; i < len; i++) {
			
				char c = (char)palavra[i];
				frequencia.merge(c, 1, Integer::sum);
			
			
		}

		frequencia.forEach((chave, valor) -> {
			tries.add(new No(chave, valor, null, null));
		});
		int bitsFinais = 0;
		while(tries.size() != 1){
			No menorFreq1 = tries.poll();
			No menorFreq2 = tries.poll();
			
			No pai = new No(menorFreq1.getFrequencia() + menorFreq2.getFrequencia());
			bitsFinais += pai.getFrequencia();
			pai.esquerda = menorFreq1;
			pai.direita = menorFreq2;
			menorFreq1.pai = pai;
			menorFreq2.pai = pai;
			tries.add(pai);
		}

		
		No pai = tries.poll();
		long bits = pai.getBits();
		System.out.println("#bits cabecalho " + bits);
		System.out.println("#bits comprimidos " + bitsFinais);
		if((bits + bitsFinais + 32)%8 == 0) {
			System.out.println(bits + bitsFinais + 32 + " bits");
		}else {
			System.out.println(bits + bitsFinais + 32 + 8-(bits + bitsFinais + 32)%8 + " bits");
		}
		

	}
	private static class FastReader {

		private BufferedReader b;
		private StringTokenizer s;

		public FastReader() {
			b = new BufferedReader(new InputStreamReader(System.in));
		}

		public String next() {
			while (s == null || !s.hasMoreTokens()) {
				try {
					s = new StringTokenizer(b.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return s.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				if (s != null && s.hasMoreTokens()) {
					str = s.nextToken("\n");
				} else {
					str = b.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}

		public boolean hasNext() {
			try {
				while (s == null || !s.hasMoreTokens()) {
					String line = b.readLine();
					if (line == null) {
						return false;
					}
					s = new StringTokenizer(line);
				}
				return true;
			} catch (IOException e) {
				return false;
			}
		}
	}
}

class No {
	char letra;
	private int frequencia;
	No pai;
	No esquerda;
	No direita;

	No(char letra, int frequencia, No esquerda, No direita) {
		this.letra = letra;
		this.frequencia = frequencia;
		this.esquerda = esquerda;
		this.direita = direita;
	}

	No(int frequencia) {
		this.frequencia = frequencia;
	}

	long getBits() {
		if (this.esquerda == null && this.direita == null) {
			return 9;
		}
		return 1 + this.esquerda.getBits() + this.direita.getBits();
	}

	void setFrequencia(int a) {
		frequencia = a;
	}

	int getFrequencia() {
		return frequencia;
	}
}
