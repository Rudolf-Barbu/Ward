function themeInitialization()
{
    lightTheme = document.getElementById("light-theme");
    darkTheme = document.getElementById("dark-theme");

    lightTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
    darkTheme.addEventListener("click", function(event) {changeTheme(event.target || event.srcElement)});
}

function changeTheme(element)
{
    if (String(element.id) == "light-theme")
    {
        html.setAttribute("theme", "light");
    }
    else
    {
        html.setAttribute("theme", "dark");
    }
}