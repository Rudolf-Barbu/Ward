/**
 * Initializes uptime, labels and chart values
 */
function tickInitialization()
{
    xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            let response = JSON.parse(this.response);
            labelsTick(response);
            chartTick(response);
            sendAjaxRequest();
        }
    }
    sendAjaxRequest();

    setInterval(() =>
    {
        uptimeTick();
    }, 1000);
}

/**
 * Sending ajax request to receive usage info
 */
function sendAjaxRequest()
{
    xhr.open("GET", "/api/usage");
    xhr.send();
}