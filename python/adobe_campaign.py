#!/usr/bin/env python3
'''
  You are given this file for code review.
  Are there any areas that can be improved?
  What would you change / do differently?
'''
import csv
from flask import Flask
from prettytable import from_csv
import yaml
import json

application = Flask(__name__)

@application.route('/list')
def list_items():
    item_table = from_csv(open('items.csv')).get_html_string()
    return item_table

@application.route('/list/json')
def list_json():
    with open('items.csv', 'r+') as x:
        rowlist = csv.reader(x)
        next(rowlist)
        dict = {row[0]:[row[1],row[2],row[3]] for row in rowlist}
    return json.dumps(dict)

@application.route('/SKU/<sku>')
def list_item(sku):
    with open('items.csv', 'r+') as x:
        rowlist = csv.reader(x)
        next(rowlist)
        dict = {row[0]:[row[1],row[2],row[3]] for row in rowlist}
        itemsummary = "{}: | Price: ${} | Cost Basis: {}".format(dict[sku][0], dict[sku][1], dict[sku][2])
    return itemsummary

@application.route('/ping')
def health():
    return "OK"

if __name__ == '__main__':
    application.run(host='0.0.0.0', port=80, debug=True)



# items.csv
'''
SKU, Name, Price per foot, Cost
1, Hemp Rope, 5.50, 0.90
2, Nylon Rope, 4.00, 0.20
3, Paracord, 2.50, 0.50
4, Cotton Twine, 0.35, 0.04
5, Cotton String, 0.38, 0.06
6, Nylon Thread, 0.25, 0.01
7, Sailcloth, 20.00, 2.50
8, Jute Thread, 0.50, 0.05
'''

# unused yaml import
# redundant code in dict (however this could be used to short circuit the read)
# some write lock on first file open
# this is single thread (flask, how to fix?)
# runs as root bec of port 80 - inputs are not obviously scrubbed
# function names need to be better named
# health doesnt show actual health, could check for file.
