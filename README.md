<h2>Descrição Geral</h2>
<p>
  Este projeto consiste em um sistema de gerenciamento de contatos desenvolvido com Spring Boot e PostgreSQL, utilizando Flyway para o controle de versão do banco de dados. A aplicação oferece uma API RESTful completa, permitindo que os usuários realizem operações CRUD (Criar, Ler, Atualizar e Deletar) de maneira eficiente. Todos os dados dos contatos são armazenados de forma segura e estruturada em um banco de dados relacional PostgreSQL, garantindo a persistência e integridade das informações.
</p>

<h2>Tecnologias Utilizadas</h2>
<ul>
  <li>Spring Boot 3.3.3: Framework para construir aplicações Java.</li>
  <li>PostgreSQL: Banco de dados relacional.</li>
  <li>Flyway: Ferramenta de controle de versão para o banco de dados.</li>
  <li>Maven: Gerenciador de dependências.</li>
  <li>Java 17: Linguagem de programação.</li>
  <li>Lombok: Simplificação do código Java.</li>
</ul>

<h2>GitHub</h2>
<p>
  O projeto está versionado e disponível no GitHub, proporcionando um gerenciamento eficaz do código-fonte e facilitando a colaboração entre desenvolvedores de forma eficiente. O repositório Git pode ser acessado através do seguinte link: 
  <a href="https://github.com/projetoticeub/sistematizacao-orientacao-objeto-II">GitHub Repositório</a>.
</p>

<h2>Passos para Configuração do Projeto</h2>

<h3>1. Clonando o Projeto</h3>
<p>
  Faça o clone do projeto em sua máquina local:
</p>
<pre>
git clone https://github.com/projetoticeub/sistematizacao-orientacao-objeto-II.git
</pre>

<h3>2. Configurando o Banco de Dados</h3>
<p>
  Crie o banco de dados <strong>Contact</strong> no PostgreSQL usando o seguinte comando:
</p>
<pre>
CREATE DATABASE contact;
</pre>

<h3>3. Configuração do application.properties</h3>
<p>
  No arquivo <code>src/main/resources/application.properties</code>, as configurações de conexão com o banco de dados estão definidas da seguinte maneira:
</p>
<pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/contact
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
</pre>
<p>
  Certifique-se de que o <code>username</code> e <code>password</code> estejam corretamente configurados de acordo com suas credenciais do PostgreSQL para garantir o acesso adequado ao banco de dados.
</p>

<h3>4. Controle de Versão com Flyway</h3>
<p>
  O Flyway gerencia automaticamente o versionamento do banco de dados, assegurando que todas as migrações sejam aplicadas de forma controlada e consistente.
  Ao iniciar a aplicação pela primeira vez, o Flyway executa os scripts de migração localizados no diretório <code>src/main/resources/db/migration</code>,
  garantindo que o banco de dados esteja sempre atualizado com a estrutura exigida pelo sistema.
</p>

<p>
  Para um funcionamento adequado, é essencial seguir um padrão de nomenclatura para os arquivos de migração, pois o Flyway organiza e executa esses scripts em ordem de versão.
  O formato recomendado é o seguinte:
</p>

<ul>
  <li><strong>V{versão}__{descrição}.sql</strong></li>
  <li><strong>V:</strong> Prefixo que indica a versão.</li>
  <li><strong>{versão}:</strong> Número sequencial da versão, que pode ser um inteiro ou decimal (por exemplo, V1, V1.1, V2).</li>
  <li><strong>__:</strong> Dois underscores que separam o número da versão do nome descritivo.</li>
  <li><strong>{descrição}:</strong> Uma descrição clara e breve da migração, usando underscores (_) em vez de espaços (por exemplo, <code>V1__create_contact_table.sql</code>).</li>
</ul>

<p>Exemplo:</p>
<ul>
  <li><strong>V1__create_contact_table.sql</strong>: Cria a tabela de contatos.</li>
  <li><strong>V2__add_email_to_contact.sql</strong>: Adiciona o campo de e-mail à tabela de contatos.</li>
</ul>

<p>
  Seguindo essa convenção, o Flyway garante a aplicação correta das migrações na ordem adequada, facilitando o controle e a evolução do banco de dados ao longo do desenvolvimento do projeto.
</p>

<pre>
CREATE TABLE contact (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  email VARCHAR(255)
);
</pre>

<p>Após a criação da tabela, os seguintes registros de exemplo serão inseridos para fins de teste:</p>
<pre>
INSERT INTO contact (name, phone_number, email) VALUES
('William Jackson', '41967890123', 'williamjackson51@example.com'),
('Ranon Campos', '61991919191', 'ranon@email.com'),
('Alana Melo', '61991919192', 'email@email.com'),
('Zoe Young', '71966456787', 'zoeyoung111@example.com'),
('Lucas Silva', '31985739284', 'lucas.silva@example.com'),
('Mariana Costa', '21987654321', 'marianac@example.com'),
('Ana Oliveira', '31987654322', 'ana.oliveira@example.com'),
('Felipe Souza', '21912345678', 'felipe.souza@example.com'),
('Clara Santos', '41991234567', 'clara.santos@example.com'),
('Pedro Almeida', '51998765432', 'pedro.almeida@example.com'),
('Julia Rocha', '11987654321', 'julia.rocha@example.com'),
('Thiago Lima', '61987654322', 'thiago.lima@example.com'),
('Beatriz Martins', '71987654323', 'beatriz.martins@example.com'),
('Gabriel Pereira', '21912345679', 'gabriel.pereira@example.com'),
('Fernanda Ramos', '31998765432', 'fernanda.ramos@example.com');
</pre>

<h3>Endpoints Disponíveis</h3>

