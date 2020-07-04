<h3 align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183832249548920590/A4887F3781AB6DD6B7CA098566FB62E6F5D92975/" alt = "Logo" />
    <h4 align = "center"><a href = "http://b-server.org/">Demo</a></h4> 
</h3>

---

### About

Ward is a simple and and minimalistic server monitoring tool. Ward supports adaptive design system. Also it supports dark theme.
It shows only principal information and can be used, if you want to see nice looking dashboard instead looking on bunch of numbers and graphs.
Ward works nice on all popular operating systems, because it uses [OSHI](https://github.com/oshi/oshi).

**All features tested on:** `Windows` `Linux`

<p align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183832249548928124/96254EF1AE05044ADA3AF54073ADDE891D8CA71E/" alt = "Preview Image" />
    <h7 align = "center">Preview Image</h7>
</p>

---

### Features

<table>
    <tr>
        <td width = "600.5">Processor name</td>
        <td rowspan = "5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183831643851282360/403FDEBFCF7D1FF0F69EB5278A61D4E20845DB7A/" alt = "Card 1" align = "center" />
        </td>
    </tr>
    <tr>
        <td>Processor utilization percentage</td>
    </tr>
    <tr>
        <td>Processor cores count (Logical and physical ones)</td>
    </tr>
    <tr>
        <td>Maximum frequency of the processor</td>
    </tr>
    <tr>
        <td>Does the processor supports 64-bit instructions</td>
    </tr>
</table>

<br>

<table>
    <tr>
        <td width = "600.5">Type of operating system and it's version</td>
        <td rowspan = "5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183831643851285796/144BB74471806F12E742CD82F8FF2756F30E4683/" alt = "Card 2" align = "center" />
        </td>
    </tr>
    <tr>
        <td>RAM utilization percentage</td>
    </tr>
    <tr>
        <td>Amount of total installed RAM</td>
    </tr>
    <tr>
        <td>Generation of the installed RAM</td>
    </tr>
    <tr>
        <td>Current processes count</td>
    </tr>
</table>

<br>

<table>
    <tr>
        <td width = "600.5">Host0 storage name</td>
        <td rowspan = "5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183831643851289616/A01554173BC78C5AF030573F8572B53E00CF5EFD/" alt = "Card 3" align = "center" />
        </td>
    </tr>
    <tr>
        <td>Storage utilization percentage</td>
    </tr>
    <tr>
        <td>Total Storage installed (Including external drives)</td>
    </tr>
    <tr>
        <td>Installed disks count</td>
    </tr>
    <tr>
        <td>Total amount of virtual memory (Swap in Linux)</td>
    </tr>
</table>

<br>

<table>
    <tr>
        <td width = "916.5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1183831643851293102/5E0B6FB960A45E5397102326BDCD6035E96A23EA/" alt = "Card 4" align = "center" />
        </td>
    </tr>
    <tr>
        <td>
            This block contain uptime and chart modules. Uptime represent time since last boot on Linux, and time between hard resets on Windows.
            Chart display last fifteen seconds of server utilization. (Proccesor, ram, storage)
            You can hide separated datasets by clicking on rectangles on re top right corner of chart module.
        </td>
    </tr>
</table>

---

### Installation
    Create your own jar

    1. Clone the project
    2. Import project in your IDE as Maven project
    3. mvn clean package
    4. jar will be in the target folder

<br>

    Run jar file

    1. Download jar file from latest release (Or build you own as described above)
    2. Execute jar on Windows or Linux with administrative rights
    3. Enter "your path":4000 and set up application

<br>

    Build for Docker

    1. Clone the project
    2. mvn clean package
    3. docker build --tag ward
    4. docker run --rm -it --name ward -p 8082:80 ward
    5. Go to localhost:8082 in web browser
    
    NOTE: Thanks to NangiDev
