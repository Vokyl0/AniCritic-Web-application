package com.august.web.services;

import com.august.web.dtos.GenreDto;
import com.august.web.models.Genre;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GenreService {
    List<GenreDto> findAllGenres();

    Genre saveGenre(GenreDto genreDto);

    void delete(Long genreId);
    public Genre getGenreById(Long genreId);
}
