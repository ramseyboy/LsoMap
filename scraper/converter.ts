import * as cheerio from "cheerio"
import * as fs from "fs"

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
                matrix[i][j] = htmlTable(head).text().trim().replace(/(\r\n|\n|\r)/gm, "")
            });

            htmlTable(row).find('td').each((j, data) => {
                matrix[i][j] = htmlTable(data).text().trim()
            });
        });

        //filter out items that do not contain a switch identifier
        return matrix.filter(row => row[SWITCH_COLUMN].length)
    }

    private mapToCsv(table: Array<Array<string>>): string {
        return table.map(value => {
            return value.join(',')
        }).join('\n')
    }
}