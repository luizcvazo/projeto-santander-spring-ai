package com.calculadora.inteligente;

import jakarta.annotation.PreDestroy;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class CalculadoraController {

    private final ChatClient chatClient;
    private final CalculadoraService calculadoraService;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final long timeoutSeconds;

    public CalculadoraController(ChatClient chatClient,
                                 CalculadoraService calculadoraService,
                                 @Value("${app.ai.timeout-seconds:120}") long timeoutSeconds) {
        this.chatClient = chatClient;
        this.calculadoraService = calculadoraService;
        this.timeoutSeconds = timeoutSeconds;
    }

    @GetMapping("/")
    public String home() {
        return """
                Calculadora Inteligente rodando.
                Use /chat?mensagem=... para conversar com o Ollama.
                Use POST /gastos para cadastrar e GET /gastos?indiceMes=0 para consultar.
                """;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String mensagem) {
        Callable<String> task = () -> chatClient.prompt().user(mensagem).call().content();
        Future<String> future = executor.submit(task);
        try {
            String response = future.get(timeoutSeconds, TimeUnit.SECONDS);
            return response == null || response.isBlank()
                    ? "O modelo nao retornou conteudo."
                    : response;
        } catch (TimeoutException e) {
            future.cancel(true);
            return "Servico de IA nao respondeu a tempo (timeout). Verifique se o Ollama esta rodando e se o modelo foi baixado.";
        } catch (Exception e) {
            return "Erro ao chamar servico de IA: " + e.getMessage();
        }
    }

    @PostMapping("/gastos")
    public String adicionarGasto(@RequestBody GastosRequest request) {
        calculadoraService.adicionarGasto(
                request.nome(),
                request.valor(),
                request.parcela(),
                request.indiceMes()
        );
        return "Gasto adicionado com sucesso.";
    }

    @GetMapping("/gastos")
    public List<Gasto> obterGastos(@RequestParam int indiceMes) {
        return calculadoraService.obterGastosDoMes(indiceMes);
    }

    @PreDestroy
    void shutdownExecutor() {
        executor.shutdownNow();
    }
}
