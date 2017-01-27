import * as fs from "fs"
import * as csv2geojson from 'csv2geojson'
import {Converter} from "./converter"

function main() {
    fs.readFile("resources/434.html", "utf-8", function (err, data) {
        if (err) throw err

        let converter = new Converter()
        let csv = converter.convert(data)

        let geoJson = csv2geojson.csv2geojson(csv, function(err, data) {
            if (err) {
                console.log(err)
                throw err
            }

            let json = JSON.stringify(data, null, 2)
            console.log(json)
            // fs.writeFile("resources/434.json", json)
        });
    })
}

main()