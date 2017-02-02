rm -rf src/main/resources/static/comm/

echo "Removed comm"
sleep 1

rm -rf src/main/resources/static/mobi

echo  "Removed static/mobi"
sleep 1

rm -rf src/main/resources/static/WEB-INF

echo "Removed static/WEB-INF"
sleep 1


mvn clean install
