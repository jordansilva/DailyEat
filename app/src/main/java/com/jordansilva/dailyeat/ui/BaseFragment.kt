package com.jordansilva.dailyeat.ui

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

open class BaseFragment : Fragment() {

    val fragmentManagerChild: FragmentManager by lazy { activity!!.supportFragmentManager }

    protected fun addFragment(@IdRes containerViewId: Int, vararg fragment: Fragment) {
        val ft = fragmentManagerChild.beginTransaction()
        fragment.forEach { ft.add(containerViewId, it) }
        ft.commit()
    }
}