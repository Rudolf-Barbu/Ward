function settingsInitialization()
{
    lightTheme = document.getElementById("light-theme");
    darkTheme = document.getElementById("dark-theme");

    serverName = document.getElementById("server-name");
    port = document.getElementById("port");
    submit = document.getElementById("submit");

    lightTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    darkTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    submit.addEventListener("click", function(event) {sendSettingsRequest(event.target || event.srcElement)});
}

function changeTheme(element)
{
    (String(element.id) == "light-theme") ? html.setAttribute("theme", "light") : html.setAttribute("theme", "dark");

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

function sendSettingsRequest()
{
    xhr.open("POST", "/api/settings");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function()
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
        xhr.send(JSON.stringify(data));
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