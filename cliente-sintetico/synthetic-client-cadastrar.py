import time
import requests
import os
import random
from datetime import datetime

URL = os.getenv("TARGET_URL_CURSO_CADASTRAR", "http://api-cursos:8080/curso")
TOKEN = os.getenv("TOKEN", "")
INTERVAL = int(os.getenv("INTERVAL_CURSO_CADASTRAR", "10"))

HEADERS = {
    "Content-Type": "application/json",
    "User-Agent": "synthetic-client",
    "Authorization": f"Bearer {TOKEN}"
}

# Função para montar o JSON com dados simulados
def montar_payload():
    return {
        "numeroMatricula": str(random.randint(1000, 9999)),  # Gerando um número aleatório para matrícula
        "numeroCurso": str(random.randint(100000, 999999)),  # Gerando um número aleatório para o curso
        "nomeCurso": random.choice(["Matemática Aplicada", "Física Teórica", "Química Orgânica"]),
        "categoriaCurso": random.choice(["exatas", "humanas", "biológicas"]),
        "preRequisito": random.choice(["Álgebra Linear", "Cálculo 1", "Geometria Analítica"]),
        "nomeProfessor": random.choice(["Maria Oliveira", "Carlos Silva", "Ana Souza"]),
        "periodoCurso": random.choice(["1", "2", "3", "4", "5", "6"])
    }

while True:
    payload = montar_payload()
    try:
        response = requests.post(URL, json=payload, headers=HEADERS, timeout=5)
        print(f"[{datetime.now()}] POST {URL} → {response.status_code} | Payload: {payload}")
    except requests.exceptions.RequestException as e:
        # Captura qualquer erro de rede (conexão, DNS, timeout, etc.)
        print(f"[{datetime.now()}] Erro de conexão: {e}")
    except Exception as e:
        print(f"[{datetime.now()}] Erro: {e}")
    time.sleep(INTERVAL)
