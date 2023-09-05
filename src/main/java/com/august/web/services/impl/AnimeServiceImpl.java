package com.august.web.services.impl;

import com.august.web.dtos.AnimeDto;
import com.august.web.models.Anime;
import com.august.web.repository.AnimeRepository;
import com.august.web.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AnimeServiceImpl implements AnimeService {
    private AnimeRepository animeRepository;
    @Autowired
    public AnimeServiceImpl(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    public List<AnimeDto> findAllAnimes() {
        List<Anime> animes = animeRepository.findAll();
        return animes.stream().map((anime) -> mapToAnimeDto(anime)).collect(Collectors.toList());
    }
    public Anime saveAnime(AnimeDto animeDto){
        Anime anime = mapToAnime(animeDto);
        return animeRepository.save(anime);
    }

    @Override
    public AnimeDto findAnimeById(Long id) {
        Anime anime = animeRepository.findById(id).get();
        return mapToAnimeDto(anime);
    }

    @Override
    public void updateAnime(AnimeDto animeDto) {
        Anime anime = mapToAnime(animeDto);
        animeRepository.save(anime);
    }

    @Override
    public void delete(Long animeId) {
        animeRepository.deleteById(animeId);
    }

    public Anime mapToAnime(AnimeDto animeDto){
        Anime anime = Anime.builder()
                .id(animeDto.getId())
                .title(animeDto.getTitle())
                .description(animeDto.getDescription())
                .imageUrl(animeDto.getImageUrl())
                .createdOn(animeDto.getCreatedOn())
                .updatedOn(animeDto.getUpdatedOn())
                .build();
        return anime;
    }
    private AnimeDto mapToAnimeDto(Anime anime){
        AnimeDto animeDto = AnimeDto.builder()
                .id(anime.getId())
                .title(anime.getTitle())
                .description(anime.getDescription())
                .imageUrl(anime.getImageUrl())
                .createdOn(anime.getCreatedOn())
                .updatedOn(anime.getUpdatedOn())
                .build();
        return animeDto;
    }

}
