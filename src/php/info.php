<?php

function getProcessor(): array
{
    $processor = [];

    foreach (json_decode(strval(shell_exec("lscpu -J")))->lscpu as $field)
    {
        if (strval($field->field) == "Model name:")
        {
            $modelName = strval($field->data);
            if (strpos($modelName, "@") !== false)
            {
                $modelName = substr($modelName, 0, strpos($modelName, "@") - 1);
            }
            $processor["modelName"] = $modelName;
        }

        if (strval($field->field) == "CPU(s):")
        {
            $postfix = " Core";
            if (intval($field->data) > 1)
            {
                $postfix .= "s";
            }
            $processor["coreCount"] = $field->data . $postfix;
        }

        if (strval($field->field) == "CPU max MHz:")
        {
            $processor["maxClockSpeed"] = round(floatval($field->data) / 1000, 1) . " GHz";
        }

        if (strval($field->field) == "L3 cache:")
        {
            $processor["cache"] = intval($field->data) / 1024 . " MiB Cache";
        }
    }
    
    return $processor;
}

function getMachine(): array
{
    $machine = [];

    $boardVendor = strval(shell_exec("cat /sys/devices/virtual/dmi/id/board_vendor"));
    $boardProductCode = strval(shell_exec("cat /sys/devices/virtual/dmi/id/product_name"));
    $machine["machineName"] = trim($boardVendor) . " " . trim($boardProductCode);

    $ramAmount = intval(intval(shell_exec("free -k | awk 'NR == 2 {print $2}'")) * 9.3132e-7);
    $machine["ramAmount"] = $ramAmount . " GiB Ram";

    $processCount = intval(shell_exec("ps -aux | wc -l"));
    $postfix = " Proc";
    if ($processCount > 1)
    {
        $postfix .= "s";
    }
    $machine["processCount"] = $processCount . $postfix;

    foreach (json_decode(strval(shell_exec("lscpu -J")))->lscpu as $field)
    {
        if (strval($field->field) == "BogoMIPS:")
        {
            $machine["mips"] = intval($field->data) . " Mips";
        }
    }
    return $machine;
}

function getStorage(): array
{
    $storage = [];

    
    $storageName = strval(shell_exec("cat /proc/scsi/scsi | awk 'NR == 3 {print $0}'"));
    $storage["storageName"] = substr($storageName, strpos($storageName, "Model:") + 6,  strlen($storageName));

    $storageAmount = intval(intval(shell_exec("df --block-size=1024 --output=size | awk 'NR > 1 {sum += $1} END {print sum}'")) * 9.3132e-7);
    $storage["storageAmount"] = $storageAmount . " GiB Total";

    $diskCount = intval(shell_exec("cat /proc/scsi/scsi | grep 'Host:' | wc -l"));
    $postfix = " Disk";
    if ($diskCount > 1)
    {
        $postfix .= "s";
    }
    $storage["diskCount"] = $diskCount . $postfix;

    $swapAmount = intval(intval(shell_exec("free -k | awk 'NR == 3 {print $2}'")) * 9.3132e-7);
    $storage["swapAmount"] = $swapAmount . " GiB Swap";

    return $storage;
}