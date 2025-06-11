# API_Estoq – API de Gerenciamento de Fornecedores


# Tecnologias Utilizadas  
Java 17  
Spring Boot  
Spring Data JPA  
PostgreSQL  
Maven  
RESTful API  


# Banco de dados  
Tenha un banco de dados desse jeito e configura a conexão  
dele em dependecias no projeto pra funcionar localmente  

CREATE TABLE suppliers (  
    id BIGSERIAL PRIMARY KEY,  
    name VARCHAR(255),  
    contact_person VARCHAR(255),  
    phone VARCHAR(50),  
    email VARCHAR(255),  
    address VARCHAR(500),  
    registration_date TIMESTAMP  
);  
# Json  
O Arquivo Json tambem deve estar nesse formato  

{  
  "name": "Fornecedor Z",  
  "contactPerson": "Zé Ninguem",  
  "phone": "(99) 99999-9999",  
  "email": "ze.ninguem@example.com",  
  "address": "Rua do Zé, 123",  
  "registrationDate": "2025-06-10T18:43:37.156703"  
}  
