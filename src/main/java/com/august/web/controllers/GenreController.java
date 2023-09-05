package com.august.web.controllers;

import com.august.web.dtos.GenreDto;
import com.august.web.models.Genre;
import com.august.web.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GenreController {
    private GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }
    @GetMapping("/genres")
    public String genresPage(Model model){
        List<GenreDto> genres = genreService.findAllGenres();
        model.addAttribute("genres", genres);
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "genres";
    }
    @PostMapping("/genres/new")
    public String saveGenre(@Valid @ModelAttribute("genre") GenreDto genreDto, BindingResult result, Model model){
        if (result.hasErrors()){
            List<GenreDto> genres = genreService.findAllGenres();
            model.addAttribute("genres", genres);
            model.addAttribute("genre", genreDto);
            return "genres";
        }
        genreService.saveGenre(genreDto);
        return "redirect:/genres";
    }
    @GetMapping("/genres/{genreId}/delete")
    public String deleteGenre(@PathVariable Long genreId){
        genreService.delete(genreId);
        return "redirect:/genres";
    }

}
