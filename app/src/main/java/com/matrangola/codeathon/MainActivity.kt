package com.matrangola.codeathon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mu.KotlinLogging

val log = KotlinLogging.logger {  }
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            log.info { "onClick: ${editText.text}" }
            textOut.text = "Message: ${editText.text}"
            toast("Message : ${editText.text}")

            Thread{
                Thread.sleep(2000)
                log.info {"In thread"}
                runOnUiThread {
                    log.info {"In UI thread"}
                    textOut.text = "again: ${editText.text}"

                    val intent = Intent(this, MessageActivity::class.java)
                    intent.putExtra("Message", editText.text.toString())
                    startActivity(intent)
                }
            }.start()
        }
    }

    override fun onResume() {
        super.onResume()
        log.info{"onResume"}
    }

    override fun onPause() {
        super.onPause()
        log.info { "onPause" }
    }
}
