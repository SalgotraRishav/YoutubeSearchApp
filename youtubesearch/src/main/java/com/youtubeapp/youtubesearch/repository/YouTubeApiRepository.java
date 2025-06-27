package com.youtubeapp.youtubesearch.repository;

import com.youtubeapp.youtubesearch.model.YouTubeResponse;
import reactor.core.publisher.Mono;

public interface YouTubeApiRepository {
    Mono<YouTubeResponse> searchVideos(String query);
}