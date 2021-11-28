# countries-api
 Uma api para gerenciar as informações das propriedades dos países (identificador – gerado automaticamente, nome, capital, região, sub-região, área).
 
 Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
 | `GET: http://localhost:8080/country/` | Retorna informações de todos os países na base de dados. |
| `POST: http://localhost:8080/country/` | Utilizado para criar um novo registro na api. |
| `PUT: http://localhost:8080/country/{id}` | Atualiza dados de um registro na api. |
| `DELETE: http://localhost:8080/country/{id}` | Remove um registro do sistema. |


## 🛠 Tecnologias utilizadas
### :coffee: Back end
- [Java](https://www.oracle.com/br/java/)
- JPA / Hibernate
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) 
- [Maven](https://maven.apache.org/)
