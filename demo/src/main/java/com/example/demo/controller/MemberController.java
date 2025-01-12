package com.example.demo.controller;

import com.example.demo.entity.Artist;
import com.example.demo.entity.Member;
import com.example.demo.entity.Setlist;
import com.example.demo.entity.Show;
import com.example.demo.entity.Song;
import com.example.demo.service.ArtistService;
import com.example.demo.service.MemberService;
import com.example.demo.service.SetlistService;
import com.example.demo.service.ShowService;
import com.example.demo.service.SongService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private SetlistService setlistService;

    @Autowired
    private ShowService showService;

    @Autowired
    private MemberService memberService;

     @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

    // Get all members
    @GetMapping
    public String manageMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        model.addAttribute("newMember", new Member());
        return "members"; // Mengarah ke file members.html
    }

    // Add a new member
    @PostMapping("/add")
    public String addMember(@ModelAttribute("newMember") Member member) {
        memberService.addMember(member);
        return "redirect:/members";
    }

    // Update a member
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("updatedMember") Member member) {
        memberService.updateMember(id, member);
        return "redirect:/members";
    }

    // Delete a member
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }

    // Halaman Member Setlist Page
    @GetMapping("/member-setlist-page")
    public String showMemberSetlistPage() {
        return "member-setlist-page"; // Nama file HTML
    }

    // Halaman Member Artist Page
    @GetMapping("/member-artist-page")
    public String showMemberArtistPage() {
        return "member-artist-page"; // Nama file HTML
    }

    // Menampilkan halaman untuk menambah artis
    @GetMapping("/member-add-artist")
    public String getAddArtistPage(Model model) {
        model.addAttribute("newArtist", new Artist());
        return "member-add-artist"; // File HTML untuk member
    }

    // Halaman Member Add Setlist
    @GetMapping("/member-add-setlist")
    public String addSetlistPage(Model model) {
        model.addAttribute("shows", showService.getAllShows());
        model.addAttribute("artists", artistService.getAllArtists());
        return "member-add-setlist"; // Halaman Thymeleaf untuk tambah setlist
    }

    // Halaman Member Add Show
    @GetMapping("/member-add-show")
    public String showMemberAddShowPage() {
        return "member-add-show"; // Nama file HTML
    }

    // Halaman Member Comment
    @GetMapping("/member-comment")
    public String showMemberCommentPage() {
        return "member-comment"; // Nama file HTML
    }

    // Halaman Member Show Page
    @GetMapping("/member-show-page")
    public String showMemberShowPage() {
        return "member-show-page"; // Nama file HTML
    }

    // Menangani POST untuk menambah artis
    @PostMapping("/add-artist")
    public String addArtist(@ModelAttribute("newArtist") Artist artist, Model model) {
        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            model.addAttribute("error", "Nama artis tidak boleh kosong.");
            return "member-add-artist";
        }

        if (artist.getName().length() < 3) {
            model.addAttribute("error", "Nama artis terlalu pendek.");
            return "member-add-artist";
        }

        try {
            artistService.addArtist(artist);
            model.addAttribute("success", "Artis berhasil ditambahkan!");
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menambahkan artis: " + e.getMessage());
        }

        return "member-add-artist";
    }


    // Menangani form penambahan setlist
@PostMapping("/member-add-setlist")
public String addSetlist(@RequestParam("showId") Long showId,
                         @RequestParam("artistId") Long artistId,
                         @RequestParam("setlist") String setlist) {
    try {
        // Ambil show dan artist berdasarkan ID
        Show show = showService.getShowById(showId);
        Artist artist = artistService.getArtistById(artistId);

        // Buat setlist baru
        Setlist newSetlist = new Setlist();
        newSetlist.setShow(show);
        newSetlist.setArtist(artist);

        // Proses lagu dari input setlist
        String[] songTitles = setlist.split(",");
        List<Song> songs = new ArrayList<>();
        for (String title : songTitles) {
            title = title.trim(); // Hilangkan spasi di awal/akhir
            Optional<Song> optionalSong = songService.findByTitleAndArtist(title, artist);
            Song song;
            if (optionalSong.isPresent()) {
                song = optionalSong.get();
            } else {
                // Jika lagu tidak ditemukan, buat baru
                song = new Song();
                song.setTitle(title);
                song.setArtist(artist);
                songService.addSong(song);
            }
            songs.add(song);
        }
        newSetlist.setSongs(songs);

        // Simpan setlist ke database
        setlistService.addSetlist(newSetlist);

    } catch (Exception e) {
        e.printStackTrace();
        return "redirect:/members/member-add-setlist?error";
    }
    return "redirect:/members/member-setlist-page?success";
}

}
