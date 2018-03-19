package com.jordansilva.dailyeat.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.jordansilva.dailyeat.R
import org.jetbrains.anko.find
import unimedbh.app.prestador.util.notNull

open class BaseActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    var toolbarTitle: TextView? = null
    val fragmentManager: FragmentManager by lazy { supportFragmentManager }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        configureToolbar()
    }

    open fun configureToolbar() {
        toolbar = find(R.id.toolbar)
        toolbar.notNull {
            setSupportActionBar(toolbar)
            toolbarTitle = find(R.id.toolbar_title)
            toolbarTitle?.text = it.title
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    protected fun addFragment(@IdRes containerViewId: Int, vararg fragment: Fragment) {
        val ft = fragmentManager.beginTransaction()
        fragment.forEach { ft.add(containerViewId, it) }
        ft.commit()
    }
}
