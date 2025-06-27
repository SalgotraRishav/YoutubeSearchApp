package com.youtubeapp.youtubesearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Data // This annotation should now correctly generate all necessary getters.
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor
public class YouTubeResponse {

    private List<Item> items; // Lombok will generate getItems()
    private String kind;      // Lombok will generate getKind()
    private String etag;      // Lombok will generate getEtag()
    private String nextPageToken; // Lombok will generate getNextPageToken()
    private String regionCode;    // Lombok will generate getRegionCode()
    private PageInfo pageInfo;    // Lombok will generate getPageInfo()

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfo {
        private Integer totalResults;   // Lombok will generate getTotalResults()
        private Integer resultsPerPage; // Lombok will generate getResultsPerPage()
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String kind;    // Lombok will generate getKind()
        private String etag;    // Lombok will generate getEtag()
        private Id id;          // Lombok will generate getId()
        private Snippet snippet; // Lombok will generate getSnippet()
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Id {
        private String kind;       // Lombok will generate getKind()
        private String videoId;    // Lombok will generate getVideoId()
        private String channelId;  // Lombok will generate getChannelId()
        private String playlistId; // Lombok will generate getPlaylistId()
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Snippet {
        private String publishedAt;      // Lombok will generate getPublishedAt()
        private String channelId;        // Lombok will generate getChannelId()
        private String title;            // Lombok will generate getTitle()
        private String description;      // Lombok will generate getDescription()
        private Thumbnails thumbnails;   // Lombok will generate getThumbnails()
        private String channelTitle;     // Lombok will generate getChannelTitle()
        private String liveBroadcastContent; // Lombok will generate getLiveBroadcastContent()
        private String publishTime;      // Lombok will generate getPublishTime()

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Thumbnails {
            @JsonProperty("default")
            private Thumbnail defaultT; // Lombok will generate getDefaultT()
            private Thumbnail medium;   // Lombok will generate getMedium()
            private Thumbnail high;     // Lombok will generate getHigh()
        }

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Thumbnail {
            private String url;    // Lombok will generate getUrl()
            private Integer width; // Lombok will generate getWidth()
            private Integer height; // Lombok will generate getHeight()
        }
    }
}