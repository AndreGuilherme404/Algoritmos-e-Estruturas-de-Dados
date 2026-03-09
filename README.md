# 📚 Estruturas de Dados e Algoritmos

## Sobre o Repositório
Este repositório consolida projetos e implementações práticas desenvolvidas durante o curso de Engenharia da Computação (CEFET-MG). O foco principal é a aplicação de estruturas de dados clássicas e a análise de complexidade de algoritmos em cenários que simulam problemas do mundo real.

Todos os projetos foram desenvolvidos em **Java**, priorizando a construção manual das estruturas (listas encadeadas, filas, pilhas, matrizes) sem o uso exclusivo de coleções prontas da linguagem, demonstrando domínio sobre alocação de memória e manipulação de ponteiros.

##  Índice de Projetos

Abaixo estão os projetos contidos neste repositório. Clique no link de cada um para acessar o código-fonte e a documentação técnica específica detalhando a lógica, os testes e os resultados obtidos.

* **[1. Análise Empírica de Algoritmos de Ordenação](./algoritmos-de-ordenacao)**
  * Estudo comparativo de performance (tempo de execução real) entre Quick Sort, Merge Sort, Shell Sort, Heap Sort, Bubble Sort, Selection Sort e   Insertion Sort usando massas de dados de até 1 milhão de posições. Inclui testes de estresse com vetores aleatórios, crescentes e decrescentes.

* **[2. Simulação de Atendimento com Filas Dinâmicas](./sistema-atendimento)**
  * Sistema de Call Center simulado via eventos discretos. Implementa Filas Dinâmicas (usando *Generics*) para escalonamento de boxes de atendimento, regras de prioridade (VIPs) e gestão de tempo de espera/timeout.

* **[3. Processamento de Imagens com Matriz Esparsa](./matriz-esparsa)**
  * Aplicação de Matrizes Esparsas baseadas em listas ortogonalmente encadeadas para manipulação e processamento de arquivos de imagem (`.pgm`). Implementa filtros (negativo) e rotação espacial otimizando o consumo de memória ao ignorar pixels de valor zero.

## Tecnologias e Conceitos Aplicados
* **Linguagem:** Java
* **Paradigmas:** Programação Orientada a Objetos (POO), Tipos Genéricos (*Generics*).
* **Estruturas Base:** Filas e Pilhas Dinâmicas, Matrizes Esparsas, Listas Encadeadas.
* **Análise:** Complexidade de Tempo (Big-O), testes empíricos de algoritmos de ordenação, simulação de eventos discretos.

---
> **Aviso:** Para testar os projetos localmente, recomenda-se clonar o repositório e compilar os arquivos de cada diretório individualmente via terminal.
