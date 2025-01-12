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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SetlistService setlistService;

    @Autowired
    private ShowService showService;

    @Autowired
    private ArtistService artistService;

    // Halaman utama admin (Dashboard)
    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("message", "Selamat datang di Dashboard Admin!");
        return "admin-dashboard"; // File: admin-dashboard.html
    }

    // Halaman Manage Members
    @GetMapping("/members")
    public String getAllMembers(Model model) {
        try {
            List<Member> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            model.addAttribute("newMember", new Member());
        } catch (Exception e) {
            model.addAttribute("error", "Gagal memuat data anggota: " + e.getMessage());
        }
        return "admin-manage-user"; // File: admin-manage-user.html
    }

    // Halaman Add Show
    @GetMapping("/admin-add-show")
    public String addShowPage() {
        return "admin-add-show"; // File: admin-add-show.html
    }

    /// Menampilkan halaman tambah setlist
    @GetMapping("/admin-add-setlist")
    public String addSetlistPage(Model model) {
        List<Show> shows = showService.getAllShows();
        List<Artist> artists = artistService.getAllArtists();

        if (shows == null || shows.isEmpty() || artists == null || artists.isEmpty()) {
            model.addAttribute("error", "Data shows atau artists kosong.");
        } else {
            model.addAttribute("shows", shows);
            model.addAttribute("artists", artists);
        }

        return "admin-add-setlist";
    }



    // Halaman Festival Management
    @GetMapping("/admin-festivals")
    public String manageFestivals() {
        return "admin-festival"; // File: admin-festival.html
    }

    // Halaman Generate Reports
    @GetMapping("/admin-generate-report")
    public String generateReportPage() {
        return "admin-generate-report"; // File: admin-generate-report.html
    }

    // Halaman Function Management (Optional)
    @GetMapping("/admin-member-function")
    public String memberFunctionPage() {
        return "admin-member-function"; // File: admin-member-function.html
    }

    // Halaman Setlist Management
    @GetMapping("/admin-setlist")
    public String manageSetlist(Model model) {
        List<Setlist> setlists = setlistService.getAllSetlists();

        // Add setlists to the model
        model.addAttribute("setlists", setlists);
        return "admin-setlist"; // File: admin-setlist.html
    }

    // Halaman Edit Member
    @GetMapping("/admin-edit-member")
    public String editMemberPage(Model model) {
        model.addAttribute("member", new Member());
        return "admin-edit-member"; // File: admin-edit-member.html
    }

    // Menambahkan anggota baru
    @PostMapping("/members/add")
    public String addMember(@ModelAttribute("newMember") Member member, Model model) {
        try {
            memberService.addMember(member);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menambahkan anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }

    // Memperbarui data anggota
    @PostMapping("/members/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("updatedMember") Member member, Model model) {
        try {
            memberService.updateMember(id, member);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal memperbarui anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }

    // Menghapus anggota berdasarkan ID
    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id, Model model) {
        try {
            memberService.deleteMember(id);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menghapus anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }

    // Menangani permintaan POST untuk menambahkan setlist baru
    @PostMapping("/admin-add-setlist")
    public String addSetlist(@RequestParam("showId") Long showId,
                            @RequestParam("artistId") Long artistId,
                            @RequestParam("setlist") String setlist,
                            Model model) {
        try {
            // Ambil entitas Show dan Artist berdasarkan ID
            Show show = showService.getShowById(showId);
            Artist artist = artistService.getArtistById(artistId);

            // Parse lagu-lagu dari setlist
            List<Song> songs = setlistService.parseSongs(setlist, artist);

            // Buat entitas baru untuk Setlist
            Setlist newSetlist = new Setlist();
            newSetlist.setShow(show);
            newSetlist.setArtist(artist);
            newSetlist.setSongs(songs);

            // Simpan setlist ke database
            setlistService.addSetlist(newSetlist);

            model.addAttribute("success", "Setlist berhasil ditambahkan!");
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menambahkan setlist: " + e.getMessage());
        }

        return "redirect:/admin/admin-setlist";
    }



}
