# Mutant
Microservicio para la clasificación de ADN

## Comenzando 🚀
Se debe tener instalada una base de datos postgres

Data base = management_mutant
username & password se configura en el archivo configuration.properties del proyecto src/main/resources

## Instalación 🔧

Clonar el proyecto en el ambiente local.

git clone https://github.com/juliomulcue/mutant.git


## Ejecutar endpoint ⚙️

### Post para crear y analizar un adn

POST /mutant/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 5b400e4e-f7b4-3479-b3ca-29db91cea46f

{
	"adn": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

### Get para obtener las estadísticas

GET /stats HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: 2e9f0cad-ce51-4080-1d51-1c8bf9b52f70
