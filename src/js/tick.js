let xhr;

function tickInitialization()
{
    xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            let response = JSON.parse(this.response);
            labelTick(response);
            chartTick(response);
            sendAjaxReqest();
        }
    }

    sendAjaxReqest();
    setInterval(function()
    {
        uptimeTick();
    }, 1000);
}

function sendAjaxReqest()
{
    xhr.open("GET", "./../php/usage.php");
    xhr.send();
}