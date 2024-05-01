let path = require('path');

module.exports = {
  entry: './src/main/main.jsx',
  devtool: 'cheap-module-source-map', // or 'eval-source-map'
  cache: true,
  mode: 'development',
  output: {
    path: path.join(__dirname, '/target/generated-test-sources/bundled-src/'),
    filename: 'bundle.js'
  },
  devServer: {
    port: 3010,
  },
  resolve: {
    extensions: ['.js', '.jsx'],
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /(node_modules)/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: ["@babel/preset-env", "@babel/preset-react"]
          }
        }]
      },
      {
        test: /\.s[ac]ss$/i,
        use: [
          // Creates `style` nodes from JS strings
          "style-loader",
          // Translates CSS into CommonJS
          "css-loader",
          // Compiles Sass to CSS
          "sass-loader",
        ],
      }
    ]
  }
};
