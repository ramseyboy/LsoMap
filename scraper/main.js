let fs = require("fs");
let csv2geojson = require("csv2geojson");
let axios = require("axios");
let sleep = require("sleep");

let convert = require("./converter");

let url = "http://localcallingguide.com/lca_prefix.php";
let out_dir = "out/";
let crawl_delay = 5000;

async function paginateAreaCodes(areaCode) {
    var index = 1;
    while (true) {
        try {
            let html = await axios.get(url, {
                params: {
                    page: index,
                    npa: areaCode
                }
            });

            let csv = convert(html.data);
            let geoJson = await convertCsvToGeoJson(csv);
            // if we dont have any valid features, we are out of pages
            if (geoJson.features.length == 0) {
                break
            }

            await writeJsonToDisk(geoJson, areaCode, index)
        } catch (err) {
            console.log(`Error creating ${areaCode}_${index}.json, err -> ${err}`)
        }
        index++
    }
}

async function writeJsonToDisk(geoJson, areaCode, index) {
    let json = JSON.stringify(geoJson, null, 2);

    let areaCodeDir = out_dir + areaCode;
    if (!fs.existsSync(areaCodeDir)) {
        fs.mkdirSync(areaCodeDir)
    }

    fs.writeFileSync(`${areaCodeDir}/${areaCode}_${index}.json`, json);
    sleep.msleep(crawl_delay)
}

function readFile(filePath) {
    return new Promise(function (resolve, reject) {
        fs.readFile(filePath, "utf-8", function (err, data) {
            if (err !== null) {
                return reject(err);
            }

            resolve(data);
        })
    })
}

function convertCsvToGeoJson(csv) {
    return new Promise(function (resolve, reject) {
        csv2geojson.csv2geojson(csv, function (err, data) {
            if (err) {
                return reject(err);
            }

            resolve(data)
        })
    })
}

async function main() {
    try {
        if (!fs.existsSync(out_dir)) {
            fs.mkdirSync(out_dir)
        }

        let areacodes = await readFile("resources/areacodes.csv");
        for (let line of areacodes.split("\n")) {
            let areaCode = line.split(",")[0];
            if (Number(areaCode)) {
                await paginateAreaCodes(areaCode)
            }
        }

    } catch (err) {
        console.log(`error: ex ->`, err);
        process.exit(1)
    }
}

main();