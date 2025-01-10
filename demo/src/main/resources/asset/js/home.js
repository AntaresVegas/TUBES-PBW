let slideIndex = 0;

function showSlides() {
    const slides = document.querySelectorAll('.slide');
    slides.forEach((slide, index) => {
        slide.style.display = 'none'; // Sembunyikan semua slide
    });

    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1; // Reset ke slide pertama
    }

    slides[slideIndex - 1].style.display = 'block'; // Tampilkan slide aktif
    setTimeout(showSlides, 9000); // Ganti slide setiap 3 detik
}

document.addEventListener('DOMContentLoaded', showSlides); // Jalankan saat DOM siap
