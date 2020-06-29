/**
 * Used to determine html tag object
 */
let html;

/**
 * Used for holding button element
 */
let lightTheme

/**
 * Used for holding button element
 */
let darkTheme

/**
 * Used for sending ajax requests
 */
let xhr;

/**
 * Used to hold values of processor usage
 */
let processorLabelsArray;

/**
 * Used to hold values tens of processor usage
 */
let ramLabelsArray;

/**
 * Used to hold values ones of processor usage
 */
let storageLabelsArray;

/**
 * Used to hold dataset element
 */
let processorRectangle

/**
 * Used to hold dataset element
 */
let ramRectangle

/**
 * Used to hold dataset element
 */
let storageRectangle

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
 * Used to handle chart object, displays usage for 15 seconds
 */
let chart;

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
 * Initialises html object and theme value
 */
function globalsInitialization()
{
    html = document.getElementById("html");
}