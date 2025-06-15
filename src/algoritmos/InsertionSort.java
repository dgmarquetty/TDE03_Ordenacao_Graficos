package algoritmos;

public class InsertionSort {
    public static int[] ordenar(int[] vetor, int tamanho, long[] trocas, long[] iteracoes) {
        int[] resultado = vetor.clone();
        trocas[0] = 0;
        iteracoes[0] = 0;

        for (int i = 1; i < tamanho; i++) {
            int chave = resultado[i];
            int j = i - 1;
            iteracoes[0]++;

            while (j >= 0 && resultado[j] > chave) {
                resultado[j + 1] = resultado[j];
                j--;
                trocas[0]++;
                iteracoes[0]++;
            }

            resultado[j + 1] = chave;
            trocas[0]++;
        }

        return resultado;
    }
}
