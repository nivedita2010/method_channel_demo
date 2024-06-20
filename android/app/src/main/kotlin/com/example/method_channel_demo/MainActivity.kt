package com.example.method_channel_demo

import android.annotation.SuppressLint
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlin.random.Random

class MainActivity: FlutterActivity() {


    @SuppressLint("SuspiciousIndentation")
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        flutterEngine?.dartExecutor?.binaryMessenger?.
        let { MethodChannel(it,"demo.method").setMethodCallHandler { call, result ->
            if(call.method=="button_click"){
                Log.d("native call","button click called")
            }
        } }
        flutterEngine?.dartExecutor?.binaryMessenger?.
        let { MethodChannel(it,"demo.method").setMethodCallHandler { call, result ->
            if(call.method=="getRandomNumber"){
              val rand = Random.nextInt(100)
                result.success(rand)
            }
            else {result.notImplemented()}
        } }



    }
    override fun onStop() {
        super.onStop()


        flutterEngine?.dartExecutor?.binaryMessenger?.let { MethodChannel(it,"demo.method").invokeMethod("stop",null) }
    }

    override fun onStart() {
        super.onStart()
        flutterEngine?.dartExecutor?.binaryMessenger?.let { MethodChannel(it,"demo.method").invokeMethod("start",null) }
    }

}
