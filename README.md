# countries-api
 Uma api para gerenciar as informações das propriedades dos países (identificador – gerado automaticamente, nome, capital, região, sub-região, área).
 
 Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
 | `GET: http://localhost:8080/country/` | Retorna informações de todos os países na base de dados. |
| `POST: http://localhost:8080/country/` | Utilizado para criar um novo registro na api. |
| `PUT: http://localhost:8080/country/{id}` | Atualiza dados de um registro na api. |
| `DELETE: http://localhost:8080/country/{id}` | Remove um registro do sistema. |
| `GET: http://localhost:8080/country/id` | Retorna informações de todos os países ordenado pelo id. |
| `GET: http://localhost:8080/country/nome` | Retorna informações de todos os países ordenado pelo nome do pais. |

`GET: http://localhost:8080/country/`

   +Body
   
      {
         "id":1,"
         nome":"Mozambique",
         "capital":"Maputo",
         "regiao":"Africa",
         "subRegiao":"sul da africa",
         "area":"Sul"
      }
      
## 🛠 Tecnologias utilizadas
### :coffee: Back end
- [Java](https://www.oracle.com/br/java/)
- JPA / Hibernate
- PostgreSql
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) 
- [Maven](https://maven.apache.org/)

## 🚀 Como executar o projeto

### 🎲 Back end

Pré-requisitos: Java 8 e Base de Dados Postgresql

```bash
#criar uma base de dados com o nome countries
   modificar as credencias em src/main/resources/application.properties
   username e password
   
# clonar repositório
git clone https://github.com/BelmiroMungoi/countries-api.git

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```
