let processorLabel;
let memoryLabel;
let storageLabel;

function labelInitialization()
{
    processorLabel = document.getElementById("processor-label");
    memoryLabel = document.getElementById("memory-label");
    storageLabel = document.getElementById("storage-label");
}

function labelTick(usageData)
{
    processorLabel.innerHTML = labelAssignment(usageData.processorUsage);
    memoryLabel.innerHTML = labelAssignment(usageData.memoryUsage);
    storageLabel.innerHTML = labelAssignment(usageData.storageUsage);
}

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