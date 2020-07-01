/**
 * Initializes uptime, labels and chart values
 */
function tickInitialization()
{
    xhr.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            let response = JSON.parse(this.response);
            labelsTick(response);
            chartTick(response);
            sendUsageRequest();
        }
    }
    sendUsageRequest();

    setInterval(function()
    {
        uptimeTick();
    }, 1000);
}

/**
 * Sending ajax request to receive usage info
 */
function sendUsageRequest()
{
    xhr.open("GET", "/api/usage");
    xhr.send();
}