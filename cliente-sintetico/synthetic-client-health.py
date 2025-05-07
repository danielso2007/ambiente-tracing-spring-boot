import time
import requests
import os
from datetime import datetime

URL = os.getenv("TARGET_URL_HEALTH", "http://api-cursos:8080/actuator/health")
TOKEN = os.getenv("TOKEN", "")
INTERVAL = int(os.getenv("INTERVAL_HEALTH", "30"))  # segundos

HEADERS = {
    "User-Agent": "synthetic-client",
    "Authorization": f"Bearer {TOKEN}"
}

while True:
    try:
        response = requests.get(URL, headers=HEADERS, timeout=5)
        print(f"[{time.ctime()}] Status: {response.status_code}")
    except requests.exceptions.RequestException as e:
        # Captura qualquer erro de rede (conexão, DNS, timeout, etc.)
        print(f"[{datetime.now()}] Erro de conexão: {e}")
    except Exception as e:
        print(f"[{time.ctime()}] Erro: {e}")
    time.sleep(INTERVAL)
