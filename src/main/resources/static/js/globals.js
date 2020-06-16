/**
 * Used for sending ajax requests
 */
let xhr;

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
 * Used to hold values of processor usage
 */
let processorUsageArray;

/**
 * Used to hold values tens of processor usage
 */
let ramUsageArray;

/**
 * Used to hold values ones of processor usage
 */
let storageUsageArray;

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
 * Used to handle chart object, displays usage for 15 seconds
 */
let chart;