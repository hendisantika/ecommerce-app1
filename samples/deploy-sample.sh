#!/bin/bash
ssh -p "${SERVER_PORT}" "${SERVER_USERNAME}"@"${SERVER_HOST}" -i key.txt -t -t -o StrictHostKeyChecking=no << 'ENDSSH'
cd ~/ecommerce
cat .env
set +a
source .env
start=$(date +"%s")
echo $YOUR_PERSONAL_ACCESS_TOKEN | docker login --username $DOCKER_USERNAME --password-stdin
docker pull $CONTAINER_REPOSITORY:$IMAGE_TAG

if [ "$(docker ps -qa -f name=$CONTAINER_NAME)" ]; then
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        echo "Container is running -> stopping it..."
        docker system prune -af
        docker stop $CONTAINER_NAME;
        docker rm $CONTAINER_NAME
    fi
fi

docker run -d --restart unless-stopped -p $APP_PORT:$APP_PORT --env-file .env --name $CONTAINER_NAME  $CONTAINER_REPOSITORY:$IMAGE_TAG
# $CONTAINER_REPOSITORY = hendisantika/ecommerce
docker ps
exit
ENDSSH

if [ $? -eq 0 ]; then
  exit 0
else
  exit 1
fi

end=$(date +"%s")

diff=$(($end - $start))

echo "Deployed in : ${diff}s"