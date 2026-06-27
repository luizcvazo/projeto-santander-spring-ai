package com.calculadora.inteligente;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder,
                                 CalculadoraTools calculadoraTools,
                                 @Value("${app.ai.tools-enabled:false}") boolean toolsEnabled) {
        ChatClient.Builder configuredBuilder = builder.defaultSystem(systemPrompt(toolsEnabled));

        if (toolsEnabled) {
            configuredBuilder.defaultTools(calculadoraTools);
        }

        return configuredBuilder.build();
    }

    private String systemPrompt(boolean toolsEnabled) {
        if (toolsEnabled) {
            return """
                    Voce e uma calculadora de gastos pessoais.
                    Use as ferramentas disponiveis para cadastrar gastos e consultar os gastos de um mes.
                    Quando o usuario mencionar um mes por nome, converta para o indice: Janeiro=0, Fevereiro=1, Marco=2, Abril=3, Maio=4, Junho=5, Julho=6, Agosto=7, Setembro=8, Outubro=9, Novembro=10, Dezembro=11.
                    """;
        }

        return """
                Voce e uma calculadora de gastos pessoais.
                Responda de forma objetiva. O modelo configurado nao tem suporte a chamadas de ferramenta, entao nao confirme cadastro real de gastos.
                """;
    }
}
