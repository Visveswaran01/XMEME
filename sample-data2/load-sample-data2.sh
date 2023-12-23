#!/bin/bash

mongoimport --db greetings --collection memes --drop --jsonArray --file ./sample-data2.json