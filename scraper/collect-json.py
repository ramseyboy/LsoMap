#! /usr/bin/env python3

import json
from argparse import ArgumentParser, FileType
from re import compile
import os

float_pat = compile(r'^-?\d+\.\d+(e-?\d+)?$')
charfloat_pat = compile(r'^[\[,\,]-?\d+\.\d+(e-?\d+)?$')

parser = ArgumentParser(description="Group (merge) multiple GeoJSON files.")

defaults = dict(precision=6)

parser.set_defaults(**defaults)

parser.add_argument('-p', '--precision', dest='precision',
                    type=int, help='Digits of precision')

if __name__ == '__main__':
    args = parser.parse_args()

    jsonDir = "out/"

    outjson = dict(type='FeatureCollection', features=[])

    for root, subdirs, files in os.walk(jsonDir):
        for infile in files:
            injson = json.loads(open(root + "/" + infile).read())

            if injson.get('type', None) != 'FeatureCollection':
                raise Exception('Sorry, "%s" does not look like GeoJSON' % infile)

            if type(injson.get('features', None)) != list:
                raise Exception('Sorry, "%s" does not look like GeoJSON' % infile)
            try:
                outjson['features'] += injson['features']
            except:
                outjson['features'] += injson

    encoder = json.JSONEncoder(separators=(',', ':'))
    encoded = encoder.iterencode(outjson)

    format = '%.' + str(args.precision) + 'f'

    outFilePath = "collected-switches.json"
    outFile = open(outFilePath, 'w+')
    output = outFile

    for token in encoded:
        if charfloat_pat.match(token):
            # in python 2.7, we see a character followed by a float literal
            output.write(token[0] + format % float(token[1:]))

        elif float_pat.match(token):
            # in python 2.6, we see a simple float literal
            output.write(format % float(token))

        else:
            output.write(token)
