package algoritmos;

public class CountingSort {
    public static int[] ordenar(int[] vetor, int tamanho, long[] trocas, long[] iteracoes) {
        trocas[0] = 0; // CountingSort não realiza trocas reais
        iteracoes[0] = 0;

        int maior = vetor[0];
        for (int i = 1; i < tamanho; i++) {
            if (vetor[i] > maior) maior = vetor[i];
            iteracoes[0]++;
        }

        int[] contagem = new int[maior + 1];
        for (int i = 0; i < tamanho; i++) {
            contagem[vetor[i]]++;
            iteracoes[0]++;
        }

        int[] resultado = new int[tamanho];
        int index = 0;
        for (int i = 0; i <= maior; i++) {
            while (contagem[i]-- > 0) {
                resultado[index++] = i;
                iteracoes[0]++;
            }
        }

        return resultado;
    }
}
