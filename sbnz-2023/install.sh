cd model || exit
mvn clean install
cd ../kjar || exit
mvn clean install
cd ../service || exit
mvn clean install
cd ..

