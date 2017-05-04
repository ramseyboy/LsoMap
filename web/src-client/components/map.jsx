import * as React from "react";
import * as ReactDOM from "react-dom";
import '../css/exchange.css';
import "leaflet_css";
import L from 'leaflet';

export class Map extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        let node = ReactDOM.findDOMNode(this);
        this.map = L.map(node, {
            center: [40, 30],
            zoom: 5,
            maxZoom: 18,
            layers: L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
                attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
                maxZoom: 18,
                id: 'mapbox.mapbox-streets-v7',
                accessToken: 'pk.eyJ1IjoiYXdhaGFubmFuIiwiYSI6ImNpeHppaGt4NjAwNXUyd3A5d2g1cGFlb2cifQ.-H1RftaaYL9h6wajmqwGzQ'
            })
        }, 100)
    }

    componentWillUnmount() {
        this.map.remove()
    }

    render() {
        return <div id='map'></div>;
    }
}

