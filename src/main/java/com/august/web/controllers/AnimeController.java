package com.august.web.controllers;

import com.august.web.dtos.AnimeDto;
import com.august.web.dtos.GenreDto;
import com.august.web.models.Anime;
import com.august.web.models.Genre;
import com.august.web.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnimeController {
    private AnimeService animeService;
    @Autowired
    public AnimeController(AnimeService animeService){
         this.animeService = animeService;
    }

    @GetMapping("/animes")
    public String listAnimes(Model model){
        List<AnimeDto> animes = animeService.findAllAnimes();
        model.addAttribute("animes", animes);
        return "animes-list";
    }
    @GetMapping("/animes/new")
    public String createAnimeForm(Model model){
        Anime anime = new Anime();
        model.addAttribute("anime", anime);
        return "animes-create";
    }
    @PostMapping("/animes/new")
    public String saveAnime(@Valid @ModelAttribute("anime") AnimeDto animeDto, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("anime", animeDto);
            return "animes-create";
        }
        animeService.saveAnime(animeDto);
        return "redirect:/animes";
    }
    @GetMapping("/animes/{animeId}/edit")
    public String editAnimeForm(@PathVariable("animeId") Long animeId, Model model){
        AnimeDto anime = animeService.findAnimeById(animeId);
        model.addAttribute("anime",anime);
        return "anime-edit";
    }
    @GetMapping("/animes/{animeId}")
    public String animeDetails(@PathVariable("animeId") Long animeId, Model model){
        AnimeDto anime = animeService.findAnimeById(animeId);
        model.addAttribute("anime", anime);
        return "anime-details";
    }
    @PostMapping("/animes/{animeId}/edit")
    public String updateAnime(@PathVariable("animeId") Long animeId,
                              @Valid @ModelAttribute("anime") AnimeDto animeDto,
                              BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("anime", animeDto);
            return "anime-edit";
        }
        animeDto.setId(animeId);
        animeService.updateAnime(animeDto);
        return "redirect:/animes";
    }
    @GetMapping("/animes/{animeId}/delete")
    public String deleteAnime(@PathVariable("animeId") Long animeId){
        animeService.delete(animeId);
        return "redirect:/animes";
    }
    @GetMapping("/animes/genre/{genreName}")
    public String listAnimesByGenre(@PathVariable("genreName") String genreName, Model model){
        List<AnimeDto> animes = animeService.findAnimesByGenre(genreName);
        model.addAttribute("animes", animes);
        return "animes-list";
    }
}
