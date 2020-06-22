/**
 * Initializes dynamic background
 */
function backgroundInitialization()
{
    html = document.getElementById("html");

    VANTA.FOG
    ({
        el: "#background",
        highlightColor: (html.getAttribute("theme") == "light") ? 0xcac7e8 : 0x797979,
        midtoneColor: (html.getAttribute("theme") == "light") ? 0xbbb7ed : 0xffffff,
        lowlightColor: (html.getAttribute("theme") == "light") ? 0xe4e3ef : 0xbcbcbc,
        baseColor: (html.getAttribute("theme") == "light") ? 0xe4e3ef : 0xbcbcbc,
        blurFactor: 0.40,
        zoom: 1.50
    });
}