<!DOCTYPE html>
<html lang = "en">
    <head>
        <title>B-Server</title>
        <meta charset = "utf-8">
        <link rel = "stylesheet" href = "./css/assets/bootstrap.min.css">
        <link rel = "stylesheet" href = "./css/colors.css">
        <link rel = "stylesheet" href = "./css/dimensions.css">
        <link rel = "stylesheet" href = "./css/fonts.css">
        <link rel = "stylesheet" href = "./css/theme.css">
        <script src = "./js/assets/chart.min.js"></script>
        <script src = "./js/chart.js"></script>
        <script src = "./js/label.js"></script>
        <script src = "./js/tick.js"></script>
        <script src = "./js/uptime.js"></script>
        <?php require_once './php/info.php'; ?>
        <?php require_once './php/uptime.php'; ?>
    </head>
    <body>
        <div class = "container">
            <div class = "row">
                <div class = "col-4">
                    <div class = "card">
                        <div class = "header">
                            <div class = "hw-logo blue-light"></div>
                            <div class = "label-hw-info">
                                <div class = "hw-type">Processor</div>
                                <div class = "hw-name">
                                    <?php
                                        echo getProcessor()["modelName"];
                                    ?>
                                </div>
                            </div>
                        </div>
                        <div class = "body">
                            <div class = "card-body-squares-grid">
                                <div class = "blue-light"></div>
                                <div class = "blue-light"></div>
                                <div class = "blue-light"></div></div>
                            <div class = "main-hw-info">
                                <div id = "processor-label" class = "hw-utilization-value">000%</div>
                                <p class = "info-label">PROCESSOR USAGE</p>
                                <div class = "underline blue-light"></div>
                            </div>
                        </div>
                        <div class = "footer blue-light">
                            <div class = "card-footer-dots-grid">
                                <div class = "first-dot">
                                    <div class = "inner-dot blue"></div>
                                </div>
                                <div class = "second-dot">
                                    <div class = "inner-dot blue"></div>
                                </div>
                                <div class = "third-dot">
                                    <div class = "inner-dot blue"></div>
                                </div>
                            </div>
                            <div class = "detailed-hw-info">
                                <div class = "first-label">
                                    <?php
                                        echo getProcessor()["coreCount"];
                                    ?>
                                </div>
                                <div class = "second-label">
                                    <?php
                                        echo getProcessor()["maxClockSpeed"];
                                    ?>
                                </div>
                                <div class = "third-label">
                                    <?php
                                        echo getProcessor()["cache"];
                                    ?>
                                </div>
                            </div>
                            <div class = "dividers">
                                <div class = "first-divider"></div>
                                <div class = "second-divider"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col-4">
                    <div class = "card">
                        <div class = "header">
                            <div class = "hw-logo red-light"></div>
                            <div class = "label-hw-info">
                                <div class = "hw-type">Machine</div>
                                <div class = "hw-name">
                                    <?php 
                                        echo getMachine()["machineName"];
                                    ?>
                                </div>
                            </div>
                        </div>
                        <div class = "body">
                            <div class = "card-body-squares-grid">
                                <div class = "red-light"></div>
                                <div class = "red-light"></div>
                                <div class = "red-light"></div>
                            </div>
                            <div class = "main-hw-info">
                                <div id = "memory-label" class = "hw-utilization-value">000%</div>
                                <p class = "info-label">RAM USAGE</p>
                                <div class = "underline red-light"></div>
                            </div>
                        </div>
                        <div class = "footer red-light">
                            <div class = "card-footer-dots-grid">
                                <div class = "first-dot">
                                    <div class = "inner-dot red"></div>
                                </div>
                                <div class = "second-dot">
                                    <div class = "inner-dot red"></div>
                                </div>
                                <div class = "third-dot">
                                    <div class = "inner-dot red"></div>
                                </div>
                            </div>
                            <div class = "detailed-hw-info">
                                <div class = "first-label">
                                    <?php 
                                        echo getMachine()["ramAmount"];
                                    ?>
                                </div>
                                <div class = "second-label">
                                    <?php 
                                        echo getMachine()["processCount"];
                                    ?>
                                </div>
                                <div class = "third-label">
                                    <?php 
                                        echo getMachine()["mips"];
                                    ?>
                                </div>
                            </div>
                            <div class = "dividers">
                                <div class = "first-divider"></div>
                                <div class = "second-divider"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col-4">
                    <div class = "card">
                        <div class = "header">
                            <div class = "hw-logo green-light"></div>
                            <div class = "label-hw-info">
                                <div class = "hw-type">Storage</div>
                                <div class = "hw-name">
                                    <?php
                                        echo getStorage()["storageName"];
                                    ?>
                                </div>
                            </div>
                        </div>
                        <div class = "body">
                            <div class = "card-body-squares-grid">
                                <div class = "green-light"></div>
                                <div class = "green-light"></div>
                                <div class = "green-light"></div>
                            </div>
                            <div class = "main-hw-info">
                                <div id = "storage-label" class = "hw-utilization-value">000%</div>
                                <div class = "info-label">STORAGE USAGE</div>
                                <div class = "underline green-light"></div>
                            </div>
                        </div>
                        <div class = "footer green-light">
                            <div class = "card-footer-dots-grid">
                                <div class = "first-dot">
                                    <div class = "inner-dot green"></div>
                                </div>
                                <div class = "second-dot">
                                    <div class = "inner-dot green"></div>
                                </div>
                                <div class = "third-dot">
                                    <div class = "inner-dot green"></div>
                                </div>
                            </div>
                            <div class = "detailed-hw-info">
                                <div class = "first-label">
                                    <?php
                                        echo getStorage()["storageAmount"];
                                    ?>
                                </div>
                                <div class = "second-label">
                                    <?php
                                        echo getStorage()["diskCount"];
                                    ?>
                                </div>
                                <div class = "third-label">
                                    <?php
                                        echo getStorage()["swapAmount"];
                                    ?>
                                </div>
                            </div>
                            <div class = "dividers">
                                <div class = "first-divider"></div>
                                <div class = "second-divider"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <script>
                    labelInitialization();
                </script>
            </div>
            <div class = "row">
                <div class = "col-4">
                    <div class = "card">
                        <div class = "about">
                            <div class = "dashboard-logo">WARD</div>
                            <div class = "dashboard-logo-description">LINUX DASHBOARD</div>
                            <img class = "dashboard-logo-background" src = "./img/logo/background.jpg" />
                        </div>
                        <div class = "uptime">
                            <div class = "uptime-squares-grid">
                                <div class = "blue-light"></div>
                                <div class = "red-light"></div>
                                <div class = "green-light"></div>
                            </div>
                            <div class = "uptime-rectangle-grid">
                                <div class = "values-grid">
                                    <?php
                                        echo getUptime()[0];
                                        echo getUptime()[1];
                                        echo getUptime()[2];
                                        echo getUptime()[3];
                                    ?>
                                    <script>
                                        uptimeInitialization();
                                    </script>
                                </div>
                                <div class = "labels-grid">
                                    <div>DAYS</div>
                                    <div>HOURS</div>
                                    <div>MINUTES</div>
                                    <div>SECONDS</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col-8">
                    <div class = "card">
                        <div class = "chart-label">% Utilization</div>
                        <div class = "chart-rectangle-grid">
                            <div id = "processor-rectangle" class = "first-rectangle blue-light"></div>
                            <div id = "ram-rectangle" class = "second-rectangle red-light"></div>
                            <div id = "storage-rectangle" class = "third-rectangle green-light"></div>
                        </div>
                        <div class = "chart-container">
                            <canvas id = "chart-body"></canvas>
                            <script>
                                chartInitialization("chart-body");
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            tickInitialization();
        </script>
    </body>
</html>