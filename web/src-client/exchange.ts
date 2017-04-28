import './css/exchange.css';
import "leaflet_css";
import L = require('leaflet');

(function() {
    'use strict';

    var console = window.console;

    console.log("exchange it");

    let myMap = L.map('root').setView([38.03, -78.478889], 13);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox.mapbox-streets-v7',
        accessToken: 'pk.eyJ1IjoiYXdhaGFubmFuIiwiYSI6ImNpeHppaGt4NjAwNXUyd3A5d2g1cGFlb2cifQ.-H1RftaaYL9h6wajmqwGzQ'
    }).addTo(myMap);

})();
