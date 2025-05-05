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

clear

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data ' {
    "numeroMatricula": "1323",
    "numeroCurso": "123215",
    "nomeCurso": "Matemática Aplicada",
    "categoriaCurso": "exatas",
    "preRequisito": "Álgebra Linear",
    "nomeProfessor": "Maria Oliveira",
    "periodoCurso": "2"
  }'
  
curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data ' {
    "numeroMatricula": "1324",
    "numeroCurso": "123216",
    "nomeCurso": "Introdução à Programação",
    "categoriaCurso": "tecnologia",
    "preRequisito": "nenhum",
    "nomeProfessor": "Carlos Silva",
    "periodoCurso": "1"
  }'
  
curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1325",
    "numeroCurso": "123217",
    "nomeCurso": "História do Brasil",
    "categoriaCurso": "humanas",
    "preRequisito": "História Geral",
    "nomeProfessor": "Ana Souza",
    "periodoCurso": "3"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1326",
    "numeroCurso": "123218",
    "nomeCurso": "Física 1",
    "categoriaCurso": "exatas",
    "preRequisito": "Matemática Básica",
    "nomeProfessor": "João Mendes",
    "periodoCurso": "1"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1327",
    "numeroCurso": "123219",
    "nomeCurso": "Química Orgânica",
    "categoriaCurso": "biológicas",
    "preRequisito": "Química Geral",
    "nomeProfessor": "Luciana Prado",
    "periodoCurso": "4"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1328",
    "numeroCurso": "123220",
    "nomeCurso": "Engenharia de Software",
    "categoriaCurso": "tecnologia",
    "preRequisito": "Programação Orientada a Objetos",
    "nomeProfessor": "Pedro Fernandes",
    "periodoCurso": "5"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1329",
    "numeroCurso": "123221",
    "nomeCurso": "Direito Constitucional",
    "categoriaCurso": "jurídica",
    "preRequisito": "Introdução ao Direito",
    "nomeProfessor": "Cláudia Lima",
    "periodoCurso": "6"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1330",
    "numeroCurso": "123222",
    "nomeCurso": "Administração Financeira",
    "categoriaCurso": "negócios",
    "preRequisito": "Contabilidade Básica",
    "nomeProfessor": "Rafael Costa",
    "periodoCurso": "3"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1331",
    "numeroCurso": "123223",
    "nomeCurso": "Psicologia Organizacional",
    "categoriaCurso": "humanas",
    "preRequisito": "Psicologia Geral",
    "nomeProfessor": "Fernanda Rocha",
    "periodoCurso": "4"
  }'

curl --location 'http://localhost:80/curso' \
--header 'Content-Type: application/json' \
--data '{
    "numeroMatricula": "1332",
    "numeroCurso": "123224",
    "nomeCurso": "Estatística Aplicada",
    "categoriaCurso": "exatas",
    "preRequisito": "Probabilidade",
    "nomeProfessor": "Henrique Matos",
    "periodoCurso": "2"
  }'