package com.masashi.hamaguchi.ld_taskproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var active: Boolean = false
    var num: Int = 0
    var carList: MutableList<Car> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("男気ガチャ")

        manufacturer_text.setText("どんな車がでるかな？")
        model_text.setText("Let's START!")
        button.setText("START")

        carList.add(Car("BMW", "4シリーズ カブリオレ", R.mipmap.bmw))
        carList.add(Car("トヨタ", "新型ヴェルファイア", R.mipmap.vellfire))
        carList.add(Car("ロールスロイス", "ファントム", R.mipmap.rolls_royce))
        carList.add(Car("スズキ", "トイレカー", R.mipmap.toilet_car))
        carList.add(Car("ダイハツ？", "トゥクトゥク", R.mipmap.tuktuk))

        // Handlerの準備
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                // UIスレッド
                val random = Random()
                num = random.nextInt(5)

                var car = carList.get(num)
                manufacturer_text.setText(car.manufacturer)
                model_text.setText(car.model)
                imageView.setImageResource(car.resourceImage)

                handler.postDelayed(this, 100)
            }
        }

        button.setOnClickListener {
            if (active) {
                // -> stop
                active = !active
                handler.removeCallbacks(runnable)
                button.setText("START")

            } else {
                // -> start
                active = !active
                handler.post(runnable)
                button.setText("STOP")
            }
        }

    }

    class Car(var manufacturer: String?, var model: String?, var resourceImage: Int)

}
