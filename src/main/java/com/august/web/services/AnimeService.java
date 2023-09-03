package com.august.web.services;

import com.august.web.dtos.AnimeDto;
import com.august.web.models.Anime;

import java.util.List;

public interface AnimeService {
    List<AnimeDto> findAllAnimes();

    Anime saveAnime(Anime anime);
}