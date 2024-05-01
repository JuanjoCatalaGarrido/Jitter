let path = require('path');

module.exports = {
  entry: './src/main/main.jsx',
  devtool: 'cheap-module-source-map', // or 'eval-source-map'
  cache: true,
  mode: 'development',
  output: {
    path: __dirname,
    filename: './target/generated-test-sources/compiled-js/bundle.js'
  },
  resolve: {
    extensions: ['.js', '.jsx']
  },
  module: {
    rules: [
      {
        test: path.join(__dirname, '.'),
        exclude: /(node_modules)/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: ["@babel/preset-env", "@babel/preset-react"]
          }
        }]
      }
    ]
  }
};
