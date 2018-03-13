package com.jordansilva.dailyeat.view

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.util.disableShiftMode
import com.jordansilva.dailyeat.util.typeface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bio -> {
                message.setText(R.string.title_bio)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_track -> {
                message.setText(R.string.title_track)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                message.setText(R.string.title_diary)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.disableShiftMode()
        navigation.typeface(null, style = Typeface.BOLD, allCaps = true)
    }
}
