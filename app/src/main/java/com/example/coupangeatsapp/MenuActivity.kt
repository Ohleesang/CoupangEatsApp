package com.example.coupangeatsapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.cardview.widget.CardView
import java.io.ByteArrayOutputStream

class MenuActivity : AppCompatActivity() {

    val searchView: SearchView? by lazy { findViewById<SearchView>(R.id.searchView) }
    val cards : MutableList<CardView> = mutableListOf()
    val images : MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initCardView()
        googleSearch()

        cards.forEach {
            it.setOnClickListener{
                val idx = cards.indexOf(it)
                val image = images[idx]
                val name = it.findViewById<TextView>(R.id.cd_name_textView).text
                val time = it.findViewById<TextView>(R.id.cd_time_TextView).text
                val score = it.findViewById<TextView>(R.id.cd_score_TextView).text
                //명시적 인텐트
                val intent = Intent(this,ViewCardActivity::class.java)
                intent.putExtra("image",image)
                intent.putExtra("name",name)
                intent.putExtra("time",time)
                intent.putExtra("score",score)
                startActivity(intent)
            }
        }

    }


    //searchView 기능 구현
    fun googleSearch() {
        searchView?.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //암시적 인텐트
                val uri = Uri.parse("geo:0,0?q=$query")
                var intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
    //카드들을 초기화!
    fun initCardView(){
        var id = arrayOf(R.id.card1,R.id.card2,R.id.card3,R.id.card4,R.id.card5,R.id.card6,R.id.card7,R.id.card8,R.id.card9,R.id.card10)
        id.forEach{
            cards.add(findViewById(it))
        }
        //intent에 image 리소스를 따로보내야하므로 (이게맞나,...)
        images.add(R.drawable.food1)
        images.add(R.drawable.food2)
        images.add(R.drawable.food3)
        images.add(R.drawable.food4)
        images.add(R.drawable.food5)

        images.add(R.drawable.card1)
        images.add(R.drawable.card2)
        images.add(R.drawable.card3)
        images.add(R.drawable.card4)
        images.add(R.drawable.card5)

        setCardView(cards[0],images[0],"피자탑 강남 1호점","27~37분","4.9(12,343) · 2.3km")
        setCardView(cards[1],images[1],"중경마라탕 강남점","25~35분","4.9(18,906) · 0.4km")
        setCardView(cards[2],images[2],"당신이 선택한 인생치킨 강남본점","30~40분","4.8(4,463) · 0.3km")
        setCardView(cards[3],images[3],"파스타에 반하다","24~34분","4.9(4,309) · 1.0km")
        setCardView(cards[4],images[4],"잭잭 강남점","15~20분","4.9(57) · 0.3km")
        setCardView(cards[5],images[5],"크리스피크림도넛 1호점","26분~35분","4.9(3,089) · 0.1km")
        setCardView(cards[6],images[6],"어수수산","35~45분","4.9(21,824) · 1.5km")
        setCardView(cards[7],images[7],"강남진해장","20~30분","4.9(21,824) · 1.5km")
        setCardView(cards[8],images[8],"메가박스 강남점","14~24분","4.9(21,824) · 1.5km")
        setCardView(cards[9],images[9],"파파이스 강남점","19~20분","4.6(594) · 0.5km")
    }
    fun setCardView(cd : CardView,image : Int,name:String,time:String,score:String){

        val imageView = cd.findViewById<ImageView>(R.id.cardimageView)
        val nameText = cd.findViewById<TextView>(R.id.cd_name_textView)
        val timeText = cd.findViewById<TextView>(R.id.cd_time_TextView)
        val scoreText = cd.findViewById<TextView>(R.id.cd_score_TextView)

        imageView.setImageResource(image)
        nameText.text = name
        timeText.text = time
        scoreText.text = score
    }

    fun bitmapToByteArray(bitmap: Bitmap):ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
        return stream.toByteArray()
    }
}

