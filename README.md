<h3 align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1012690662470353073/FC58F696881C05DF43E3D45E707E2A35E9176E91/" alt = "Logo" />
</h3>

---

### About

Ward is a simple and and minimalistic server monitoring tool. 
It shows only principal information and can be used, if you want to see nice looking dashboard instead bunch of numbers and graphs.
Ward works nice on all popular operating systems, because it uses [OSHI](https://github.com/oshi/oshi).

<p align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1021700505300814148/E9FD3820605C945DA9C14E5FB6E189D7D36ABA77/" alt = "Preview Image" />
    <h7 align = "center">Preview Image</h7>
</p>

**Ward tested on:** `Windows` `Linux`

---

### Features

<table>
    <tr>
        <td width = "600.5">Processor name</td>
        <td rowspan = "5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1021700505300885710/ECF3044C71DEBE87D06E7017E61D6ABA9735CEF8/" alt = "Card 1" align = "center" width = "289" height = "224" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1021700505300889331/2708C0302E91CF20B78DBB012878F46EA906FB49/" alt = "Card 2" align = "center" width = "289" height = "224" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1021700505300892445/953B8488230DA6316D12D37E2DE1B7B815FB19E7/" alt = "Card 3" align = "center" width = "289" height = "224" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1021700703201509261/E3DF09C892C172B7FC98CD19FBA022BBC1925179/" alt = "Card 4" align = "center" width = "994" height = "224" />
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
Just deploy `.war` file from latest `Stable` release on your `Tomcat` server.