# Wifi Indoor Positioning [![Build Status](https://travis-ci.org/sankalpchauhan-me/IndoorPositioning.svg?branch=master)](https://travis-ci.org/sankalpchauhan-me/IndoorPositioning) [![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html) [![GitHub version](https://badge.fury.io/gh/sankalpchauhan-me%2FIndoorPositioning.svg)](https://badge.fury.io/gh/sankalpchauhan-me%2FWifiIndoorPositioning)

<img src="https://slideplayer.com/slide/4835959/15/images/2/Indoor+WiFi-based+Localization.jpg" align="middle" />&nbsp;

Wi-Fi positioning system (WPS) or WiPS/WFPS is a geolocation system that uses the characteristics of nearby Wi-Fi hotspots and other wireless access points to discover where a device is located. It is used where satellite navigation such as GPS is inadequate due to various causes including multipath and signal blockage indoors, or where acquiring a satellite fix would take too long. Such systems include indoor positioning systems. Wi-Fi positioning takes advantage of the rapid growth in the early 21st century of wireless access points in urban areas.

The most common and widespread localization technique used for positioning with wireless access points is based on measuring the intensity of the received signal (received signal strength indication or RSSI) and the method of "fingerprinting". Typical parameters useful to geolocate the wireless access point include its SSID and MAC address. The accuracy depends on the number of nearby access points whose positions have been entered into the database. The Wi-Fi hotspot database gets filled by correlating mobile device GPS location data with Wi-Fi hotspot MAC addresses.[4] The possible signal fluctuations that may occur can increase errors and inaccuracies in the path of the user. To minimize fluctuations in the received signal, we are using Kalman Filter.

This app uses Fingerprinting method for evaluating current location of the device relative to Access Points (for e.g. Wifi Routers). Fingerprinting is a great technique for positioning. It uses Received Signal Strength Index (RSSI) measurements. The basic idea of the fingerprinting method is to match a database to a particular fingerprint in the area at hand. When used with Wi-Fi systems, the fingerprinting method can be typically divided into two phases, calibration phase and positioning phase. As shown in Figure, the calibration phase is for establishing a database storing locations of Reference Points (RPs) in the area of interest. In this phase, signal strengths(RSS) from all the RPs are measured first, then the mean values of the RSS at each of the RPs are calculated, along with other information including the coordinates, the orientation and MAC address etc. In the positioning phase, the signal strengths from the APs are measured at the mobile side and compared with all the records in the database to identify the most probable location of the mobile object using either the deterministic or probabilistic algorithms.

<img src="/media/details.jpg" />&nbsp;


#### 1. Adding an Access Point

<img src="/media/WhatsApp%20Image%202019-04-14.gif" />&nbsp;

#### 2. Adding a Reference Point

<img src="/media/AddReferencePoint.gif" />&nbsp;

#### 3. User's Location or Device's Location plotted on Map

<img src="/media/WhatsApp%20Image%202019-04-14.jpg" />&nbsp;

## Minimum Requirements

 * Android SDK Platform 26 (android-O)
 * Android sdk tools 28.0.3
 * Android sdk build-tools 28.0.3

