<h3 align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1297549742976925024/97F3CBA92B2FEF3652F5A06EC90FB5FF89E5D224/" alt = "Logo" />
</h3>

---

### About

Ward is a simple and and minimalistic server monitoring tool. Ward supports adaptive design system. Also it supports dark theme.
It shows only principal information and can be used, if you want to see nice looking dashboard instead looking on bunch of numbers and graphs.
Ward works nice on all popular operating systems, because it uses [OSHI](https://github.com/oshi/oshi).

**All features tested on:** `Windows` `Linux`

<p align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1472065476915719686/49DDF67C0F9E7E7CB6F101D6358C8B4000E02765/" alt = "Preview Image" />
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
        <td>Current frequency of the processor</td>
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
        <td>Generation of the installed RAM (If you have dmidecode)</td>
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
        <td>Total current storage installed (Including external drives)</td>
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1472065476915728087/6CA3545E874463382CFFEC182CBD292C57FB29DB/" alt = "Card 4" align = "center" />
        </td>
    </tr>
    <tr>
        <td>
            This block contain uptime and chart sections. Uptime represent time since last boot on Linux, and time between hard resets on Windows.
            Also it have paginator which can be useful to see network information, or get contacts.
            Chart section display last fifteen seconds of server utilization. (Proccesor, ram, storage)
            You can hide separated datasets by clicking on rectangles on the top right corner of chart section.
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
    3. Enter localhost:4000 and set up application

<br>

    Build for Docker

    1. Clone the project
    2. docker build --tag ward .
    3. docker run --rm -it --name ward -p 4000:4000 -p <application port>:<application port> --privileged ward
    4. Go to localhost:4000 in web browser, input the same application port
    5. If you get error after being redirected to application port try hitting refresh.
