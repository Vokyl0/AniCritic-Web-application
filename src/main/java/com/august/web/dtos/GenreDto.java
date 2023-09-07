package com.august.web.dtos;

import com.august.web.models.Anime;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class GenreDto {
    private Long id;
    @NotEmpty(message = "Genre name should not be empty")
    private String name;
    private Set<Anime> animes;
}
