package com.youtubeapp.youtubesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // This annotation will correctly generate the constructor
public class VideoSummaryDto {
    private String publishTime;
    private String title;
    private String description;
    private String imageUrl;
}