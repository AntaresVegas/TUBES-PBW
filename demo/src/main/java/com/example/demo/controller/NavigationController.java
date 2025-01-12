package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.ArtistSetlistDTO;
import com.example.demo.entity.Festival;
import com.example.demo.entity.Setlist;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.service.ArtistService;
import com.example.demo.service.FestivalService;
import com.example.demo.service.SetlistService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {

    @Autowired
    private SetlistService setlistService;

    @Autowired
    private ArtistService artistService;

     @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private FestivalService festivalService;

    @GetMapping("/setlist")
    public String getSetlistsPage(Model model) {
        // Fetch all setlists from the database
        List<Setlist> setlists = setlistService.getAllSetlists();

        // Add setlists to the model
        model.addAttribute("setlists", setlists);

        // Return the name of the Thymeleaf template
        return "setlist"; // This corresponds to the setlist.html template
    }

    @GetMapping("/artists")
    public String getArtistsPage(Model model) {
        List<ArtistSetlistDTO> artists = artistRepository.findArtistsWithSetlistCount();
        model.addAttribute("artists", artists);
        return "top-artists"; // Mengarah ke template top-artists.html
    }

    @GetMapping("/festivals")
    public String getFestivalsPage(Model model) {
        // Fetch all festivals from the service
        List<Festival> festivals = festivalService.getAllFestivals();
        // Add festivals to the model
        model.addAttribute("festivals", festivals);
        // Return the name of the Thymeleaf template
        return "festival";
    }

    @GetMapping("/signin-required")
    public String signinRequiredPage() {
        return "signin-required"; // Mengarah ke festival.html di folder templates/page
    }
}
