# 🚀 Cadastro de Clientes

Sistema de cadastro de clientes desenvolvido em Java 8, com JSF para a interface, JPA para o gerenciamento de dados e H2 como banco de dados.
## 📋 Funcionalidades Implementadas

- **Cadastro de clientes**: Permite adicionar, editar e excluir clientes.
- **Visualização de clientes**: Exibe a lista de todos os clientes cadastrados.
- **Pesquisa de clientes**: Permite buscar clientes por nome.


## 🛠️ Pré-requisitos

1. **☕ Java 8** 
2. **🐙 Git** 
3. **🐳 Docker** (opcional)

---

## 🐳 Executando com Docker

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/Jaoow/cadastro-clientes.git
   cd cadastro-clientes
   ```

2. **Build da imagem Docker**:

   ```bash
   docker build -t cadastro-clientes-image .
   ```

3. **Rodando o contêiner**:

   ```bash
   docker run --name cadastro-clientes-container -p 8080:8080 cadastro-clientes-image
   ```

4. **Acessar a aplicação**:

   Abra o navegador e acesse:

   ```
   http://localhost:8080
   ```

---

## 🖥️ Executando sem Docker


### Passo 1: Baixar o Apache Tomcat

1. Acesse o site oficial do [Apache Tomcat](https://tomcat.apache.org/download-90.cgi).
2. Baixe a versão **9.x** (compatível com o Java 8).
3. Extraia o arquivo baixado para uma pasta de sua preferência.
4. Defina uma variável de ambiente chamada `CATALINA_HOME`, apontando para o diretório onde você extraiu o Tomcat.

### Passo 2: Build do Projeto

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/Jaoow/cadastro-clientes.git
   cd cadastro-clientes
   ```

2. **Compilar o projeto e gerar o WAR**:

   ```bash
   ./mvnw clean package -DskipTests
   ```

3. **Copiar o WAR para o Tomcat**:

   Após o comando de build, um arquivo `cadastro-clientes.war` será gerado na pasta `target`. Copie-o para o diretório `webapps` do Tomcat.
   - No **Linux/MacOS**:
   
      ```bash
      cp target/cadastro-clientes.war $CATALINA_HOME/webapps/ROOT.war
      ```
   - No **Windows**:
   
      ```bash
      copy target\cadastro-clientes.war %CATALINA_HOME%\webapps\ROOT.war
      ```

### Passo 3: Iniciar o Tomcat

1. Navegue até o diretório `bin` do Tomcat e inicie o servidor:

    - No **Linux/MacOS**:

      ```bash
      $CATALINA_HOME/bin/startup.sh
      ```

    - No **Windows**:

      ```bash
      %CATALINA_HOME%\bin\startup.bat
      ```

2. **Acessar a aplicação**:

   Abra o navegador e acesse:

   ```
   http://localhost:8080
   ```

3. Para parar o Tomcat, você pode usar:

    - No **Linux/MacOS**:

      ```bash
      $CATALINA_HOME/bin/shutdown.sh
      ```

    - No **Windows**:

      ```bash
      %CATALINA_HOME%\bin\shutdown.bat
      ```

---

## 🌐 Como Acessar

A aplicação estará disponível no endereço:

```
http://localhost:8080
```


