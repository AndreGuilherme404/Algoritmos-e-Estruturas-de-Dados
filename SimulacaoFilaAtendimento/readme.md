# 🎧 Simulação de Atendimento com Filas Dinâmicas (Call Center)

## Sobre o Projeto
Este projeto é uma simulação de eventos discretos (baseada na passagem de minutos) de um sistema de atendimento ao cliente tipo Call Center. 

O principal objetivo técnico deste trabalho é a implementação e manipulação de **Filas Dinâmicas** construídas do zero (sem o uso de bibliotecas prontas de coleções do Java), aplicando regras de negócio complexas de prioridade, escalabilidade de recursos e gestão de tempo.

## Dinâmica da Simulação
O sistema simula o fluxo de um dia de trabalho, gerenciando as seguintes variáveis a cada "minuto" processado:
* **Escalonamento de Boxes:** O sistema abre e fecha boxes de atendimento dinamicamente com base na probabilidade de demanda e no tamanho das filas.
* **Gestão de Atendentes:** Simula o ciclo de vida real de um funcionário, enviando-o automaticamente para a fila de almoço (`filaAlmocoAtendente`) e controlando seu limite de fadiga/tempo máximo de trabalho contínuo (`TEMPTRABALHANDOMAX`).
* **Sistema de Prioridade (VIP vs Normal):** Clientes VIPs possuem uma fila dedicada. Para evitar clientes da fila normal não sendo atendidos, o sistema possui uma regra de limite de VIPs atendidos em sequência (`VIPSSEQUENCIAMAX`).
* **Desistência por Timeout:** Clientes possuem um tempo máximo de tolerância na fila (`TEMPESPERAMAX`). Se o limite for atingido, eles saem da fila e são contabilizados como clientes insatisfeitos no relatório final.

## Estruturas de Dados
O coração do projeto é a classe `FilaDinamica<T>`:
* **Generics em Java:** A fila foi construída usando tipos genéricos (`<T>`), permitindo que a mesma estrutura lógica seja reutilizada para gerenciar a Fila de Clientes, Fila de Clientes VIP, Fila de Espera de Atendentes e Fila de Almoço.
* **Gerenciamento de Memória (Flush):** Como a fila é baseada em arrays, as operações de enfileirar/desenfileirar deslocam os índices para a direita. Para evitar estouro de memória ou redimensionamentos desnecessários, foi implementado o método `flushFila()`, que reorganiza os elementos na base do array periodicamente.

## Relatório Gerencial
Ao final da simulação, o programa gera um relatório no console contendo:
* Número de clientes atendidos por box e tempo médio de atendimento.
* Tempo de trabalho e produtividade individual de cada atendente.
* Taxa de abandono (clientes que saíram da fila por demora).
* Histórico de abertura e fechamento dinâmico de boxes.

## Como Executar na Sua Máquina
1. Clone o repositório para o seu computador.
2. Navegue até o diretório raiz do projeto (onde a pasta `main` está localizada).
3. Compile os arquivos Java:
   ```bash
   javac main/*.java
4. Execute a simulação:
   ```bash
   java main.Main
Nota: As configurações da simulação (como quantidade de atendentes, horas de simulação, chance de chegada de clientes, etc.) podem ser customizadas alterando as variáveis no arquivo Main.java antes da compilação.
