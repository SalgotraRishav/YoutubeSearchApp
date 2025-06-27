package com.youtubeapp.youtubesearch.service;

import com.youtubeapp.youtubesearch.dto.VideoSummaryDto;
import com.youtubeapp.youtubesearch.model.YouTubeResponse;
import com.youtubeapp.youtubesearch.repository.YouTubeApiRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList; // ADD THIS IMPORT
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YouTubeService {

    private final YouTubeApiRepository youtubeApiRepository;

    public YouTubeService(YouTubeApiRepository youtubeApiRepository) {
        this.youtubeApiRepository = youtubeApiRepository;
    }

    /**
     * Searches YouTube for videos and transforms the results
     * into a simplified list of VideoSummaryDto objects.
     * This method contains the business logic and data transformation.
     *
     * @param query The search term.
     * @return A Mono emitting a List of VideoSummaryDto.
     */
    public Mono<List<VideoSummaryDto>> searchVideos(String query) {
        return youtubeApiRepository.searchVideos(query)
                .map(youtubeResponse -> {
                    List<VideoSummaryDto> videoSummaries; // Declare the variable to hold the result

                    // Use if-else to assign to the 'videoSummaries' variable
                    if (youtubeResponse == null || youtubeResponse.getItems() == null) {
                        videoSummaries = Collections.<VideoSummaryDto>emptyList();
                    } else {
                        videoSummaries = youtubeResponse.getItems().stream()
                                .map(item -> {
                                    String title = null;
                                    String description = null;
                                    String imageUrl = null;
                                    String publishTime = null;

                                    if (item.getSnippet() != null) {
                                        title = item.getSnippet().getTitle();
                                        description = item.getSnippet().getDescription();
                                        publishTime = item.getSnippet().getPublishedAt();

                                        if (item.getSnippet().getThumbnails() != null) {
                                            if (item.getSnippet().getThumbnails().getHigh() != null) {
                                                imageUrl = item.getSnippet().getThumbnails().getHigh().getUrl();
                                            } else if (item.getSnippet().getThumbnails().getMedium() != null) {
                                                imageUrl = item.getSnippet().getThumbnails().getMedium().getUrl();
                                            } else if (item.getSnippet().getThumbnails().getDefaultT() != null) {
                                                imageUrl = item.getSnippet().getThumbnails().getDefaultT().getUrl();
                                            }
                                        }
                                    }
                                    return new VideoSummaryDto(publishTime, title, description, imageUrl);
                                })
                                .collect(Collectors.toCollection(ArrayList::new));
                    }
                    return videoSummaries;
                })
                .defaultIfEmpty(Collections.<VideoSummaryDto>emptyList());
    }
}