package com.august.web.services.impl;

import com.august.web.dtos.GenreDto;
import com.august.web.models.Genre;
import com.august.web.repository.GenreRepository;
import com.august.web.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map((genre -> mapToGenreDto(genre))).collect(Collectors.toList());
    }

    @Override
    public Genre saveGenre(GenreDto genreDto) {
        Genre genre = mapToGenre(genreDto);
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long genreId) {
        genreRepository.deleteById(genreId);
    }


    private Genre mapToGenre(GenreDto genreDto) {
        Genre genre = Genre.builder()
                .id(genreDto.getId())
                .name(genreDto.getName())
                .build();
        return genre;
    }


    private GenreDto mapToGenreDto(Genre genre){
        GenreDto genreDto = GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
        return genreDto;
    }
}
