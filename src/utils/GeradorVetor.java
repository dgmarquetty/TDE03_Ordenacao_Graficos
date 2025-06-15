package utils;

import java.util.Random;

public class GeradorVetor {
    public static int[] gerarVetor(int tamanho, int seed) {
        Random random = new Random(seed);
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1_000_000);
        }
        return vetor;
    }
}
