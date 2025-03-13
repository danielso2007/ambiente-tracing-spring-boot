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

echo -e "${BROWN_ORANGE}Baixando opentelemetry-javaagent.jar...${NC}"
curl -o opentelemetry-javaagent.jar https://repo1.maven.org/maven2/io/opentelemetry/javaagent/opentelemetry-javaagent/2.13.3/opentelemetry-javaagent-2.13.3.jar