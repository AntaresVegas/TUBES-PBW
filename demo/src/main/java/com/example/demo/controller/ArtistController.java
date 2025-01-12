package com.example.demo.controller;

import com.example.demo.dto.ArtistSetlistDTO;
import com.example.demo.entity.Artist;
import com.example.demo.repository.ArtistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    // Menampilkan halaman daftar artis
    @GetMapping("/admin-artist")
    public String getArtistsPage(Model model) {
        List<ArtistSetlistDTO> artists = artistRepository.findArtistsWithSetlistCount();
        model.addAttribute("artists", artists);
        return "admin-artist"; // Halaman Thymeleaf untuk daftar artis
    }

    // Menampilkan halaman tambah artis
    @GetMapping("/admin-add-artist")
    public String getAddArtistPage() {
        return "admin-add-artist"; // Halaman Thymeleaf untuk tambah artis
    }

    // Menangani permintaan POST untuk menambahkan artis baru
    @PostMapping("/admin-add-artist")
    public String addArtist(@RequestParam("artistName") String artistName) {
        // Buat dan simpan entitas Artist baru
        Artist artist = new Artist();
        artist.setName(artistName);
        artistRepository.save(artist);
        return "redirect:/admin/admin-artist"; // Redirect ke halaman daftar artis
    }

    // Menampilkan halaman edit artis
    @GetMapping("/admin-edit-artist/{id}")
    public String getEditArtistPage(@PathVariable("id") Long id, Model model) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID: " + id));
        model.addAttribute("artist", artist);
        return "admin-edit-artist"; // Halaman Thymeleaf untuk edit artis
    }

    // Menangani permintaan POST untuk mengupdate artis
    @PostMapping("/admin-edit-artist/{id}")
    public String editArtist(@PathVariable("id") Long id, @RequestParam("artistName") String artistName) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID: " + id));
        artist.setName(artistName);
        artistRepository.save(artist);
        return "redirect:/admin/admin-artist"; // Redirect ke halaman daftar artis
    }

    // Menangani penghapusan artis
    @GetMapping("/admin-delete-artist/{id}")
    public String deleteArtist(@PathVariable("id") Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist ID: " + id));
        artistRepository.delete(artist);
        return "redirect:/admin/admin-artist"; // Redirect ke halaman daftar artis
    }
}
