"use strict";

/**
 * Initializes dom objects
 */
function setupInitialization()
{
    setAlertStyle("light");

    let lightTheme = document.getElementById("light-theme");
    let darkTheme = document.getElementById("dark-theme");
    let submit = document.getElementById("submit");

    lightThemeSquare = document.getElementById("light-theme-square");
    darkThemeSquare = document.getElementById("dark-theme-square");

    serverName = document.getElementById("server-name");
    port = document.getElementById("port");

    setupXHR = new XMLHttpRequest();

    lightTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    darkTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    submit.addEventListener("click", function(event) {sendSetupRequest(event.target || event.srcElement)});
}

/**
 * Changes theme
 */
function changeTheme(element)
{
    if (String(element.id) == "light-theme")
    {
        html.setAttribute("theme", "light");
        setAlertStyle("light");

        lightThemeSquare.style.animation = "fade-in-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-out-square 0.5s forwards";

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
        html.setAttribute("theme", "dark");
        setAlertStyle("dark");

        darkThemeSquare.style.visibility = "visible";

        lightThemeSquare.style.animation = "fade-out-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-in-square 0.5s forwards";

        background.setOptions
        ({
            highlightColor: 0x797979,
            midtoneColor: 0xFFFFFF,
            lowlightColor: 0xBCBCBC,
            baseColor: 0xBCBCBC
        });
    }
}

/**
 * Changes alert style
 *
 * @param {*} style name
 */
function setAlertStyle(styleName)
{
    let links = document.getElementsByTagName("link");

    for (let i = 0; i < links.length; i++)
    {
        if ((links[i].getAttribute("title") == "light") || (links[i].getAttribute("title") == "dark"))
        {
            links[i].disabled = (links[i].getAttribute("title") != styleName);
        }
    }
}

/**
 * Sends settings request
 */
function sendSetupRequest()
{
    setupXHR.open("POST", "/api/setup");
    setupXHR.setRequestHeader("Content-Type", "application/json");

    setupXHR.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            if (this.status == 200)
            {
                submit.value = "LOADING";
                window.location = "http://" + window.location.hostname + ":" + port.value;
            }
            else
            {
                let message =
                {
                    text: "Fill the form correctly",
                    type: ("")
                }

                dhtmlx.message(message);
            }
        }
    }

    let data =
    {
        "serverName": serverName.value,
        "theme": html.getAttribute("theme"),
        "port": port.value
    }

        setupXHR.send(JSON.stringify(data));

}