import time
import requests
import os

from datetime import datetime

# Configurações da URL
TARGET_URL_CONSULTA = os.getenv("TARGET_URL_CONSULTA", "http://api-cursos:8080/curso")
URL_POR_ID = os.getenv("URL_CONSULTA_DELETE", "http://api-cursos:8080/curso/{id}")
TOKEN = os.getenv("TOKEN", "")
INTERVAL = int(os.getenv("INTERVAL_POR_ID", "3"))

HEADERS = {
    "Content-Type": "application/json",
    "User-Agent": "synthetic-client",
    "Authorization": f"Bearer {TOKEN}"
}

def consultar_id():
    response = requests.get(TARGET_URL_CONSULTA, headers=HEADERS)
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

def obter_id(id):
    url_completo = URL_POR_ID.format(id=id)
    response = requests.get(url_completo, headers=HEADERS, timeout=5)
    if response.status_code == 200:
        print(response.json())
    else:
        print(f"[{datetime.now()}] Erro nao obter por ID {id}: {response.status_code}")

while True:
    try:
        id_curso = consultar_id()
        if id_curso:
            obter_id(id_curso)
        else:
            print(f"[{datetime.now()}] Nenhum ID encontrado, pulando a consulta por id.")
    except requests.exceptions.RequestException as e:
        print(f"[{datetime.now()}] Erro de conexão: {e}")
    except Exception as e:
        print(f"[{datetime.now()}] Erro inesperado: {e}")
    
    time.sleep(INTERVAL)
