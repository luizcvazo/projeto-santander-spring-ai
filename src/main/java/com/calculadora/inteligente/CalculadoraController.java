package com.calculadora.inteligente;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    private final ChatClient chatClient;

    public CalculadoraController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String mensagem) {
        return chatClient.prompt()
                .user(mensagem)
                .call()
                .content();
    }
}