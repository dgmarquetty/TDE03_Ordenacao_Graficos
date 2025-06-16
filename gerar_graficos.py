import pandas as pd
import matplotlib.pyplot as plt
import os
import glob

# Diretórios de entrada e saída
diretorio_csv = 'resultados'
diretorio_saida = 'graficos'

# Cria pasta de saída, se não existir
os.makedirs(diretorio_saida, exist_ok=True)

# Remove gráficos antigos
for arquivo in glob.glob(f'{diretorio_saida}/*.png'):
    os.remove(arquivo)

# Carrega e calcula médias
lista_medias = []
for caminho in glob.glob(f'{diretorio_csv}/*.csv'):
    algoritmo = os.path.splitext(os.path.basename(caminho))[0]
    df = pd.read_csv(
        caminho,
        header=None,
        names=['Tamanho', 'Tempo_ms', 'Trocas', 'Iteracoes']
    )
    df = df.apply(pd.to_numeric, errors='coerce')
    medias = df.groupby('Tamanho', as_index=False).mean()
    medias['Algoritmo'] = algoritmo
    lista_medias.append(medias)

# Concatena todos os dados em um único DataFrame
df_final = pd.concat(lista_medias, ignore_index=True)
df_final = df_final.sort_values(['Algoritmo', 'Tamanho'])

# Dicionário de escalas: 'linear' (None) e 'log' ('log')
escalas = {'linear': None, 'log': 'log'}

# Gera gráficos para cada métrica e cada escala
def gerar_graficos(df, coluna, escala, tipo_escala):
    plt.figure()
    for alg in df['Algoritmo'].unique():
        sub = df[df['Algoritmo'] == alg]
        x = sub['Tamanho']
        y = sub[coluna]
        # Para evitar log(0) ao plotar Trocas
        if tipo_escala == 'log' and coluna == 'Trocas':
            y = y.replace(0, 1)
        plt.plot(x, y, marker='o', label=alg)

    plt.title(f'{coluna} por Algoritmo ({escala})')
    plt.xlabel('Tamanho do Vetor')
    plt.ylabel(coluna)
    if tipo_escala:
        plt.yscale(tipo_escala)
    plt.grid(True, which='both', linestyle='--', linewidth=0.5)
    plt.legend()
    plt.tight_layout()
    nome_arquivo = f'{coluna.lower()}_{escala}.png'
    plt.savefig(os.path.join(diretorio_saida, nome_arquivo))
    plt.close()

# Loop sobre métricas e escalas
metricas = ['Tempo_ms', 'Trocas', 'Iteracoes']
for escala, tipo in escalas.items():
    for metrica in metricas:
        gerar_graficos(df_final, metrica, escala, tipo)

print(f'Gráficos gerados em: {diretorio_saida}')
