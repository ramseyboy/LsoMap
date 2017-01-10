var path = require('path');

module.exports = {
    entry: './wwwroot/js/exchange.js',
    output: {
        path: path.join(__dirname, 'wwwroot/build'),
        filename: 'bundle.js'
    },
    devtool: 'inline-source-map',
    module: {
        loaders: [
            {
                test: /\.js/,
                loader: 'babel-loader',
                include: __dirname + '/src'
            }
        ]
    }
};