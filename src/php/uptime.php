<?php

function getUptime(): array
{
    $uptime = [];

    $secondsSinceBoot = intval(shell_exec("cat /proc/uptime | awk '{print $1;}'"));

    $uptime[] = ["uptime-days", strval(floor($secondsSinceBoot / 86400))];
    $uptime[] = ["uptime-hours", strval(floor(($secondsSinceBoot % 86400) / 3600))];
    $uptime[] = ["uptime-minutes", strval(floor(($secondsSinceBoot / 60) % 60))];
    $uptime[] = ["uptime-seconds", strval(floor($secondsSinceBoot % 60))];

    $divElements = [];
    foreach($uptime as $divElement)
    {
        if (intval($divElement[1]) < 10)
        {
            $divElement[1] = "0" . $divElement[1];
        }
        $divElements[] = "<div><p id = \"" . $divElement[0] . "\">" . $divElement[1] . "</p></div>";
    }
    
    return $divElements;
}