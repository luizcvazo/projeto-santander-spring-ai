# Calculadora Inteligente

Projeto Spring Boot que gerencia gastos mensais e integra com um backend de IA via Spring AI.

Resumo
- Permite cadastrar gastos por mês e parcelamento (beans e serviços implementados em Java).
- Expõe um endpoint /chat que encaminha mensagens a um ChatClient (configurado via Spring AI).

Como executar a aplicação (local)
1. Requisitos: JDK 26 (ou versão compatível) e Maven wrapper. Porta 8080 livre.
2. Build: 
   - Abra o terminal na raiz do projeto
   - .\mvnw.cmd -DskipTests package
3. Rodar:
   - java -jar target\inteligente-0.0.1-SNAPSHOT.jar
   - ou: .\mvnw.cmd spring-boot:run

Observações sobre o backend de IA
- O projeto tenta conectar a um backend de IA em http://localhost:11434 (ex.: Ollama). Sem esse serviço, o endpoint /chat retorna uma mensagem de timeout.
- Para testes rápidos, rode um mock HTTP escutando na porta 11434 ou instale o Ollama (https://ollama.ai) e execute um modelo local.

Melhoria implementada
- Injeção do ChatClient como bean (ChatConfig) para configuração centralizada.
- Timeout de 5 segundos no endpoint /chat para evitar bloqueio quando o backend de IA está indisponível.
- Implementação de beans Function (CalculadoraTools) para expor funcionalidades de adicionar/consultar gastos.

Tecnologias usadas
- Java (25/26), Spring Boot, Spring AI, Maven.

Como testar o fluxo principal
- Endpoint de chat: GET http://localhost:8080/chat?mensagem=teste
  - Sem backend IA: resposta prevista: "Serviço de IA não respondeu a tempo (timeout)."
  - Com backend IA (Ollama) rodando: retorna a resposta do modelo.

O que aprendi
- Integração com Spring AI e injeção de beans de cliente de chat.
- Como adicionar timeouts e tratamento de falhas para dependências externas.
- Organização de serviços e ferramentas usando Spring Boot.

Exemplos
- Requisição curl local:
  curl "http://localhost:8080/chat?mensagem=olá"

Se quiser, incluo prints, exemplos de requisições mais completos, ou instruções para rodar um mock HTTP no porto 11434 para testes automáticos.
>>>>>>> 8243075 (Prepare project for course submission: add README and notes)
