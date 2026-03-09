# 📊 Análise Empírica de Algoritmos de Ordenação

## Sobre o Projeto
Este projeto foca na implementação e, principalmente, na **análise de performance empírica** de métodos avançados de ordenação de dados em Java. 

O objetivo não é apenas ordenar arrays, mas medir o tempo de execução real de diferentes algoritmos frente a volumes variados de dados e em diferentes cenários (pior, médio e melhor caso). Os resultados gerados pelo sistema foram exportados para o formato `.csv` e analisados em planilhas eletrônicas para a visualização gráfica da complexidade de tempo de cada estrutura.

## Algoritmos Implementados
* **Quick Sort:** Implementação clássica com base na literatura de Nívio Ziviani.
* **Merge Sort:** Algoritmo estável de divisão e conquista de complexidade $O(n \log n)$.
* **Shell Sort:** Extensão do algoritmo de inserção utilizando saltos (gaps) para otimização de trocas.

## Metodologia de Teste
Para validar o comportamento assintótico dos algoritmos, o sistema gera e processa os dados sob as seguintes condições rigorosas:
* **Escala de Entradas:** Testes realizados com arrays crescendo de 10.000 até 1.000.000 de elementos.
* **Cenários de Organização:** * Arrays gerados com números totalmente **aleatórios**.
    * Arrays já ordenados de forma **crescente** (para forçar cenários de melhor/pior caso dependendo do pivô/lógica).
    * Arrays ordenados de forma **decrescente**.
* **Métrica de Desempenho:** O tempo de execução de cada operação foi cronometrado em milissegundos para comparação direta.

## Como Executar na Sua Máquina

1. Clone o repositório para o seu ambiente local.
2. Navegue pelo terminal até a pasta com os arquivos fonte.
3. Compile todas as classes Java:
   ```bash
   javac *.java
4. Execute o programa principal que realiza as medições:
   ```bash
   java ProgOrdenacao
Obs.: Descomente os métodos de ordenação para ver eles funcionando, para valores altos comparar um algoritmo $O(n^{2})$ com um $O(nlogn)$ não faz sentido, por isso a maioria está comentada
