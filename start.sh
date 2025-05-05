#/usr/bin/bash
RED='\033[1;31m'
BLACK='\033[0;30m'
DARK_GRAY='\033[1;30m'
LIGHT_RED='\033[0;31m'
GREEN='\033[0;32m'
LIGHT_GREEN='\033[1;32m'
BROWN_ORANGE='\033[0;33m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
LIGHT_BLUE='\033[1;34m'
PURPLE='\033[0;35m'
LIGHT_PURPLE='\033[1;35m'
CYAN='\033[0;36m'
LIGHT_CYAN='\033[1;36m'
LIGHT_GRAY='\033[0;37m'
WHITE='\033[1;37m'
NC='\033[0m' # No Color
parar="start"

loading() {
for ((i=0; i<=100; i++)); do
    progress=$(printf "%${i}s" "")
    progress=${progress// /▒}
    echo -ne "${LIGHT_BLUE}\r[$progress]${NC}"
    sleep 0.090
done
}

clear
cd api-cursos
echo -e "${LIGHT_BLUE}Criando Jar da API...${NC}"
mvn -Dmaven.test.skip=true clean package verify -q -Dspring.profiles.active=prod
cd ..
echo -e "${LIGHT_BLUE}Subindo containers...${NC}"
docker compose up -d &
wait $!

echo -e "${LIGHT_BLUE}Aguardando banco de dados subir...${NC}"

loading &
sleep 10
echo

echo -e "${LIGHT_BLUE}Criando Database no Postgresql...${NC}"
docker exec -it database-api-cursos psql -U postgres -c "CREATE DATABASE cursosdb WITH OWNER = postgres ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8' LOCALE_PROVIDER = 'libc' TABLESPACE = pg_default CONNECTION LIMIT = -1 IS_TEMPLATE = False;"
docker exec -it database-api-cursos psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE cursosdb TO postgres;"
docker compose restart