/**
 * Initializes uptime, labels and chart values
 */
function indexInitialization()
{
    clockSpeed = document.getElementById("clockSpeed");
    procCount = document.getElementById("procCount");
    totalStorage = document.getElementById("totalStorage");
    diskCount = document.getElementById("diskCount");

    currentPage = 2;
    firstControl = document.getElementById("first-control");
    secondControl = document.getElementById("second-control");

    locationPage = document.getElementById("location-page");
    logoPage = document.getElementById("logo-page");
    infoPage = document.getElementById("info-page");

    cloudLeft = document.getElementById("cloud-left");
    cloudRight = document.getElementById("cloud-right");

    firstUptimeSquare = document.getElementById("first-uptime-square");
    secondUptimeSquare = document.getElementById("second-uptime-square");
    thirdUptimeSquare = document.getElementById("third-uptime-square");

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

    firstControl.addEventListener("click", function(event) {changePage(event.target || event.srcElement)});
    secondControl.addEventListener("click", function(event) {changePage(event.target || event.srcElement)});
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

/**
 * Changes page
 *
 * @param {*} control element
 */
function changePage(element)
{
    if ((String(element.id) == "first-control") && (currentPage > 1))
    {
        currentPage -= 1;
    }
    else if ((String(element.id) == "second-control") && (currentPage < 3))
    {
        currentPage += 1;
    }

    setPageVisibility(currentPage);
    setSquareScale(currentPage);

    cloudLeft.style.animation = (currentPage == 2) ? "fade-in-cloud-left 0.5s forwards" : "fade-out-cloud-left 0.5s forwards";
    cloudRight.style.animation = (currentPage == 2) ? "fade-in-cloud-right 0.5s forwards" : "fade-out-cloud-right 0.5s forwards";
}

/**
 * Changes page visibility
 *
 * @param {*} new page
 */
function setPageVisibility(newPage)
{
    switch (newPage)
    {
        case 1:
        {
            locationPage.style.visibility = "visible";
            logoPage.style.visibility = "hidden";
            infoPage.style.visibility = "hidden";
            break;
        }
        case 2:
        {
            locationPage.style.visibility = "hidden";
            logoPage.style.visibility = "visible";
            infoPage.style.visibility = "hidden";
            break;
        }
        case 3:
        {
            locationPage.style.visibility = "hidden";
            logoPage.style.visibility = "hidden";
            infoPage.style.visibility = "visible";
            break;
        }
    }
}

/**
 * Changes square scale
 *
 * @param {*} new square scale
 */
function setSquareScale(newSquareScale)
{
    switch (newSquareScale)
    {
        case 1:
        {
            firstUptimeSquare.style.transform = "scale(1.3)";
            secondUptimeSquare.style.transform = "scale(1.0)";
            thirdUptimeSquare.style.transform = "scale(1.0)";
            break;
        }
        case 2:
        {
            firstUptimeSquare.style.transform = "scale(1.0)";
            secondUptimeSquare.style.transform = "scale(1.3)";
            thirdUptimeSquare.style.transform = "scale(1.0)";
            break;
        }
        case 3:
        {
            firstUptimeSquare.style.transform = "scale(1.0)";
            secondUptimeSquare.style.transform = "scale(1.0)";
            thirdUptimeSquare.style.transform = "scale(1.3)";
            break;
        }
    }
}