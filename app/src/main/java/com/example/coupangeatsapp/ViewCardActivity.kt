package com.example.coupangeatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.get

class ViewCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card)

        //인텐트에 값 받아오기
        val image = intent.getIntExtra("image",0)
        val name = intent.getStringExtra("name")
        val time = intent.getStringExtra("time")
        val score = intent.getStringExtra("score")

        //Id 설정
        var imageView = findViewById<ImageView>(R.id.cardimageView)
        val nameText = findViewById<TextView>(R.id.cd_name_textView)
        val timeText = findViewById<TextView>(R.id.cd_time_TextView)
        val scoreText = findViewById<TextView>(R.id.cd_score_TextView)

        imageView.setImageResource(image)
        nameText.text = name
        timeText.text = time
        scoreText.text = score

        val order_btn = findViewById<Button>(R.id.btn_order)
        val back_btn = findViewById<Button>(R.id.btn_back)

        order_btn.setOnClickListener {
            Toast.makeText(this,"미구현!",Toast.LENGTH_SHORT).show()
        }
        back_btn.setOnClickListener {
            finish()
        }
    }
}