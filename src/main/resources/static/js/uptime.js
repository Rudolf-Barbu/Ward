/**
 * Used to display current days of uptime
 */
let days;

/**
 * Used to display current hours of uptime
 */
let hours;

/**
 * Used to display current minutes of uptime
 */
let minutes;

/**
 * Used to display current seconds of uptime
 */
let seconds;

/**
 * Determines uptime label elements
 */
function uptimeInitialization()
{
    days = document.getElementById("uptime-days");
    hours = document.getElementById("uptime-hours");
    minutes = document.getElementById("uptime-minutes");
    seconds = document.getElementById("uptime-seconds");
}

/**
 * Increments uptime labels, once in second
 */
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

/**
 * Formats uptime label
 *
 * @param {*} domObject uptime label
 */
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