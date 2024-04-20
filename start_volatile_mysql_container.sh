docker run --rm \
	-e MYSQL_ROOT_PASSWORD='' \
	-e MYSQL_DATABASE=Jitter \
	-e MYSQL_USER=jitter \
	-e MYSQL_PASSWORD=jitter \
	-e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
	-d mysql:latest

