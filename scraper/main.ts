import * as fs from "fs"
import * as csv2geojson from 'csv2geojson'
import {Converter} from "./converter"
import axios, {
  AxiosResponse
} from "axios"
import * as sleep from "sleep"

class App {

    private static readonly url: string = "http://localcallingguide.com/lca_prefix.php"
    private static readonly out_dir: string = "out/"
    private static readonly crawl_delay: number = 5000
    private readonly converter = new Converter()

    public async run() {
        try {
            if (!fs.existsSync(App.out_dir)) {
                fs.mkdirSync(App.out_dir)
            }

            let areacodes: string = await this.readFile("resources/areacodes.csv")
            for (let line of areacodes.split("\n")) {
                let areaCode = line.split(",")[0]
                if (Number(areaCode)) {
                    await this.paginateAreaCodes(areaCode)
                }                
            }
            
        } catch(err) {
            console.log(`error: ex ->`, err)
            process.exit(1)
        }
    }

    private async paginateAreaCodes(areaCode: string) {
        var index = 1
            while (true) {
                try {
                    let html: AxiosResponse = await axios.get(App.url, {
                        params: {
                            page: index,
                            npa: areaCode
                        }
                    })

                    let csv: string = this.converter.convert(html.data)
                    let geoJson = await this.convertCsvToGeoJson(csv)
                    // if we dont have any valid features, we are out of pages
                    if (geoJson.features.length == 0) {
                        break
                    }

                    await this.writeJsonToDisk(geoJson, areaCode, index)
                } catch(err) {
                    console.log(`Error creating ${areaCode}_${index}.json`)
                }
                index++
            }
    }

    private async writeJsonToDisk(geoJson: string, areaCode: string, index: number) {
        let json: string = JSON.stringify(geoJson, null, 2)
        
        let areaCodeDir = App.out_dir + areaCode
        if (!fs.existsSync(areaCodeDir)) {
            fs.mkdirSync(areaCodeDir)
        }

        fs.writeFileSync(`${areaCodeDir}/${areaCode}_${index}.json`, json)
        sleep.msleep(App.crawl_delay)
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

    private convertCsvToGeoJson(csv: string): Promise<any> {
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