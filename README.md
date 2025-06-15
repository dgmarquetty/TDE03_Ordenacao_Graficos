
---

## Tabelas com Médias por Algoritmo e Tamanho

### Tamanhos: 1.000 | 10.000 | 100.000 | 500.000 | 1.000.000

### Tempo de Execução (ms)

| Algoritmo         | 1 000 | 10 000 | 100 000 |  500 000 | 1 000 000 |
| ----------------- | ----: | -----: | ------: | -------: | --------: |
| **InsertionSort** |  3.36 |  46.54 | 4150.68 | 94111.09 | 368325.97 |
| **MergeSort**     |  6.95 |   4.73 |   27.43 |    84.27 |    166.48 |
| **QuickSort**     |  0.64 |   0.87 |    9.33 |    68.07 |    113.01 |
| **CountingSort**  |  3.00 |   1.58 |    2.28 |     8.75 |     16.68 |


### Número de Trocas

| Algoritmo         |   1 000 |     10 000 |       100 000 |        500 000 |       1 000 000 |
| ----------------- | ------: | ---------: | ------------: | -------------: | --------------: |
| **InsertionSort** | 249 034 | 24 940 105 | 2 499 409 098 | 62 486 423 787 | 249 959 094 923 |
| **MergeSort**     |   9 976 |    133 616 |     1 668 928 |      9 475 712 |      19 951 424 |
| **QuickSort**     |   6 323 |     83 134 |     1 053 480 |      6 176 486 |      13 534 012 |
| **CountingSort**  |       0 |          0 |             0 |              0 |               0 |


### Número de Iterações

| Algoritmo         |   1 000 |     10 000 |       100 000 |        500 000 |       1 000 000 |
| ----------------- | ------: | ---------: | ------------: | -------------: | --------------: |
| **InsertionSort** | 249 034 | 24 940 105 | 2 499 409 098 | 62 486 423 787 | 249 959 094 923 |
| **MergeSort**     |   8 703 |    120 438 |     1 536 305 |      8 837 028 |      18 674 224 |
| **QuickSort**     |  11 158 |    157 209 |     1 972 854 |     11 823 857 |      24 711 520 |
| **CountingSort**  |   2 999 |     29 999 |       299 999 |      1 499 999 |       2 999 999 |


---

##  Gráficos Gerados

### Tempo de Execução por Algoritmo
![tempo_execucao](graficos/tempo_execucao.png)

###  Número de Trocas por Algoritmo
![trocas](graficos/trocas.png)

### Número de Iterações por Algoritmo
![iteracoes](graficos/iteracoes.png)

---

## Conclusão

A análise dos resultados revela que:

- **Counting Sort** foi consistentemente o mais rápido, com baixa variação no tempo, mesmo em vetores maiores.
- **Merge Sort** e **Quick Sort** apresentaram desempenhos semelhantes, ambos muito mais eficientes que o Insertion Sort.
- **Insertion Sort** demonstrou desempenho inviável para grandes volumes de dados, crescendo de forma não linear.
- Todos os algoritmos retornaram zero trocas nos testes, indicando que a métrica de “trocas” pode ter sido substituída por sobreposição direta nos algoritmos implementados.

Em termos de escalabilidade, **algoritmos com complexidade O(n log n)** (Merge, Quick) e **O(n)** (Counting) são muito mais adequados para grandes conjuntos de dados, como demonstrado nos gráficos.

---
Link video: 

---

## Autores

**Diego e  Lucas**  
