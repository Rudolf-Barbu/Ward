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
                    var message =
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

    xhr.send(JSON.stringify(data));
}