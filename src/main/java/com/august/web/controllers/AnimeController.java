package com.august.web.controllers;

import com.august.web.dtos.AnimeDto;
import com.august.web.models.Anime;
import com.august.web.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String saveAnime(@ModelAttribute("anime") Anime anime){
        animeService.saveAnime(anime);
        System.out.println(anime.getDescription() + "  " + anime.getTitle());
        return "redirect:/animes";
    }
}
