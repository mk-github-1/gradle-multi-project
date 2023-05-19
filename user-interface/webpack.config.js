// Webpack5以降の設定です。

// SPAはhtmlを出力しないので不要
// const HtmlWebpackPlugin = require('html-webpack-plugin');
// import HtmlWebpackPlugin from 'html-webpack-plugin';

// Pluginは自動的に検出
const path = require('path');

module.exports = {
	// バンドルするファイルに影響するプロパティ。
	mode: 'development',	// 'production'

	// どのファイルを起点にバンドルするかを設定するプロパティ。
	entry: path.resolve(__dirname, 'frontend/src/index.tsx'),

	//　どのディレクトリにバンドルしたファイルを出力するかを設定するプロパティ。
	output: {
		filename: 'bundle.js',
		path: path.resolve(__dirname, 'src/main/resources/static/dist')
	},

	// インポート時にのパスの問題(絶対パスや相対パス)を解決するプロパティ。
	resolve: {
		extensions: ['.tsx', '.ts', '.js', '.jsx'],	// '.json'
	},

	//　ts-loaderやcss-loaderといった Loader の設定を行うプロパティ。
	module: {
		rules: [
			// TypeScriptファイルのローダー設定
			{
				test: /\.(ts|tsx)$/,
				exclude: /node_modules/,
				use: [
					{
						loader: 'ts-loader',
						options: {
							configFile: path.resolve(__dirname, 'tsconfig.json')
						}
					}
				]
			},
			// JavaScriptファイルのローダー設定
			{
				test: /\.(js|jsx)$/,
				exclude: /node_modules/,
				use: [
					{
						loader: 'babel-loader',
						options: {
							// Babelのプリセットを指定する
							presets: ['@babel/preset-env', '@babel/preset-react']
						}
					}
				]
			},
			// ag-gridのCSSファイルのローダー設定
			{
				test: /ag-grid\.css$/,
				use: ['style-loader', 'css-loader'],
			},
			// BootstrapのCSSファイルのローダー設定
			{
				test: /bootstrap\.css$/,
				use: ['style-loader', 'css-loader'],
			},
			// その他のCSSファイルのローダー設定
			{
				test: /\.css$/,
				exclude: [/bootstrap\.css$/, /ag-grid\.css$/],
				use: ['style-loader', 'css-loader'],
			},
			// 画像ファイルのローダー設定
			{
				test: /\.(png|jpg|jpeg|gif|bmp|tiff|svg)$/,
				use: ['file-loader']
			},
		]
	},

	/* 不要？
	plugins: [
		new HtmlWebpackPlugin({
			template: 'src/main/resources/static/dist/index.html'
		})
		],
	 */

	// webpack-dev-serverの設定を行うプロパティ。
	devServer: {
		compress: true,
		port: 3000,
	},

	// サーバー側(Node.js)とブラウザ側(フロント)どちらにコンパイルするかを設定するプロパティ。
	target: 'web',
};
