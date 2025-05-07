FROM python:3.9-slim
WORKDIR /app
COPY synthetic-client-cadastrar.py .
COPY synthetic-client-delete.py .
COPY synthetic-client-health.py .
COPY synthetic-client-consulta.py .
COPY synthetic-client-por-id.py .
RUN pip install requests
CMD ["bash", "-c", "python synthetic-client-cadastrar.py & python synthetic-client-delete.py & python synthetic-client-health.py & python synthetic-client-consulta.py & python synthetic-client-por-id.py & wait"]