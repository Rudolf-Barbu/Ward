/**
 * Initializes uptime, labels and chart values
 */
function indexInitialization()
{
    clockSpeed = document.getElementById("clockSpeed");
    procCount = document.getElementById("procCount");
    totalStorage = document.getElementById("totalStorage");
    diskCount = document.getElementById("diskCount");

    currentPage = 1;
    firstControl = document.getElementById("first-control");
    secondControl = document.getElementById("second-control");

    locationPage = document.getElementById("location-page");
    logoPage = document.getElementById("logo-page");
    contactsPage = document.getElementById("contacts-page");

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
        setCloudAnimation(currentPage);
    }
    else if ((String(element.id) == "second-control") && (currentPage < 2))
    {
        currentPage += 1;
        setCloudAnimation(currentPage);
    }

    setPageVisibility(currentPage);
    setControlOpacity(currentPage);
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
            logoPage.style.visibility = "visible";
            contactsPage.style.visibility = "hidden";
            break;
        }
        case 2:
        {
            logoPage.style.visibility = "hidden";
            contactsPage.style.visibility = "visible";
            break;
        }
    }
}

/**
 * Animates clouds
 *
 * @param {*} new page
 */
function setCloudAnimation(newSquareScale)
{
    switch (newSquareScale)
    {
        case 1:
        {
            cloudLeft.style.animation = "fade-in-cloud-left 0.3s forwards";
            cloudRight.style.animation = "fade-in-cloud-right 0.3s forwards";
            break;
        }
        case 2:
        {
            cloudLeft.style.animation = "fade-out-cloud-left 0.3s forwards";
            cloudRight.style.animation = "fade-out-cloud-right 0.3s forwards";
            break;
        }
    }
}

/**
 * Changes opacity of control
 *
 * @param {*} new page
 */
function setControlOpacity(newSquareScale)
{
    switch (newSquareScale)
    {
        case 1:
        {
            firstControl.style.opacity = "0.5";
            secondControl.style.opacity = "1";
            break;
        }
        case 2:
        {
            firstControl.style.opacity = "1";
            secondControl.style.opacity = "0.5";
            break;
        }
    }
}