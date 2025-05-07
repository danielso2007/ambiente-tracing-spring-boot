import requests
import os
from datetime import datetime
import time

URL = os.getenv("TARGET_URL_CONSULTA", "http://api-cursos:8080/curso")
TOKEN = os.getenv("TOKEN", "")
INTERVAL = int(os.getenv("INTERVAL_CONSULTA", "5"))

HEADERS = {
    "Accept": "application/json",
    "Authorization": f"Bearer {TOKEN}",
    "User-Agent": "synthetic-client"
}

PARAMS = {
    "page": 0,
    "size": 20,
    "sort": "dataInscricao,ASC"
}

while True:
    try:
        response = requests.get(URL, headers=HEADERS, params=PARAMS, timeout=5)
        print(f"[{datetime.now()}] Status: {response.status_code}")
        print(response.json())
    except requests.exceptions.RequestException as e:
        # Captura qualquer erro de rede (conexão, DNS, timeout, etc.)
        print(f"[{datetime.now()}] Erro de conexão: {e}")
    except Exception as e:
        # Captura erros inesperados
        print(f"[{datetime.now()}] Erro inesperado: {e}")
    
    time.sleep(INTERVAL)

