package com.example.demo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String serverUrl;

    @Value("${spring.elasticsearch.password}")
    private String apiKey;

    private final ElasticsearchTransportConfig transportConfig;

    @Bean
    public ElasticsearchClient client() {
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        return new ElasticsearchClient(transportConfig.customRestClientTransport(restClient, new JacksonJsonpMapper(objectMapper)));
    }
}
