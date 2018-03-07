# Build
mvn clean package && docker build -t com.airhacks/marketing .

# RUN

docker rm -f marketing || true && docker run -d -p 8080:8080 -p 4848:4848 --name marketing com.airhacks/marketing 