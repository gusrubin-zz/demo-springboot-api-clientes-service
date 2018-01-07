# api-clientes-service
Micro serviço que disponibilizar CRUD do cadastro de clientes em um banco de dados PostgreSql via API REST.

O objetivo deste projeto é demonstrar algumas habilidades técnicas de desenvolvimento de micro serviços REST utilizando o framework Springboot com a linguagem de progamação Java 8, gerenciador de pacotes Maven, a ferramenta Flyway para automação de migrações de banco de dados, a construção de testes unitários utilizando o framework JUnit e Mockito, construção de caderno de testes incluindo pequenas automações em Javascript na ferramenta Postman, entre outras tecnologias envolvidas neste processo. O padrão de projeto utilizado no desenvolvimento do micro serviço foi o Domain Driven Design (DDD) que separa os blocos de implementação em contextos de interface de usuário, aplicação, domínio e infra-estrutura.

Este repositório contém:

- as dependências e configurações para o Maven montar o pacote do projeto (arquivo pom.xml);
- o código fonte da aplicação, disponível no diretório src/main/java;
- os recursos utilizados pelo framework Springboot, disponíveis no diretório src/main/resources
- o script de criação dos objetos da base de dados (sequences e tabelas), disponível no diretório src/main/resources/db/migration. Observação: Esse script é executado automaticamente pelo Flyway quando a aplicação está sendo executada pela primeira vez em uma base de dados vazia.
- o arquivo de collection do Postman, disponível em src/test/resources

O ambiente para execução da aplicação é Java 1.8 (ou superior) e banco de dados PostgreSql 10 (ou superior).
