/**
 * Initializes uptime, labels and chart values
 */
function indexInitialization()
{
    clockSpeed = document.getElementById("clockSpeed");
    procCount = document.getElementById("procCount");
    totalStorage = document.getElementById("totalStorage");
    diskCount = document.getElementById("diskCount");

    days = document.getElementById("uptime-days");
    hours = document.getElementById("uptime-hours");
    minutes = document.getElementById("uptime-minutes");
    seconds = document.getElementById("uptime-seconds");

    usageXHR = new XMLHttpRequest();
    infoXHR = new XMLHttpRequest();

    sendUsageRequest();

    setInterval(function()
    {
        sendInfoRequest();
    }, 1000);
}

/**
 * Sending ajax request to receive usage info
 */
function sendUsageRequest()
{
    usageXHR.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            let response = JSON.parse(this.response);

            labelsTick(response);
            chartTick(response);

            sendUsageRequest();
        }
    }

    usageXHR.open("GET", "/api/usage");
    usageXHR.send();
}

function sendInfoRequest()
{
    infoXHR.onreadystatechange = function()
    {
        if (this.readyState == 4)
        {
            let response = JSON.parse(this.response);

            clockSpeed.innerHTML = response.processorDto.clockSpeed;
            procCount.innerHTML = response.machineDto.procCount;
            totalStorage.innerHTML = response.storageDto.totalStorage;
            diskCount.innerHTML = response.storageDto.diskCount;

            days.innerHTML = response.uptimeDto.days;
            hours.innerHTML = response.uptimeDto.hours;
            minutes.innerHTML = response.uptimeDto.minutes;
            seconds.innerHTML = response.uptimeDto.seconds;
        }
    }

    infoXHR.open("GET", "/api/info");
    infoXHR.send();
}