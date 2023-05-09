## SpringBoot3 × Node.js(typescript, React)の自習用リポジトリ

gradleマルチプロジェクトで、SpringBoot3 × Node.js(typescript, React)の  
Webアプリケーションを作成するための自習用リポジトリです。  

クライアント側はReactのシングルページアプリケーション(javascript管理下)で動き、  
アプリケーションはログイン認証機能を持ち、サーバー側はAPIとして受付し、  
DBの操作を行うことができるアプリケーションを構築します。  
誰にでも理解しやすいシンプルな構成、設定であるものを構築したいと思います。   

自習が終わった頃には実務でも使えそうな一通りの技術は、何となく使えるようになっているかも。  

#### (扱う技術としては下記を考えています)
Java(JDK17), SpringBoot3, PostgreSQL 15, Gradle 7.6, Node.js, typescript, React18, html, css, Bootstrap5  

#### (使用ツール)
Visual Studio Code, pgAdmin 4  

## (やりたいこと)

#### (全体イメージ)  
![自習アプリ作成の概要](https://user-images.githubusercontent.com/32920703/218778468-d0df6e7e-dd67-4ebc-9114-6b9c745dd639.png)


#### (進め方と課題)  

GitHubのリポジトリにアップしているものはgradle buildが通っているものです。  
ログイン画面まで表示できます。

このコードを各サブプロジェクトの役割ごとに分散した時にgradle buildのエラーが発生するため、調査中。

(やることメモ)
・サーバー側のプログラムを一通り実装する。AOPによる例外処理を@AfterThrowingで実施する。  
validation無し: @RequestBody  
validation有り: @RequestBody @Validated  

・データ保存時の作成日時、更新日時の更新、競合チェック処理を追加する。  

・非同期処理に対応する。WebFluxと@Asyncの組み合わせ？(クライアント側はjavascript管理下で、axiosで対応予定。)

・Node.jsのnpmでnode_modulesをインストール、webpackを利用できるようにする。  
webpack.config.jsの設定をして、javascriptをモジュールとして使用できるようにする。    

・LoginUserの一覧・登録画面を準備する  
ユーザー登録、本人確認、パスワードリマインダーなど  

・メインのindex.htmlは共通テンプレートとして作成が必要。  

・Reactを利用してSPA対応、React routerで画面切り替えをできるようにする。  

・クライアント側の入力チェックの方法を検討(model_and_viewの設定値を渡すか、javascriptライブラリを併用)

・2段階認証を実装する。  


## 事前のインストール
事前のインストールとして下記を行います。  
Eclipseの利用は廃止し、Visual Studio Codeの利用に移行しました。
バージョンは現時点の最新版を記載します。  

インストーラーの設定については特に変わったことはしなく、標準通りです。  
GitHub Desktopは個人用の設定が必要になります。  
現在、GitHubの更新にはGitHub Desktopで操作しています。Visual Studio Codeで連携予定です。  

| 項目 | 説明 |
| :--- | :--- |
| Eclipse Adoptium Open JDK 17 | Java開発環境, SpringBootで推奨 |
| PostgreSQL 15 | 今回選択したデータベース |
| Visual Studio Code 1.78.0 | 開発ツール |
| GitHub Desktop | GitHubに簡単に接続するもの |
| Gradle 7.6 | SpringBootの初回マルチプロジェクトの構築で使用。実際はEclipseプGradleプラグインのほうを使用してます。 |
| Node.js 18.13.0 | javascriptライブラリの管理とWebpackを利用するものです。 |

## Visual Studio Codeの拡張機能・表示カスタマイズ
Visual Studio Codeの拡張機能・表示カスタマイズは下記を参照して下さい。  

gradle-multi-project/memo/1.vscodeプラグイン.txt  
gradle-multi-project/memo/2.エクスプローラーの表示カスタマイズ.txt  

## PostgreSQL設定  

Eclipseデバッグ実行時にコンソールで文字化けするので対策のため、  
postgresの「C:\Program Files\PostgreSQL\15\data/postgresql.conf」を修正します。  

```postgresql.conf
/# lc_messages = 'Japanese_Japan.932'
lc_messages = 'en_US'
```

変更後はサービスを再起動します。  

SpringBootで使用するデータベースを準備します。  

#### (データベース作成)
このリポジトリはdefaultで存在するpostgresを指定しています。  

## user-interfaceプロジェクトのDB接続準備
このSpringBootを実行するためにはDB接続設定が必要なため、   
user_interfaceプロジェクトのapplication.propertiesを編集します。  
ローカルPCのPostgreSQLに合わせた設定をして下さい。  

設定が正しくないとSpringBootは起動できません。(わかりにくいエラーが出ます)  

```application.properties
# Spring database configuration
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=xxxx
```

このコードがある状態でSpringBootをデバッグ起動すると、Entityがあって、DBに存在しないテーブルが自動で作成されます。  
※ただし不要なテーブルは削除されません。

## Gradle buildをする
Gradle buildは下記を参照して下さい。 

gradle-multi-project/memo/3.Gradleの使い方.txt  
gradle-multi-project/memo/4.gradleビルドエラーする時の確認すること.txt  

作業はビルドが通るか確認しながら進めて下さい。 

## 正しく準備できた場合のEclipse
Gradle build後、SPRING BOOT DASHBOARDからuser-interfaceプロジェクトを実行してデバッグします。  

「http://localhost:8080」 を開くとログイン画面が表示されます。  


## SpringBootプロジェクトでのNode.jsの利用方法
調査中。  
 
