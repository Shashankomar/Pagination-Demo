package com.e.paginatorkotlindemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.e.paginatorkotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var clicked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            if (clicked) {
                clicked = false
                setColor(
                    R.color.pink,
                    R.color.colorAccent,
                    R.color.black
                )
            } else {
                clicked = true
                setColor(
                    R.color.black,
                    R.color.colorPrimaryDark,
                    R.color.white
                )
            }
        }
    }

    private fun setColor(btnBg: Int, textCol: Int, btnText: Int) {
        btn.setBackgroundColor(ContextCompat.getColor(this, btnBg))
        tv_text.setTextColor(ContextCompat.getColor(this, textCol))
        btn.setTextColor(ContextCompat.getColor(this, btnText))
    }
}