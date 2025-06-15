package algoritmos;

public class QuickSort {
    public static int[] ordenar(int[] vetor, int tamanho, long[] trocas, long[] iteracoes) {
        int[] resultado = vetor.clone();
        trocas[0] = 0;
        iteracoes[0] = 0;
        quickSort(resultado, 0, tamanho - 1, trocas, iteracoes);
        return resultado;
    }

    private static void quickSort(int[] vetor, int inicio, int fim, long[] trocas, long[] iteracoes) {
        if (inicio < fim) {
            int p = particionar(vetor, inicio, fim, trocas, iteracoes);
            quickSort(vetor, inicio, p - 1, trocas, iteracoes);
            quickSort(vetor, p + 1, fim, trocas, iteracoes);
        }
    }

    private static int particionar(int[] vetor, int inicio, int fim, long[] trocas, long[] iteracoes) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            iteracoes[0]++;
            if (vetor[j] <= pivo) {
                i++;
                trocar(vetor, i, j);
                trocas[0]++;
            }
        }

        trocar(vetor, i + 1, fim);
        trocas[0]++;
        return i + 1;
    }

    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}
