import * as fs from "fs"
import * as csv2geojson from 'csv2geojson'
import {Converter} from "./converter"

class App {

    private readonly converter = new Converter()

    public async run() {
        try {
            let html: string = await this.readFile("resources/434.html")
            let csv: string = this.converter.convert(html)
            let geoJson: Object =  await this.convertCsvToGeoJson(csv)
            let json: string = JSON.stringify(geoJson, null, 2)
            console.log(json)
            // fs.writeFile("resources/434.json", json)
            
        } catch(err) {
            console.log(`error: ex ->`, err)
        }
    }

    private readFile(filePath: string): Promise<string> {
        return new Promise(function(resolve, reject) {
            fs.readFile(filePath, "utf-8", function(err, data) {
                if(err !== null) {
                    return reject(err);
                }

                resolve(data);
            })
        })
    }

    private convertCsvToGeoJson(csv: string): Promise<Object> {
        return new Promise(function(resolve, reject) {
            csv2geojson.csv2geojson(csv, function(err, data) {
                if (err) {
                    return reject(err);
                }

                resolve(data)
            })
        })
    }
}

function main() {
    new App().run()
}

main()