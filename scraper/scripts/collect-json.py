#! /usr/bin/env python3

import os
import sys

jsonDir = "../out"

outFilePath = "../out/collected-switches.json"
outFile = open(outFilePath, 'w+')

for root, subdirs, files in os.walk(jsonDir):
    for filePath in files:
        jsonFile = open(root + "/" + filePath)
        outFile.write(jsonFile.read())
    