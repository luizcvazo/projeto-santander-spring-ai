package com.calculadora.inteligente;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class CalculadoraController {
n    private final ChatClient chatClient;
    private final ExecutorService executor = Executors.newCachedThreadPool();
n    public CalculadoraController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
n    @GetMapping("/chat")
    public String chat(@RequestParam String mensagem) {
        Callable<String> task = () -> chatClient.prompt().user(mensagem).call().content();
        Future<String> future = executor.submit(task);
        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            return "Serviço de IA não respondeu a tempo (timeout).";
        } catch (Exception e) {
            return "Erro ao chamar serviço de IA: " + e.getMessage();
        }
    }
}