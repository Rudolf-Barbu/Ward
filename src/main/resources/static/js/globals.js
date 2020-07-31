/**
 * Used to determine html tag object
 */
let html;

/**
 * Light theme square
 */
let lightThemeSquare;

/**
 * Used for holding button element
 */
let lightTheme;

/**
 * Dark theme square
 */
let darkThemeSquare;

/**
 * Used for holding button element
 */
let darkTheme;

/**
 * Used to set up server name
 */
let serverName;

/**
 * Used to set up application port
 */
let port;

/**
 * Used to send settings to database
 */
let submit;

/**
 * Used for sending setup request
 */
let setupXHR;

/**
 * Used for sending usage requests
 */
let usageXHR;

/**
 * Used for sending info requests
 */
let infoXHR;

/**
 * Used to hold value of processor name
 */
let processorName;

/**
 * Used to hold values of processor usage
 */
let processorLabelsArray;

/**
 * Used to hold value of clock speed
 */
let currentClockSpeed;

/**
 * Used to hold values tens of processor usage
 */
let ramLabelsArray;

/**
 * Used to hold value of processes count
 */
let currentProcCount;

/**
 * Used to hold values ones of processor usage
 */
let storageLabelsArray;

/**
 * Used to hold values of total storage
 */
let totalStorage

/**
 * Used to hold values of disk count
 */
let diskCount

/**
 * Used to manipulate processor triangle div
 */
let processorTriangle;

/**
 * Used to manipulate ram triangle div
 */
let ramTriangle;

/**
 * Used to manipulate storage triangle div
 */
let storageTriangle;

/**
 * Used to hold dataset element
 */
let processorRectangle;

/**
 * Used to hold dataset element
 */
let ramRectangle;

/**
 * Used to hold dataset element
 */
let storageRectangle;

/**
 *  Ctx chart object
 */
let ctx;

/**
 * Data for light chart theme
 */
let dataLight;

/**
 *  Data for dark chart theme
 */
let dataDark;

/**
 *  Options of chart object
 */
let options;

/**
 * Used to determine current page
 */
let currentPage;

/**
 * Used to select pages
 */
let firstControl;

/**
 * Used to select pages
 */
let secondControl;

/**
 * Used to determine logo page
 */
let logoPage;

/**
 * Used to determine info page
 */
let contactsPage;

/**
 * Used to determine left cloud
 */
let cloudLeft

/**
 * Used to determine right cloud
 */
let cloudRight

/**
 * Used to display current days of uptime
 */
let days;

/**
 * Used to display current hours of uptime
 */
let hours;

/**
 * Used to display current minutes of uptime
 */
let minutes;

/**
 * Used to display current seconds of uptime
 */
let seconds;

/**
 * Used to handle chart object, displays usage for 15 seconds
 */
let chart;

/**
 * Initialises html object and theme value
 */
function globalsInitialization()
{
    html = document.getElementById("html");
}