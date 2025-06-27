package com.youtubeapp.youtubesearch.controller;

import com.youtubeapp.youtubesearch.dto.VideoSummaryDto;
import com.youtubeapp.youtubesearch.service.YouTubeService;

import java.util.List; // Ensure List is imported

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/youtube")
public class YouTubeController {

    private final YouTubeService youtubeService;

    public YouTubeController(YouTubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/testConnection")
    public String test() {
        return "Application working fine.";
    }

    /**
     * Handles GET requests to /api/Youtube.
     * This endpoint searches YouTube videos based on a provided query parameter
     * and returns simplified video summaries.
     *
     * @param q The search term passed as a request parameter (e.g., /search?q=Angular%20tutorial).
     * @return A ResponseEntity containing a List of VideoSummaryDto, wrapped in a Mono.
     */
    @GetMapping("/search")
    public Mono<ResponseEntity<List<VideoSummaryDto>>> search(@RequestParam String q) {
        return youtubeService.searchVideos(q)
                .map(videoList -> ResponseEntity.ok(videoList))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}