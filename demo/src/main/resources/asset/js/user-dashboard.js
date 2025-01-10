document.getElementById("add-artist-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const artistName = document.getElementById("artist-name").value;
    alert(`Artist "${artistName}" has been added!`);
    this.reset();
});

document.getElementById("add-show-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const artist = document.getElementById("artist-select").value;
    const concertName = document.getElementById("concert-name").value;
    const venue = document.getElementById("venue").value;
    const date = document.getElementById("date").value;
    alert(`Show "${concertName}" for artist "${artist}" has been added at "${venue}" on ${date}.`);
    this.reset();
});

document.getElementById("add-setlist-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const show = document.getElementById("show-select").value;
    const setlist = document.getElementById("setlist").value;
    alert(`Setlist for "${show}" has been added: ${setlist}`);
    this.reset();
});

document.getElementById("correction-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const show = document.getElementById("show-select-correction").value;
    const comment = document.getElementById("comment").value;
    alert(`Correction for "${show}" has been submitted: ${comment}`);
    this.reset();
});
