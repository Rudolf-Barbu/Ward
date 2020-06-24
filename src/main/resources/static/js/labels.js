/**
 * Initializes labels span arrays
 */
function labelsInitialization()
{
    processorLabelsArray =
    [
        document.getElementById("processor-hundreds"),
        document.getElementById("processor-tens"),
        document.getElementById("processor-ones")
    ];
    ramLabelsArray =
    [
        document.getElementById("ram-hundreds"),
        document.getElementById("ram-tens"),
        document.getElementById("ram-ones")
    ];
    storageLabelsArray =
    [
        document.getElementById("storage-hundreds"),
        document.getElementById("storage-tens"),
        document.getElementById("storage-ones")
    ];
}

/**
 * Updates labels values
 *
 * @param {*} usageData usage value
 */
function labelsTick(usageData)
{
    let usageDataArray = Object.values(usageData);

    for (let i = 0; i < usageDataArray.length; i++)
    {
        switch (i)
        {
            case 0:
            {
                formatLabels(processorLabelsArray, usageDataArray[i]);
                break;
            }
            case 1:
            {
                formatLabels(ramLabelsArray, usageDataArray[i]);
                break;
            }
            case 2:
            {
                formatLabels(storageLabelsArray, usageDataArray[i]);
                break;
            }
        }
    }
}

/**
 * Distributes values to spans and changes their color
 *
 * @param {*} labelArray array with domObjects
 * @param {*} usageData usage value to distribute
 */
function formatLabels(labelArray, usageData)
{
    usageDataString = String(usageData);

    switch (usageDataString.length)
    {
        case 1:
        {
            labelArray[0].innerHTML = 0;
            labelArray[0].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
            labelArray[1].innerHTML = 0;
            labelArray[1].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
            labelArray[2].innerHTML = usageDataString[0];
            labelArray[2].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
            break;
        }
        case 2:
        {
            labelArray[0].innerHTML = 0;
            labelArray[0].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
            labelArray[1].innerHTML = usageDataString[0];
            labelArray[1].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
            labelArray[2].innerHTML = usageDataString[1];
            labelArray[2].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
            break;
        }
        default:
        {
            labelArray[0].innerHTML = usageDataString[0];
            labelArray[0].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
            labelArray[1].innerHTML = usageDataString[1];
            labelArray[1].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
            labelArray[2].innerHTML = usageDataString[2];
            labelArray[2].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
        }
    }
}

/**
 * Changes labels theme
 */
function changeLabelsTheme()
{
    let labelArray = new Array(3);

    for (let i = 0; i < labelArray.length; i++)
    {
        switch (i)
        {
            case 0:
            {
                labelArray = processorLabelsArray;
                break;
            }
            case 1:
            {
                labelArray = ramLabelsArray;
                break;
            }
            case 2:
            {
                labelArray = storageLabelsArray;
                break;
            }
        }

        let formattedLabelBuffer = "";
        for (let k = 0; k < labelArray.length; k++)
        {
            formattedLabelBuffer += labelArray[k].innerHTML;
        }

        switch (String(Number(formattedLabelBuffer)).length)
        {
            case 1:
            {
                labelArray[labelArray.length - 1].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                labelArray[labelArray.length - 2].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
                labelArray[labelArray.length - 3].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
                break;
            }
            case 2:
            {
                labelArray[labelArray.length - 1].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                labelArray[labelArray.length - 2].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                labelArray[labelArray.length - 3].style.color = (html.getAttribute("theme") == "light") ? "rgba(188, 188, 188, 1)" : "rgba(121, 121, 121, 1)";
                break;
            }
            default:
            {
                labelArray[labelArray.length - 1].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                labelArray[labelArray.length - 2].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                labelArray[labelArray.length - 3].style.color = (html.getAttribute("theme") == "light") ? "rgba(0, 0, 0, 1)" : "rgba(255, 255, 255, 1)";
                break;
            }
        }
    }
}