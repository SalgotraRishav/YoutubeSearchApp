package com.youtubeapp.youtubesearch.repository.impl;

import com.youtubeapp.youtubesearch.model.YouTubeResponse;
import com.youtubeapp.youtubesearch.repository.YouTubeApiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class YouTubeApiRepositoryImpl implements YouTubeApiRepository {

    private final WebClient webClient;

    @Value("${youtube.api.key}")
    private String youtubeApiKey;

    public YouTubeApiRepositoryImpl(WebClient youtubeWebClient) {
        this.webClient = youtubeWebClient;
    }

    @Override
    public Mono<YouTubeResponse> searchVideos(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("search")
                        .queryParam("part", "snippet")
                        .queryParam("q", query)
                        .queryParam("type", "video")
                        .queryParam("maxResults", 10)
                        .queryParam("key", youtubeApiKey)
                        .build())
                .retrieve()
                .bodyToMono(YouTubeResponse.class);
    }
}