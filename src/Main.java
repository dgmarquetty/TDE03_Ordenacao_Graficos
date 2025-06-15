import algoritmos.CountingSort;
import algoritmos.InsertionSort;
import algoritmos.MergeSort;
import algoritmos.QuickSort;
import utils.GeradorVetor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        // Cria pasta de saída se não existir
        File pastaResultados = new File("resultados");
        if (!pastaResultados.exists()) {
            pastaResultados.mkdirs();
        }

        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        int rodadas = 5;
        String[] algoritmos = {"InsertionSort", "MergeSort", "QuickSort", "CountingSort"};

        for (String algoritmo : algoritmos) {
            for (int tamanho : tamanhos) {
                for (int seed = 0; seed < rodadas; seed++) {
                    int[] vetor = GeradorVetor.gerarVetor(tamanho, seed);
                    System.out.printf("[INFO] Rodando %s com %d elementos (seed=%d)...%n", algoritmo, tamanho, seed);
                    imprimirComentarioEngracado(algoritmo);
                    rodar(algoritmo, vetor.clone(), tamanho, seed);
                }
            }
        }

        System.out.println("\n Todos os algoritmos foram executados com sucesso.");
    }

    private static void rodar(String algoritmo, int[] vetor, int tamanho, int seed) {
        long[] trocas = new long[1];
        long[] iteracoes = new long[1];

        long inicio = System.nanoTime();

        switch (algoritmo) {
            case "InsertionSort" -> InsertionSort.ordenar(vetor, tamanho, trocas, iteracoes);
            case "MergeSort"     -> MergeSort.ordenar(vetor, tamanho, trocas, iteracoes);
            case "QuickSort"     -> QuickSort.ordenar(vetor, tamanho, trocas, iteracoes);
            case "CountingSort"  -> CountingSort.ordenar(vetor, tamanho, trocas, iteracoes);
        }

        long fim = System.nanoTime();
        long tempo = fim - inicio;

        salvarCSV(algoritmo, tamanho, tempo, trocas[0], iteracoes[0]);
        salvarLog(algoritmo, tamanho, tempo, trocas[0], iteracoes[0], seed);
    }

    private static void salvarCSV(String algoritmo, int tamanho, long tempo, long trocas, long iteracoes) {
        try (FileWriter fw = new FileWriter("resultados/" + algoritmo + ".csv", true)) {
            fw.write(String.format("%d,%.4f,%d,%d%n", tamanho, tempo / 1_000_000.0, trocas, iteracoes));
        } catch (IOException e) {
            System.err.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }

    private static void salvarLog(String algoritmo, int tamanho, long tempo, long trocas, long iteracoes, int seed) {
        try (FileWriter fw = new FileWriter("resultados/log_execucao.txt", true)) {
            fw.write(String.format(
                    "Algoritmo: %s | Tamanho: %d | Tempo: %.2fms | Trocas: %d | Iterações: %d | Seed: %d%n%s%n%n",
                    algoritmo, tamanho, tempo / 1_000_000.0, trocas, iteracoes, seed,
                    gerarFraseEngracada(algoritmo)
            ));
        } catch (IOException e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }

    private static String gerarFraseEngracada(String algoritmo) {
        return switch (algoritmo) {
            case "InsertionSort" -> "InsertionSort finalizado... na paz de quem dobra roupa ouvindo lo-fi.";
            case "MergeSort"     -> "MergeSort juntou as peças como um quebra-cabeça sem faltar nenhuma.";
            case "QuickSort"     -> "QuickSort fez jus ao nome e terminou antes mesmo da ansiedade bater.";
            case "CountingSort"  -> "CountingSort contou cada passo como quem revisa extrato bancário... com trauma.";
            default              -> "Algoritmo misterioso completou sua missão.";
        };
    }

    private static void imprimirComentarioEngracado(String algoritmo) {
        String frase = switch (algoritmo) {
            case "InsertionSort" -> "[INFO] InsertionSort na luta aqui... esse tá teimoso... melhor ir tomar um balde de café...";
            case "MergeSort"     -> "[INFO] MergeSort quase lá... vai tomar um café!";
            case "QuickSort"     -> "[INFO] QuickSort passinho de formiga, mas tá no caminho. Aguenta aí!";
            case "CountingSort"  -> "[INFO] CountingSort esse é rapidinho... na lentidão mas vai!";
            default              -> "[INFO] O negócio tá rodando... aguenta aí!";
        };
        System.out.println(frase);
    }
}
