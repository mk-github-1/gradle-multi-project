// Webpack5以降の設定です。

// Pluginは自動的に検出
const path = require('path');

module.exports = {
	// バンドルするファイルに影響するプロパティ。
	mode: 'development',	// 'production'

	// どのファイルを起点にバンドルするかを設定するプロパティ。
	entry: './src/index.tsx',

	//　どのディレクトリにバンドルしたファイルを出力するかを設定するプロパティ。
	output: {
		filename: 'bundle.js',
		// path: path.join(__dirname, 'dist'),
		path: path.resolve(__dirname, 'src', 'main', 'resources', 'static')
	},

	// インポート時にのパスの問題(絶対パスや相対パス)を解決するプロパティ。
	resolve: {
		extensions: ['.tsx', '.ts', '.js', '.jsx'],	// '.json'
	},

	//　babel-loaderやcss-loaderといった Loader の設定を行うプロパティ。
	module: {
		rules: [
			{
				test: /\.(ts|tsx)$/,
        		exclude: /node_modules/,
				use: [
					{
						loader: 'babel-loader',
						options: {
							presets: ['@babel/preset-env', '@babel/preset-react']
						},
					},
					{
						loader: 'ts-loader',
						options: {
							configFile: path.resolve(__dirname, 'tsconfig.json'),
						},
					},
				]
			},
    		{
      			test: /\.(js|jsx)$/,
      			exclude: /node_modules/,
      			use: [
					{
        				loader: 'babel-loader',
        				options: {
          					presets: ['@babel/preset-env', '@babel/preset-react'],
        				},
        			}
      			],
    		},
			{
				test: /\.css$/,
        		exclude: /node_modules/,
				use: ['style-loader', 'css-loader']	// 'sass-loader'
			},
		]
	},

  	plugins: [
    	new HtmlWebpackPlugin({
    		template: './public/index.html'
    	})
  	],

	// webpack-dev-serverの設定を行うプロパティ。
	devServer: {
	    // contentBase: path.join(__dirname, 'dist'),
	    static: {
    		directory: path.join(__dirname, 'dist'),
  		},
	    compress: true,
		port: 3000,
	},

	// サーバー側(Node.js)とブラウザ側(フロント)どちらにコンパイルするかを設定するプロパティ。
	target: 'web',
};