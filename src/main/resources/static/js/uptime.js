let days;
let hours;
let minutes;
let seconds;

function uptimeInitialization()
{
    days = document.getElementById("uptime-days");
    hours = document.getElementById("uptime-hours");
    minutes = document.getElementById("uptime-minutes");
    seconds = document.getElementById("uptime-seconds");
}

function uptimeTick()
{
    increment(seconds);
    if (parseInt(seconds.innerHTML) > 59)
    {
        increment(minutes);
        seconds.innerHTML = "00";
        if (parseInt(minutes.innerHTML) > 59)
        {
            increment(hours);
            minutes.innerHTML = "00";
            if (parseInt(hours.innerHTML) > 23)
            {
                increment(days);
                hours.innerHTML = "00";
            }
        }
    }
}

function increment(domObject)
{
    if (parseInt(domObject.innerHTML) < 9)
    {
        domObject.innerHTML = "0" + (parseInt(domObject.innerHTML) + 1);
    }
    else
    {
        domObject.innerHTML = parseInt(domObject.innerHTML) + 1;
    }
}