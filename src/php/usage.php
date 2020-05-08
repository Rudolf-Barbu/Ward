<?php

function getUsage(): string
{ 
    $usage = [];

    $usage["processorUsage"] = getProcessorUsage();

    $totalMemory = intval(shell_exec("free -k | awk 'NR == 2 {print $2;}'"));
    $freeMemory = intval(shell_exec("free -k | awk 'NR == 2 {print $4;}'"));
    $usage["memoryUsage"] = intval((100 - (($freeMemory / $totalMemory) * 100)));

    $totalStorage = intval(shell_exec("df --block-size=1024 --output=size | awk 'NR > 1 {sum += $1;} END {print sum;}'"));
    $usedStorage = intval(shell_exec("df --block-size=1024 --output=used | awk 'NR > 1 {sum += $1;} END {print sum;}'"));
    $usage["storageUsage"] = intval(($usedStorage / $totalStorage) * 100);

    return json_encode($usage);
}

function getProcessorUsage(): int
{
    $previousTotalTime = intval(shell_exec("cat /proc/stat | awk 'NR == 1 {for (i = 2; i <= NF; i++) {sum += \$i;}} END {print sum;}'"));
    $previousIdleTime = intval(shell_exec("cat /proc/stat | awk 'NR == 1 {print $5;}'"));

    sleep(1);

    $currentTotalTime = intval(shell_exec("cat /proc/stat | awk 'NR == 1 {for (i = 2; i <= NF; i++) {sum += \$i;}} END {print sum;}'"));
    $currentIdleTime = intval(shell_exec("cat /proc/stat | awk 'NR == 1 {print $5;}'"));
    
    return intval((1 - ($currentIdleTime - $previousIdleTime) / ($currentTotalTime - $previousTotalTime)) * 100);
}

header('Content-Type: application/json');
echo getUsage();