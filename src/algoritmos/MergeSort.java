package algoritmos;

public class MergeSort {
    public static int[] ordenar(int[] vetor, int tamanho, long[] trocas, long[] iteracoes) {
        trocas[0] = 0;
        iteracoes[0] = 0;
        int[] resultado = vetor.clone();
        mergeSort(resultado, 0, tamanho - 1, trocas, iteracoes);
        return resultado;
    }

    private static void mergeSort(int[] vetor, int inicio, int fim, long[] trocas, long[] iteracoes) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio, trocas, iteracoes);
            mergeSort(vetor, meio + 1, fim, trocas, iteracoes);
            merge(vetor, inicio, meio, fim, trocas, iteracoes);
        }
    }

    private static void merge(int[] vetor, int inicio, int meio, int fim, long[] trocas, long[] iteracoes) {
        int[] esquerda = new int[meio - inicio + 1];
        int[] direita = new int[fim - meio];

        for (int i = 0; i < esquerda.length; i++) esquerda[i] = vetor[inicio + i];
        for (int i = 0; i < direita.length; i++) direita[i] = vetor[meio + 1 + i];

        int i = 0, j = 0, k = inicio;
        while (i < esquerda.length && j < direita.length) {
            iteracoes[0]++;
            if (esquerda[i] <= direita[j]) {
                vetor[k++] = esquerda[i++];
            } else {
                vetor[k++] = direita[j++];
            }
            trocas[0]++;
        }

        while (i < esquerda.length) {
            vetor[k++] = esquerda[i++];
            trocas[0]++;
        }

        while (j < direita.length) {
            vetor[k++] = direita[j++];
            trocas[0]++;
        }
    }
}
