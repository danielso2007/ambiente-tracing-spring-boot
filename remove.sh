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
clear

echo -e "${BROWN_ORANGE}Removendo docker...${NC}"
yes | docker compose rm -s -f -v &
wait $!
docker ps -a
# Pergunta ao usuário
echo -e "${LIGHT_RED}Deseja remover os volumes? (sim/não)${NC}"
read resposta
# Verifica a resposta e executa a ação correspondente
if [[ "$resposta" == *"sim"* || "$resposta" == *"s"* ]]; then
    echo -e "${BROWN_ORANGE}Removendo volumes e network...${NC}"
    # Remove a pasta recursivamente
    docker volume rm ambiente-tracing-spring-boot_database-api-cursos-conf ambiente-tracing-spring-boot_database-api-cursos-data ambiente-tracing-spring-boot_database-api-cursos-logs ambiente-tracing-spring-boot_grafana-cursos-storage ambiente-tracing-spring-boot_loki-cursos-data ambiente-tracing-spring-boot_pgadmin4-cursos-conf ambiente-tracing-spring-boot_pgadmin4-cursos-serverdefinitions ambiente-tracing-spring-boot_pgadmin4-cursos-sessiondata ambiente-tracing-spring-boot_prometheus-cursos-data ambiente-tracing-spring-boot_redis-cursos-data &
    wait $!
    yes | docker volume prune &
    wait $!
    docker image rm api-cursos:latest
    yes | docker image prune &
    wait $!
    docker volume ls
    docker network rm ambiente-tracing-spring-boot_monit ambiente-tracing-spring-boot_api ambiente-tracing-spring-boot_data ambiente-tracing-spring-boot_data
    docker network ls
fi