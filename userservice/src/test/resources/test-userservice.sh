#!/bin/bash

# clear redis cache.
echo -e 'clearing redis.'
echo 'flushdb' | redis-cli

# execute createUser method
echo -e 'creating users via rest.'
curl -X PUT -H "Content-Type: application/json" -d '{"login": "tklein", "firstName": "Thomas","lastName": "Klein"}' http://127.0.0.1:8080/createUser
curl -X PUT -H "Content-Type: application/json" -d '{"login": "bschmitz", "firstName": "Bernhard","lastName": "Schmitz"}' http://127.0.0.1:8080/createUser
curl -X PUT -H "Content-Type: application/json" -d '{"login": "kfischer", "firstName": "Karl","lastName": "Fischer"}' http://127.0.0.1:8080/createUser

# check redis
echo -e 'stored in redis:'
echo 'keys *' | redis-cli
echo 'get kfischer' | redis-cli
echo 'get tklein' | redis-cli
echo 'get bschmitz' | redis-cli

# execute getUser method
echo -e 'get users from redis via rest'
curl http://127.0.0.1:8080/getUser?login=tklein
echo -e
curl http://127.0.0.1:8080/getUser?login=kfischer
echo -e
curl http://127.0.0.1:8080/getUser?login=bschmitz

# clear redis again
echo -e '\nclearing redis.'
echo 'flushdb' | redis-cli
