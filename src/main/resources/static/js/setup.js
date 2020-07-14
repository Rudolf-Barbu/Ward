/**
 * Initializes dom objects
 */
function setupInitialization()
{
    lightTheme = document.getElementById("light-theme");
    darkTheme = document.getElementById("dark-theme");

    lightThemeSquare = document.getElementById("light-theme-square");
    darkThemeSquare = document.getElementById("dark-theme-square");

    serverName = document.getElementById("server-name");
    port = document.getElementById("port");
    submit = document.getElementById("submit");

    setupXHR = new XMLHttpRequest();

    lightTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    darkTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    submit.addEventListener("click", function(event) {sendSettingsRequest(event.target || event.srcElement)});
}

/**
 * Changes theme
 */
function changeTheme(element)
{
    (String(element.id) == "light-theme") ? html.setAttribute("theme", "light") : html.setAttribute("theme", "dark");

    if (String(element.id) == "light-theme")
    {
        lightThemeSquare.style.animation = "fade-in-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-out-square 0.5s forwards";
    }
    else
    {
        lightThemeSquare.style.animation = "fade-out-square 0.5s forwards";
        darkThemeSquare.style.animation = "fade-in-square 0.5s forwards";
    }

    if (String(element.id) == "light-theme")
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

/**
 * Sends settings request
 */
function sendSettingsRequest()
{
    setupXHR.open("POST", "/api/settings");
    setupXHR.setRequestHeader("Content-Type", "application/json");

    setupXHR.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            switch (this.status)
            {
                case 200:
                {
                    submit.value = "LOADING";
                    window.location = "http://" + window.location.hostname + ":" + port.value;
                    break;
                }
                case 405:
                {
                    let message =
                    {
                        text:"Fill the form correctly",
                        type:("")
                    }

                    dhtmlx.message(message);
                    break;
                }
            }
        }
    }

    data =
    {
        "serverName": serverName.value,
        "theme": html.getAttribute("theme"),
        "port": port.value
    }

    if (port.value != 4000)
    {
        setupXHR.send(JSON.stringify(data));
    }
    else
    {
        let message =
        {
            text:"Choose other port",
            type:("")
        }

        dhtmlx.message(message);
    }
}