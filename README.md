<h3 align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1297549742976925024/97F3CBA92B2FEF3652F5A06EC90FB5FF89E5D224/" alt = "Logo" />
</h3>

---

# Quick start

## Docker

* `docker run --restart unless-stopped -it -d --name ward  -p 4000:4000 -p <application port>:<application port> --privileged antonyleons/ward`
* Go to localhost:4000 in web browser, input the same application port
* If you get error after being redirected to application port try hitting refresh

## Java

Download the latest release from [here](https://github.com/AntonyLeons/Ward/releases/latest)

```console
java -jar ward.jar
```

### About

Ward is a simple and and minimalistic server monitoring tool. Ward supports adaptive design system. Also it supports dark theme.
It shows only principal information and can be used, if you want to see nice looking dashboard instead looking on bunch of numbers and graphs.
Ward works nice on all popular operating systems, because it uses [OSHI](https://github.com/oshi/oshi).

**All features tested on:** `Windows` `Linux`

<p align = "center">
    <img src = "https://steamuserimages-a.akamaihd.net/ugc/1601547572022736987/1D8D2E576D957DDB9CE34E13D5944AF841E8AAD8/" alt = "Preview Image" />
    <h7 align = "center">Preview Image</h7>
</p>

---

### Features

<table>
    <tr>
        <td width = "600.5">Processor name</td>
        <td rowspan = "5">
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1601547572022743136/D62DF59CFA60F5749F2DC7BFE5E9256BCF59E066/" alt = "Card 1" align = "center" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1601547572022744630/F9E0CACAA81C882B2F4E401E65090BE9F1FE96F6/" alt = "Card 2" align = "center" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1601547572022746249/D6C5612E2D6AB759CC10438C2D93F7EC80F83D83/" alt = "Card 3" align = "center" />
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
            <img src = "https://steamuserimages-a.akamaihd.net/ugc/1601547572022740496/79ED24E5E626C7029DA4BDEFFBB04C3E0BF61DB1/" alt = "Card 4" align = "center" />
        </td>
    </tr>
    <tr>
        <td>
            This block contain uptime and chart sections. Uptime represent time since last boot on Linux, and time between hard resets on Windows.
            Also it have paginator which can be useful to get author contacts.
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

    1. Create you own jar as described above
    2. Execute jar on Windows or Linux with administrative rights
    3. Enter localhost:4000 and set up application

<br>

    Build for Docker

    1. Clone the project
    2. docker build --tag ward
    3. docker run --rm -it --name ward -p 4000:4000 -p <application port>:<application port> --privileged ward
    4. Go to localhost:4000 in web browser, input the same application port
    5. If you get error after being redirected to application port try hitting refresh

### Config

If you want to change Ward's configuration, you can edit `setup.ini`. When using Docker, this file is located within the container at `/`. `setup.ini` is generated once you navigate to Ward's webpage and complete the initial setup. You can also make this file yourself before starting Ward and it will use your configuration.

| Setting    | Description                  | Default |
|------------|------------------------------|---------|
| serverName | Name shown in the interface. |         |
| theme      | Either `light` or `dark`.    |         |
| port       | Port to listen on.           | 4000    |

For example:
```ini
[setup]
serverName = my-server
theme = dark
port = 8200
```

If you're using Docker and you want to avoid the initial setup or have Ward listen on a different port right away, you could create a `setup.ini` on the host and copy it to the container:
```bash
docker cp setup.ini ward:/setup.ini
```

Then, just make sure you `docker restart ward`.
