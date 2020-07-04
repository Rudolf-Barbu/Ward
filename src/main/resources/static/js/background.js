/**
 * Initializes dynamic background
 */
function backgroundInitialization()
{
    background = VANTA.FOG({el: "#background", blurFactor: 0.40, zoom: 1.50});

    if (html.getAttribute("theme") == "light")
    {
        background.setOptions
        ({
            highlightColor: 0xcac7e8,
            midtoneColor: 0xbbb7ed,
            lowlightColor: 0xe4e3ef,
            baseColor: 0xe4e3ef
        });
    }
    else
    {
        background.setOptions
        ({
            highlightColor: 0x797979,
            midtoneColor: 0xffffff,
            lowlightColor: 0xbcbcbc,
            baseColor: 0xbcbcbc
        });
    }
}