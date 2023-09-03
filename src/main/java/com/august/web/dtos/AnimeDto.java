package com.august.web.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AnimeDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
