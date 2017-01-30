var path = require('path');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var merge = require('extendify')({ isDeep: true, arrays: 'concat' });
var devConfig = require('./webpack.config.dev');
var prodConfig = require('./webpack.config.prod');
var isDevelopment = process.env.ASPNETCORE_ENVIRONMENT === 'Development';
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
        main: ['./src-client/exchange.ts']
    },
    output: {
        path: path.join(__dirname, 'src/wwwroot', 'dist'),
        filename: 'bundle.js',
        publicPath: '/dist/'
    },
    plugins: [
        extractCSS,
        new webpack.DllReferencePlugin({
            context: __dirname,
            manifest: require('./src/wwwroot/dist/vendor-manifest.json')
        })
    ]
}, isDevelopment ? devConfig : prodConfig);