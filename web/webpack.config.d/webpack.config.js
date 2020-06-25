config.devServer = config.devServer || {
};
config.devServer.port = 8081;
config.devServer.watchOptions = {
    "aggregateTimeout": 8081,
    "poll": 1000
};
config.devServer.open = false;