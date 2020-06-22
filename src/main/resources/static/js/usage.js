/**
 * Initializes chart object with options and datasets. Also determines labels elements
 */
function usageInitialization()
{
    processorUsageArray =
    [
        document.getElementById("processor-hundreds"),
        document.getElementById("processor-tens"),
        document.getElementById("processor-ones")
    ];
    ramUsageArray =
    [
        document.getElementById("ram-hundreds"),
        document.getElementById("ram-tens"),
        document.getElementById("ram-ones")
    ];
    storageUsageArray =
    [
        document.getElementById("storage-hundreds"),
        document.getElementById("storage-tens"),
        document.getElementById("storage-ones")
    ];

    processorTriangle = document.getElementById("processor-triangle");
    ramTriangle = document.getElementById("ram-triangle");
    storageTriangle = document.getElementById("storage-triangle");

    html = document.getElementById("html");

    let ctx = document.getElementById("chart-body").getContext("2d");
    let data =
    {
        type: "line",
        data:
        {
            labels: ["", "", "", "", "", "", "", "", "", "", "", "", "", "", ""],
            datasets:
            [
                {
                    label: "Processor usage",
                    borderWidth: 1.5,
                    borderColor: (html.getAttribute("theme") == "light") ? "rgba(89, 101, 249, 1)" : "rgba(188, 188, 188, 1)",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "rgba(255, 255, 255, 1)",
                    pointHoverBackgroundColor: (html.getAttribute("theme") == "light") ? "rgba(230, 232, 254, 1)" : "rgba(121, 121, 121, 1)",
                    backgroundColor: (html.getAttribute("theme") == "light") ? "rgba(230, 232, 254, 0.3)" : "rgba(121, 121, 121, 0.5)",
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                },
                {
                    label: "Ram usage",
                    borderWidth: 1.5,
                    borderColor: (html.getAttribute("theme") == "light") ? "rgba(255, 89, 89, 1)" : "rgba(188, 188, 188, 1)",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "rgba(255, 255, 255, 1)",
                    pointHoverBackgroundColor: (html.getAttribute("theme") == "light") ? "rgba(249, 226, 226, 1)" : "rgba(121, 121, 121, 1)",
                    backgroundColor: (html.getAttribute("theme") == "light") ? "rgba(249, 226, 226, 0.3)" : "rgba(121, 121, 121, 0.5)",
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                },
                {
                    label: "Storage usage",
                    borderWidth: 1.5,
                    borderColor: (html.getAttribute("theme") == "light") ? "rgba(8, 193, 141, 1)" : "rgba(188, 188, 188, 1)",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "rgba(255, 255, 255, 1)",
                    pointHoverBackgroundColor: (html.getAttribute("theme") == "light") ? "rgba(212, 242, 225, 1)" : "rgba(121, 121, 121, 1)",
                    backgroundColor: (html.getAttribute("theme") == "light") ? "rgba(212, 242, 225, 0.3)" : "rgba(121, 121, 121, 0.5)",
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                }
            ]
        },
        options:
        {
            maintainAspectRatio: false,
            legend:
            {
                display: false
            },
            elements:
            {
                line:
                {
                    tension: 0
                }
            },
            scales:
            {
                yAxes:
                [
                    {
                        ticks:
                        {
                            display: false,
                            suggestedMin: 0,
                            suggestedMax: 100
                        },
                        gridLines:
                        {
                            drawTicks: false
                        }
                    }
                ],
                xAxes:
                [
                    {
                        ticks:
                        {
                            display: false
                        },
                        gridLines:
                        {
                            drawTicks: false
                        }
                    }
                ]
            },
            animation:
            {
                duration: 150
            }
        }
    };

    chart = new Chart(ctx, data);

    document.getElementById("processor-rectangle").addEventListener("click", function(event) {hideDataset(event.target || event.srcElement)});
    document.getElementById("ram-rectangle").addEventListener("click", function(event) {hideDataset(event.target || event.srcElement)});
    document.getElementById("storage-rectangle").addEventListener("click", function(event) {hideDataset(event.target || event.srcElement)});
}

/**
 * Updates chart and labels usage values
 *
 * @param {*} usageData Json object with usage data
 */
function usageTick(usageData)
{
    updateLabels(usageData);
    updateDatasets(chart.data.datasets, usageData);
}

/**
 * Updates labels values
 *
 * @param {*} usageData usage value
 */
function updateLabels(usageData)
{
    let usageDataArray = Object.values(usageData);

    for (let i = 0; i < usageDataArray.length; i++)
    {
        switch (i)
        {
            case 0:
            {
                formatLabel(processorUsageArray, usageDataArray[i]);
                break;
            }
            case 1:
            {
                formatLabel(ramUsageArray, usageDataArray[i]);
                break;
            }
            case 2:
            {
                formatLabel(storageUsageArray, usageDataArray[i]);
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
function formatLabel(labelArray, usageData)
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
 * Updates datasets shifting previous values
 *
 * @param {*} datasets datasets to update
 * @param {*} usageData new data
 */
function updateDatasets(datasets, usageData)
{
    for (let i = 0; i < datasets.length; i++)
    {
        let dataset = datasets[i].data;
        let usageDataArray = Object.values(usageData);

        for (let k = 0; k < dataset.length - 1; k++)
        {
            dataset[k] = dataset[k + 1];
        }
        dataset[dataset.length - 1] = usageDataArray[i];
    }

    chart.update();
}

/**
 * Hides chosen dataset from chart
 *
 * @param {*} element dataset to hide
 */
function hideDataset(element)
{
    switch (String(element.id))
    {
        case "processor-rectangle":
        {
            processorTriangle.style.animation = chart.getDatasetMeta(0).hidden ? "fade-in-triangles 0.5s forwards" : "fade-out-triangles 0.5s forwards";

            element.style.backgroundColor = chart.getDatasetMeta(0).hidden ?
            ((html.getAttribute("theme") == "light") ? "rgba(230, 232, 254, 1)" : "rgba(121, 121, 121, 1)") : "rgba(188, 188, 188, 1)";

            chart.getDatasetMeta(0).hidden = chart.getDatasetMeta(0).hidden ? false : true;
            break;
        }
        case "ram-rectangle":
        {
            ramTriangle.style.animation = chart.getDatasetMeta(1).hidden ? "fade-in-triangles 0.5s forwards" : "fade-out-triangles 0.5s forwards";

            element.style.backgroundColor = chart.getDatasetMeta(1).hidden ?
            ((html.getAttribute("theme") == "light") ? "rgba(249, 226, 226, 1)" : "rgba(121, 121, 121, 1)") : "rgba(188, 188, 188, 1)";

            chart.getDatasetMeta(1).hidden = chart.getDatasetMeta(1).hidden ? false : true;
            break;
        }
        case "storage-rectangle":
        {
            storageTriangle.style.animation = chart.getDatasetMeta(2).hidden ? "fade-in-triangles 0.5s forwards" : "fade-out-triangles 0.5s forwards";

            element.style.backgroundColor = chart.getDatasetMeta(2).hidden ?
            ((html.getAttribute("theme") == "light") ? "rgba(212, 242, 225, 1)" : "rgba(121, 121, 121, 1)") : "rgba(188, 188, 188, 1)";

            chart.getDatasetMeta(2).hidden = chart.getDatasetMeta(2).hidden ? false : true;
            break;
        }
    }

    chart.update();
}