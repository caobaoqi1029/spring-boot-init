# docker

```yaml
services:
    mysql:
        image: mysql:8.0.39
        restart: "no"
        ports:
            - "3306:3306"
        volumes:
            - mysql_data:/var/lib/mysql
            - ./sql:/docker-entrypoint-initdb.d:ro
        environment:
            MYSQL_ROOT_PASSWORD: root
            MYSQL_DATABASE: db
        healthcheck:
            test: mysqladmin ping -u root -p $$MYSQL_ROOT_PASSWORD
            interval: 10s
            timeout: 5s
            start_period: 10s
            retries: 5
    redis:
        image: redis:7.4.0
        restart: "no"
        ports:
            - "6379:6379"
        healthcheck:
            test: redis-cli ping
            interval: 10s
            timeout: 5s
            start_period: 10s
            retries: 5
    mongo:
        image: mongodb/mongodb-community-server:latest
        restart: "no"
        ports:
            - "27017:27017"
        volumes:
            - mongo_data:/data/db
            -   type: bind
                source: mongo
                target: /docker-entrypoint-initdb.d
                read_only: true
        environment:
            MONGO_INITDB_ROOT_USERNAME: root
            MONGO_INITDB_ROOT_PASSWORD: root
            MONGO_INITDB_DATABASE: db
        healthcheck:
            test: echo 'db.runCommand({serverStatus:1}).ok' | mongosh admin -u $$MONGO_INITDB_ROOT_USERNAME -p $$MONGO_INITDB_ROOT_PASSWORD --quiet | grep 1
            interval: 10s
            timeout: 5s
            start_period: 10s
            retries: 5
        labels:
            amplicode.image: mongo
    mongoexpress:
        image: mongo-express:1.0.2
        restart: "no"
        ports:
            - "8081:8081"
        environment:
            ME_CONFIG_BASICAUTH_USERNAME: root
            ME_CONFIG_BASICAUTH_PASSWORD: root
            ME_CONFIG_MONGODB_URL: mongodb://root:root@mongo:27017/
        healthcheck:
            test: wget --no-verbose --tries=1 --spider http://localhost:8081/status || exit -1
            interval: 10s
            timeout: 5s
            start_period: 60s
            retries: 5
        depends_on:
            - mongo
    rabbitmq:
        image: rabbitmq:4.0.2
        restart: "no"
        ports:
            - "5432:5432"
        healthcheck:
            test: rabbitmq-diagnostics -q status
            interval: 10s
            timeout: 5s
            start_period: 10s
            retries: 5
    elasticsearch:
        image: elasticsearch:8.15.2
        restart: "no"
        ports:
            - "9200:9200"
        environment:
            ELASTIC_PASSWORD: root
            xpack.security.enabled: "false"
            bootstrap.memory_lock: "true"
            discovery.type: "single-node"
        healthcheck:
            test: curl -u --fail -s http://localhost:9200 || exit 1
            interval: 10s
            timeout: 5s
            start_period: 10s
            retries: 5
        ulimits:
            memlock:
                soft: -1
                hard: -1
volumes:
    mysql_data:
    mongo_data:

```