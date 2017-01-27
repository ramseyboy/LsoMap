import * as cheerio from "cheerio"
import {Url, parse} from "url"

const SWITCH_COLUMN = 4

export class Converter {

    public convert(html: string) {

        let table = cheerio.load(html)
        let matrix = this.createSwitchMatrix(table)

        return this.mapToCsv(matrix)
    }

    private createSwitchMatrix(htmlTable): Array<Array<string>> {
        let matrix: string[][] = []

        htmlTable("table tr").each((i, row) => {
            matrix[i] = new Array<string>()

            htmlTable(row).find('th').each((j, head) => {
                let headerText: string = htmlTable(head).text().trim().replace(/(\r\n|\n|\r)/gm, "")
                if (headerText === 'Map') {
                    matrix[i][j] = 'Latitude'
                    matrix[i][j+1] = 'longitude'
                } else {
                    matrix[i][j] = headerText
                }
            });

            htmlTable(row).find('td').each((j, data) => {
                let innerText: string = htmlTable(data).text().trim()
                if (innerText === 'map') {
                    let coordsUrl: string = htmlTable(data).children().attr('href')
                    if (coordsUrl) {
                        let latlngMap: Map<string, string> = this.extractCoords(coordsUrl)
                        matrix[i][j] = latlngMap.get("lat")
                        matrix[i][j+ 1] = latlngMap.get("lng")
                    } else {
                        matrix[i][j] = ''
                    }
                } else {
                    matrix[i][j] = innerText.replace(',', '')
                }
            });
        });

        //filter out items that do not contain a switch identifier
        return matrix.filter(row => row[SWITCH_COLUMN].length)
    }

    private extractCoords(coordsUrl: string): Map<string, string> {
        let urlParts: Url = parse(coordsUrl, true);
        let loc: string = urlParts.query['q']
        let coords: string = loc.split(":")[1]
        
        let latlngArr: string[] = coords.split(",")

        let latlng: Map<string, string> = new Map<string, string>();
        latlng.set("lat", latlngArr[0])
        latlng.set("lng", latlngArr[1])
        return latlng
    }

    private mapToCsv(table: Array<Array<string>>): string {
        return table.map(value => {
            return value.join(',')
        }).join('\n')
    }
}