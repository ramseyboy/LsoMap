import * as fs from "fs"
import * as c from "./converter"

function main() {
    fs.readFile("434.html", "utf-8", function (err, data) {
        if (err) throw err

        let converter = new c.Converter()
        let csv = converter.convert(data)
        console.log(csv)
    })
}

function writeCsvFile(csv: string): void {
    if (fs.exists('434.csv')) {
        fs.unlink('434.csv')
    }
    
    fs.appendFile('434.csv', csv)
}

main()