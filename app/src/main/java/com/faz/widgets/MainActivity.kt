package com.faz.widgets

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {
            while (true) {
                progressBar.post {
                    if (progressBar.curValue == progressBar.maxValue) {
                        progressBar.curValue = 0
                    } else {
                        progressBar.curValue++
                    }
                }
                Thread.sleep(300)
            }
        }
    }
}
