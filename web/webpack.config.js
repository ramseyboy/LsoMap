var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var merge = require('extendify')({ isDeep: true, arrays: 'concat' });
var devConfig = require('./webpack.config.dev');
var prodConfig = require('./webpack.config.prod');
var isDevelopment = true;
var extractCSS = new ExtractTextPlugin('exchange.css');

module.exports = merge({
    resolve: {
        extensions: [ '', '.js', '.jsx', '.ts', '.tsx' ],
        alias: {
            leaflet_css: __dirname + "/node_modules/leaflet/dist/leaflet.css"
        }
    },
    module: {
        loaders: [
            { test: /\.ts(x?)$/, include: /src-client/, loader: 'ts-loader?silent=true' },
            { test: /\.css/, loader: extractCSS.extract(['css-loader']) },
            {
                test: /\.png$/,
                loader: 'url-loader',
                query: { mimetype: 'image/png' }
            }
        ]
    },
    entry: {
        main: ['./src-client/index.tsx']
    },
    output: {
        path: path.join(__dirname, 'src/public', 'dist'),
        filename: 'bundle.js',
        publicPath: '/dist/'
    },
    plugins: [
        extractCSS
    ]
}, isDevelopment ? devConfig : prodConfig);