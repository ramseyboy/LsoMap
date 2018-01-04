let cheerio = require("cheerio");
let urllib = require("url");

const SWITCH_COLUMN = 4;

function convert(html) {

    let table = cheerio.load(html);
    let matrix = createSwitchMatrix(table);

    return mapToCsv(matrix)
}

function createSwitchMatrix(htmlTable) {
    let matrix = [];

    htmlTable("table tr").each((i, row) => {
        matrix[i] = [];

        htmlTable(row).find('th').each((j, head) => {
            let headerText = htmlTable(head).text().trim().replace(/(\r\n|\n|\r)/gm, "");
            if (headerText === 'Map') {
                matrix[i][j] = 'Latitude';
                matrix[i][j + 1] = 'longitude'
            } else {
                matrix[i][j] = headerText
            }
        });

        htmlTable(row).find('td').each((j, data) => {
            let innerText = htmlTable(data).text().trim();
            if (innerText === 'map') {
                let coordsUrl = htmlTable(data).children().attr('href');
                if (coordsUrl) {
                    let latlng = extractCoords(coordsUrl);
                    matrix[i][j] = latlng.lat;
                    matrix[i][j + 1] = latlng.lng
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

function extractCoords(coordsUrl) {
    let urlParts = urllib.parse(coordsUrl, true);
    let loc = urlParts.query['q'];
    let coords = loc.split(":")[1];

    let latlngArr = coords.split(",");
    return {
        "lat": latlngArr[0],
        "lng": latlngArr[1]
    };
}

function mapToCsv(table) {
    return table.map(value => {
        return value.join(',')
    }).join('\n')
}

module.exports = convert;