
document.addEventListener("DOMContentLoaded", function () {
    console.log("ICICI Bank Analysis website loaded successfully.");

    const cards = document.querySelectorAll(".dashboard-card");

    cards.forEach(function (card) {
        card.addEventListener("mouseenter", function () {
            card.style.transform = "translateY(-3px)";
            card.style.transition = "transform 0.2s ease";
        });

        card.addEventListener("mouseleave", function () {
            card.style.transform = "translateY(0)";
        });
    });
});
