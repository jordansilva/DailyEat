package com.jordansilva.dailyeat.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.jordansilva.dailyeat.R
import org.jetbrains.anko.find

open class BaseActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        configureToolbar()

    }

    private fun configureToolbar() {
        toolbar = find(R.id.toolbar)

        setSupportActionBar(toolbar)
        toolbar?.let { find<TextView>(R.id.toolbar_title).text = it.title }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


}
