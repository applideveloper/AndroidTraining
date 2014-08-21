Practice Report for 2/8
------

実習のレポートを下記に記述してください

### Service

1. サンプルプロジェクト (ServiceSample) に、サービスのライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように表示されるかをレポートしてください。

Started Servicde Control
Startボタンクリック
08-21 04:11:20.782    1367-1367/jp.mixi.sample.service V/StartedService﹕ onCreate
08-21 04:11:20.782    1367-1367/jp.mixi.sample.service V/StartedService﹕ onStartCommand
Destroyボタンクリック
08-21 04:11:38.430    1367-1367/jp.mixi.sample.service V/StartedService﹕ onDestroy

Bound Service Control
Bindボタンクリック
08-21 04:14:35.078    1367-1367/jp.mixi.sample.service V/BoundService﹕ onCreate
08-21 04:14:35.090    1367-1367/jp.mixi.sample.service V/MainActivity﹕ onServiceConnected
Unbindボタンクリック
08-21 04:16:32.926    1367-1367/jp.mixi.sample.service V/BoundService﹕ onUnbind
08-21 04:16:32.926    1367-1367/jp.mixi.sample.service V/BoundService﹕ onDestroy

Intent Service Control
Call IntentServiceクリック
08-21 04:17:23.138    1367-1367/jp.mixi.sample.service V/MyIntentService﹕ onCreate
08-21 04:17:23.138    1367-1367/jp.mixi.sample.service V/MyIntentService﹕ onStartCommand
08-21 04:17:23.138    1367-1386/jp.mixi.sample.service V/MyIntentService﹕ onHandleIntent
08-21 04:17:23.138    1367-1367/jp.mixi.sample.service V/MyIntentService﹕ onDestroy


### Loader

1. サンプルプロジェクト (LoaderSample) に、AsyncTaskLoader のライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように出力されているかをレポートしてください。
08-21 04:46:00.950    1470-1470/jp.mixi.sample.loader V/MainActivity﹕ onCreateLoader
08-21 04:46:00.954    1470-1470/jp.mixi.sample.loader V/MyAsyncTaskLoader﹕ onStartLoading
08-21 04:46:01.002    1470-1483/jp.mixi.sample.loader V/MyAsyncTaskLoader﹕ loadInBackground
08-21 04:46:02.010    1470-1470/jp.mixi.sample.loader V/MyAsyncTaskLoader﹕ deliverResult
08-21 04:46:02.010    1470-1470/jp.mixi.sample.loader V/MainActivity﹕ onLoadFinished


### AsyncTask

1. `AsyncTask#doInBackground()` で、TextView の文字を変更するような、UI の処理を実行するとどうなるかを、理由を添えてレポートしてください。
単に
					Toast.makeText(mApplicationContext, "doInBackground", Toast.LENGTH_SHORT).show();
					textView.setText("非同期処理実行");
とだけかくと
	 java.lang.RuntimeException: An error occured while executing doInBackground()
 Caused by: java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
 というエラーがでて
 落ちる理由は
 AndroidではUIへの変更はUIスレッド以外から行うことができないため
 AsyncTaskはジェネリックなクラスで
AsyncTask<Params, Progress, Result>
と3つの型変数をもつ。中心となるメソッドは
 protected abstract Result doInBackground(Params... params)
で、このメソッドの部分が非同期実行される。これはUIスレッドではない。
onProgressUpdate()にRunnableを渡す作りにすると随時UIスレッドを呼べて落ちなくなった。
 
 http://d.hatena.ne.jp/Nagise/20120309/1331265123
 