<h4>1. Criar um Novo Contato</h4>
<ul>
  <li><strong>Método:</strong> POST</li>
  <li><strong>URL:</strong> <code>http://localhost:8080/contacts</code></li>
  <li><strong>Corpo da Requisição:</strong></li>
</ul>

<pre>
{
  "name": "Jane Doe",
  "phoneNumber": "00000000000",
  "email": "jane@example.com"
}
</pre>

<p><strong>Resposta:</strong> 201 Created (Retorna o objeto do contato criado)</p>

<h4>2. Listar Todos os Contatos</h4>
<ul>
  <li><strong>Método:</strong> GET</li>
  <li><strong>URL:</strong> <code>http://localhost:8080/contacts</code></li>
  <li><strong>Filtros Disponíveis:</strong></li>
  <ul>
    <li><strong>Filtro por nome:</strong> <code>http://localhost:8080/contacts?name={name}</code></li>
    <li>Exemplo: <code>http://localhost:8080/contacts?name=jane-doe</code></li>
    <li><strong>Filtro por número de telefone:</strong> <code>http://localhost:8080/contacts?phoneNumber={phoneNumber}</code></li>
    <li>Exemplo: <code>http://localhost:8080/contacts?phoneNumber=00000000000</code></li>
  </ul>
</ul>
<p><strong>Resposta:</strong> 200 OK (Retorna uma lista paginada de contatos)</p>

<h4>3. Listar Contato por ID</h4>
<ul>
  <li><strong>Método:</strong> GET</li>
  <li><strong>URL:</strong> <code>http://localhost:8080/contacts/{id}</code></li>
  <li>Exemplo: <code>http://localhost:8080/contacts/1</code></li>
</ul>
<p><strong>Resposta:</strong> 200 OK (Retorna o contato com o ID especificado)</p>

<h4>4. Atualizar um Contato</h4>
<ul>
  <li><strong>Método:</strong> PUT</li>
  <li><strong>URL:</strong> <code>http://localhost:8080/contacts/{id}</code></li>
  <li>Exemplo: <code>http://localhost:8080/contacts/1</code></li>
  <li><strong>Corpo da Requisição:</strong></li>
</ul>

<pre>
{
  "name": "John Doe",
  "phoneNumber": "00000000001"
}
</pre>

<p><strong>Resposta:</strong> 200 OK (Retorna o objeto do contato atualizado)</p>

<h4>5. Deletar um Contato</h4>
<ul>
  <li><strong>Método:</strong> DELETE</li>
  <li><strong>URL:</strong> <code>http://localhost:8080/contacts/{id}</code></li>
  <li>Exemplo: <code>http://localhost:8080/contacts/1</code></li>
</ul>
<p><strong>Resposta:</strong> 200 OK (Contato excluído com sucesso)</p>

<h3>Estrutura de Código - Classes e Funcionalidades</h3>

<h4>• Contact (Entidade)</h4>
<p>Classe responsável por mapear e representar os atributos de um contato no banco de dados, garantindo a persistência dos dados de forma estruturada e consistente.</p>
<ul>
  <li><strong>Campos:</strong> id, name, phoneNumber, email.</li>
  <li><strong>Métodos principais:</strong> update(ContactUpdateDTO c) para atualização dos dados do contato.</li>
</ul>

<h4>• ContactRepository (Repositório)</h4>
<p>Interface que extende JpaRepository, proporcionando métodos de manipulação dos dados de contatos.</p>

<h4>• ContactService (Serviço)</h4>
<p>Classe de serviço que contém a lógica de negócio.</p>
<ul>
  <li><strong>Principais Métodos:</strong></li>
  <ul>
    <li>saveContact(ContactSaveDTO contactDTO): Salva um contato.</li>
    <li>findContactById(Long id): Busca um contato pelo ID.</li>
    <li>listAllContactByPage(Pageable pageable): Lista todos os contatos paginados.</li>
    <li>listByParam(String name, String phoneNumber, Pageable pageable): Lista contatos por parâmetros de pesquisa.</li>
    <li>updateContact(ContactUpdateDTO contactDTO, Long id): Atualiza um contato.</li>
    <li>delete(Long id): Exclui um contato.</li>
  </ul>
</ul>

<h4>• ContactSpecification</h4>
<p>Classe responsável por criar filtros dinâmicos (Specifications) para pesquisa de contatos, permitindo filtrar por nome e número de telefone.</p>

<h4>• DTOs</h4>
<ul>
  <li>ContactListDTO: Estrutura usada para listar contatos.</li>
  <li>ContactSaveDTO: Estrutura para salvar um novo contato.</li>
  <li>ContactUpdateDTO: Estrutura para atualizar um contato.</li>
</ul>

<h4>• GlobalException (Exceção)</h4>
<p>Classe de exceção personalizada que trata erros globais da aplicação.</p>

<h4>• WebConfig (Configuração)</h4>
<p>Classe de configuração que habilita o CORS para permitir requisições do frontend.</p>

<h4>• ContactController (Controller REST)</h4>
<p>Controlador responsável por lidar com as requisições HTTP e chamar os métodos de serviço.</p>

<h3>Testando a API</h3>
<p>Recomenda-se utilizar uma ferramenta como Postman ou Insomnia para testar a API RESTful.</p>
<ul>
  <li><strong>1. Criar Contato:</strong> Faça um POST com o corpo conforme especificado.</li>
  <li><strong>2. Listar Contatos:</strong> Execute GET no endpoint <code>/contacts</code>.</li>
  <li><strong>3. Atualizar Contato:</strong> Faça um PUT para <code>/contacts/{id}</code>.</li>
  <li><strong>4. Deletar Contato:</strong> Execute um DELETE no endpoint <code>/contacts/{id}</code>.</li>
</ul>


