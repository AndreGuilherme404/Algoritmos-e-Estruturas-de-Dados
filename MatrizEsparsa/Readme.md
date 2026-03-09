# 🖼️ Processamento de Imagens com Matriz Esparsa

## Sobre o Projeto
Este projeto aplica o conceito de **Matriz Esparsa** para otimizar o armazenamento e o processamento de imagens digitais no formato `.pgm` (Portable Gray Map). 

O objetivo principal é demonstrar o domínio sobre alocação dinâmica de memória e manipulação de ponteiros em Java. Em vez de carregar a imagem inteira em uma matriz bidimensional tradicional, o sistema armazena na memória apenas os pixels que possuem valores diferentes de zero (preto), reduzindo drasticamente o consumo de recursos.

## Transformações Aplicadas
O sistema lê o arquivo de imagem bruto, aloca os pixels na lista encadeada e realiza sequencialmente as seguintes operações matemáticas e visuais:
* **Inserção de Borda:** Adiciona uma moldura de 3 pixels (valor 255 - branco) nas extremidades da matriz.
* **Filtro Negativo:** Inverte as cores da imagem (`255 - valor do pixel`). Se um pixel se torna zero após a inversão, seu nó é destruído e removido da estrutura para poupar memória.
* **Rotação 90º:** Rotaciona toda a imagem em 90 graus no sentido horário, transpondo e realocando as coordenadas (x, y) de cada célula.
* **Exportação:** Percorre a estrutura gerando um novo arquivo de saída (`saida.pgm`) contendo a imagem final processada.

## Estrutura de Dados
A lógica foi construída inteiramente do zero, sem o uso de coleções prontas do Java para a matriz:
* A classe `Celula` funciona como um nó interligado ortogonalmente por ponteiros (`direita` e `abaixo`).
* O algoritmo de inserção (`set()`) e remoção (`remove()`) foi desenhado para percorrer apenas os caminhos necessários pelas linhas e colunas, reajustando os ponteiros dinamicamente.

## Como Executar na Sua Máquina

1. Clone o repositório para o seu ambiente local.
2. Navegue até a pasta correspondente aos arquivos fonte deste projeto.
3. Altere no Main.java o path para apontar para a localização do arquivo .pgm em sua máquina local.
4. Compile os arquivos:
   ```bash
   javac Main/*.java
5. Execute o arquivo compilado:
   ```bash
   java Main.Main
O resultado final será salvo automaticamente em um arquivo saida.pgm no diretório raiz da execução.
