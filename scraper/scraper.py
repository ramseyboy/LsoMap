#!/usr/bin/env python 
from lxml import html, etree

if __name__ == "__main__":

    doc = html.fromstring(open('434.html').read())
    out = open('434.xhtml', 'wb')
    out.write(etree.tostring(doc))

    dom = etree.parse("434.xhtml")
    xslt = etree.parse("convert.xslt")
    transform = etree.XSLT(xslt)
    newdom = transform(dom)

    csv = open('434_1.csv', 'wb')
    csv.write(etree.tostring(newdom, pretty_print=True))
