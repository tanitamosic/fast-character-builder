#!/bin/bash
cd model && mvn clean install
cd ../kjar && mvn clean install
cd ../service && mvn clean install
cd ..
