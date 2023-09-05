package com.august.web.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
public class AnimeDto {
    private Long id;
    @NotEmpty(message = "Anime title should not be empty")
    private String title;
    @NotEmpty(message = "Anime description should not be empty")
    private String description;
    @NotEmpty(message = "Image url should not be empty")
    private String imageUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
