/**
 * Used to display current processor usage
 */
let processorLabel;

/**
 * Used to display current ram usage
 */
let ramLabel;

/**
 * Used to display current storage usage
 */
let storageLabel;

/**
 * Used to handle chart object, displays usage for 15 seconds
 */
let chart;

/**
 * Initializes chart object with options and datasets. Also determines labels elements
 */
function usageInitialization()
{
    processorLabel = document.getElementById("processor-label");
    ramLabel = document.getElementById("ram-label");
    storageLabel = document.getElementById("storage-label");
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
                    borderColor: "#5965F9",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "#FFFFFF",
                    pointHoverBackgroundColor: "#E6E8FE",
                    backgroundColor: "rgba(230, 232, 254, 0.3)",
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                },
                {
                    label: "Ram usage",
                    borderWidth: 1.5,
                    borderColor: "#FF5959",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "#FFFFFF",
                    pointHoverBackgroundColor: "#F9E2E2",
                    backgroundColor: "rgba(249, 226, 226, 0.3)",
                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                },
                {
                    label: "Storage usage",
                    borderWidth: 1.5,
                    borderColor: "#08C18D",
                    pointRadius: 2,
                    pointHoverRadius: 3,
                    pointBackgroundColor: "#FFFFFF",
                    pointHoverBackgroundColor: "#D4F2E1",
                    backgroundColor: "rgba(212, 242, 225, 0.3)",
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
                    tension: 0.3
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
            animation: false
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
    processorLabel.innerHTML = labelAssignment(usageData.processor);
    ramLabel.innerHTML = labelAssignment(usageData.ram);
    storageLabel.innerHTML = labelAssignment(usageData.storage);
    updateDataset(chart.data.datasets[0].data, usageData.processor);
    updateDataset(chart.data.datasets[1].data, usageData.ram);
    updateDataset(chart.data.datasets[2].data, usageData.storage);
}

/**
 * Formats usage data values
 *
 * @param {*} usageData usage value
 */
function labelAssignment(usageData)
{
    let formatedUsageData = "";
    if (parseInt(usageData) < 10)
    {
        formatedUsageData = "00" + usageData + "%";
    }
    else if (parseInt(usageData) < 100)
    {
        formatedUsageData = "0" + usageData + "%";
    }
    else
    {
        formatedUsageData = usageData + "%";
    }
    return formatedUsageData;
}

/**
 * Updates dataset shifting previous values
 *
 * @param {*} dataset dataset to update
 * @param {*} usageData new data
 */
function updateDataset(dataset, usageData)
{
    for(let i = 0; i < dataset.length - 1; i++)
    {
        dataset[i] = dataset[i + 1];
    }
    dataset[dataset.length - 1] = usageData;
    chart.update();
}

/**
 * Hides chosen dataset from chart
 *
 * @param {*} element dataset to hide
 */
function hideDataset(element)
{
    switch(String(element.id))
    {
        case "processor-rectangle":
        {
            element.style.backgroundColor = (chart.getDatasetMeta(0).hidden) ? "rgba(230, 232, 254, 1)" : "rgba(188, 188, 188, 1)";
            chart.getDatasetMeta(0).hidden = (chart.getDatasetMeta(0).hidden) ? false : true;
            break;
        }
        case "ram-rectangle":
        {
            element.style.backgroundColor = (chart.getDatasetMeta(1).hidden) ? "rgba(249, 226, 226, 1)" : "rgba(188, 188, 188, 1)";
            chart.getDatasetMeta(1).hidden = (chart.getDatasetMeta(1).hidden) ? false : true;
            break;
        }
        case "storage-rectangle":
        {
            element.style.backgroundColor = (chart.getDatasetMeta(2).hidden) ? "rgba(212, 242, 225, 1)" : "rgba(188, 188, 188, 1)";
            chart.getDatasetMeta(2).hidden = (chart.getDatasetMeta(2).hidden) ? false : true;
            break;
        }
    }
    chart.update();
}