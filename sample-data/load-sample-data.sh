#!/bin/bash

mongoimport --db greetings --collection greetings --drop --jsonArray --file ./sample-data.json

mongoimport --db greetings --collection counter --drop --jsonArray --file ./sample-data3.json