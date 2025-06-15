import pandas as pd
import matplotlib.pyplot as plt
import os

# Caminhos de entrada e saída
PASTA_CSV = "resultados"
PASTA_SAIDA = "graficos"

# Lista com os algoritmos esperados
algoritmos = [
    "InsertionSort",
    "MergeSort",
    "QuickSort",
    "CountingSort"
]

# Leitura dos arquivos CSV
dfs = []
for alg in algoritmos:
    caminho = os.path.join(PASTA_CSV, f"{alg}.csv")
    if os.path.exists(caminho):
        try:
            df = pd.read_csv(
                caminho,
                header=None,
                names=["Tamanho", "Tempo_ms", "Trocas", "Iteracoes"]
            )
            df["Algoritmo"] = alg
            dfs.append(df)
            print(f" Arquivo lido: {caminho}")
        except Exception as e:
            print(f" Erro ao ler {caminho}: {e}")
    else:
        print(f" Arquivo não encontrado: {caminho}")

# Verifica se algum arquivo foi carregado
if not dfs:
    print(" Nenhum dado encontrado. Verifique a pasta 'resultados'.")
    exit()

# Junta e agrupa os dados
df_total = pd.concat(dfs, ignore_index=True)
df_grouped = df_total.groupby(["Algoritmo", "Tamanho"]).mean().reset_index()

# Garante que a pasta de saída existe
os.makedirs(PASTA_SAIDA, exist_ok=True)


# Função para gerar gráficos
def gerar_grafico(y, titulo, ylabel, nome_arquivo):
    plt.figure(figsize=(10, 6))

    for alg in algoritmos:
        sub = df_grouped[df_grouped["Algoritmo"] == alg]
        if not sub.empty:
            plt.plot(
                sub["Tamanho"],
                sub[y],
                marker='o',
                label=alg
            )
        else:
            print(f"⚠️ Nenhum dado encontrado para: {alg}")

    plt.title(titulo)
    plt.xlabel("Tamanho do Vetor")
    plt.ylabel(ylabel)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()

    caminho_saida = os.path.join(
        PASTA_SAIDA,
        nome_arquivo
    )
    plt.savefig(caminho_saida)
    print(
        f" Gráfico salvo em: {caminho_saida}"
    )
    plt.close()


# Gera os gráficos principais
gerar_grafico(
    "Tempo_ms",
    "Tempo de Execução por Algoritmo",
    "Tempo (ms)",
    "tempo_execucao.png"
)

gerar_grafico(
    "Trocas",
    "Número de Trocas por Algoritmo",
    "Trocas",
    "trocas.png"
)

gerar_grafico(
    "Iteracoes",
    "Número de Iterações por Algoritmo",
    "Iterações",
    "iteracoes.png"
)
