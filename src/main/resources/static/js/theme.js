function themeInitialization()
{
    if (document.cookie == "")
    {
        document.cookie = "light"
    }

    html.setAttribute("theme", document.cookie);
}

function changeTheme()
{
    document.cookie = (html.getAttribute("theme") == "light") ? "dark" : "light";
    html.setAttribute("theme", document.cookie);

    changeBackgroundTheme();
    changeLabelsTheme();
    changeChartTheme(chart);
}