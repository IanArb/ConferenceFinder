config.devServer = config.devServer || {
    proxy: {
        '/conferences/*': {
            target: "http://localhost:8080"
        }
    }
};
config.devServer.port = 8080;
config.devServer.watchOptions = {
    "aggregateTimeout": 3004,
    "poll": 1000
};
config.devServer.open = false;