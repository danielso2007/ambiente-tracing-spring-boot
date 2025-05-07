import time
import requests
import os

from datetime import datetime

# Configurações da URL
URL_CONSULTA = os.getenv("URL_CONSULTA_DELETE", "http://api-cursos:8080/curso")
URL_DELECAO = os.getenv("URL_DELECAO", "http://api-cursos:8080/curso/{id}")
TOKEN = os.getenv("TOKEN", "")
INTERVAL = int(os.getenv("INTERVAL_DELETE", "120"))

HEADERS = {
    "Content-Type": "application/json",
    "User-Agent": "synthetic-client",
    "Authorization": f"Bearer {TOKEN}"
}

# Função para realizar consulta e pegar o ID
def consultar_id():
    response = requests.get(URL_CONSULTA, headers=HEADERS)
    if response.status_code == 200:
        data = response.json()  # Supondo que a resposta seja uma lista de cursos
        if data:
            # Pega o ID do primeiro item da resposta (ou pode ser ajustado conforme sua API)
            return data[0]["id"]
        else:
            print(f"[{datetime.now()}] Nenhum item encontrado.")
            return None
    else:
        print(f"[{datetime.now()}] Erro na consulta: {response.status_code}")
        return None

# Função para deletar usando o ID
def deletar_id(id):
    url_delecao_completo = URL_DELECAO.format(id=id)
    response = requests.delete(url_delecao_completo, headers=HEADERS)
    if response.status_code == 200:
        print(f"[{datetime.now()}] Deleção bem-sucedida para o ID {id}.")
    else:
        print(f"[{datetime.now()}] Erro na deleção para o ID {id}: {response.status_code}")

while True:
    try:
        id_curso = consultar_id()
        if id_curso:
            deletar_id(id_curso)
        else:
            print(f"[{datetime.now()}] Nenhum ID encontrado, pulando a deleção.")
    except requests.exceptions.RequestException as e:
        # Captura qualquer erro de rede (conexão, DNS, timeout, etc.)
        print(f"[{datetime.now()}] Erro de conexão: {e}")
    except Exception as e:
        # Captura erros inesperados
        print(f"[{datetime.now()}] Erro inesperado: {e}")
    
    time.sleep(INTERVAL)
