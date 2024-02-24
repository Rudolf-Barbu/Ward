"use strict";

/**
 * Initializes dynamic background
 */
function backgroundInitialization()
{
if (html.getAttribute("enableFog") == "true")
    {
        background = VANTA.FOG({el: "#background", blurFactor: 0.40, zoom: 1.50});
        if (html.getAttribute("theme") == "light")
        {
            background.setOptions
            ({
                highlightColor: 0xCAC7E8,
                midtoneColor: 0xBBB7ED,
                lowlightColor: 0xE4E3EF,
                baseColor: 0xE4E3EF
            });
        }
        else
        {
            background.setOptions
            ({
                highlightColor: 0x797979,
                midtoneColor: 0xFFFFFF,
                lowlightColor: 0xBCBCBC,
                baseColor: 0xBCBCBC
            });
        }
    }
    else
    {
        if (background){
            background.destroy();
        }
        if (html.getAttribute("backgroundColor") == "default" )
        {
            document.body.style.backgroundColor = html.getAttribute("theme") == "light" ? "#e5e5e5" : "#303030"
        }
        else
        {
            document.body.style.backgroundColor = html.getAttribute("backgroundColor")
        }
    }
}