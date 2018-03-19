package com.jordansilva.dailyeat.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import android.view.animation.AlphaAnimation
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.ui.dashboard.DashboardFragment
import com.jordansilva.dailyeat.util.disableShiftMode
import com.jordansilva.dailyeat.util.typeface
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import unimedbh.app.prestador.util.notNull



class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener when (item.itemId) {
            R.id.navigation_dashboard -> {
                replaceFragment(DashboardFragment.newInstance())
                true
            }
            R.id.navigation_bio -> {
                toast(R.string.title_bio)
                true
            }
            R.id.navigation_diary -> {
                toast(R.string.title_diary)
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    private fun setupUI() {
        setupNavigation()
        initFragments()
    }

    fun setupNavigation() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.disableShiftMode()
        navigation.typeface(null, style = Typeface.BOLD, allCaps = true)
    }

    override fun configureToolbar() {
        super.configureToolbar()

        val actionBarLayout = find<AppBarLayout>(R.id.app_bar)
        actionBarLayout.notNull {
            it.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                run {
                    when (Math.abs(verticalOffset)) {
                        appBarLayout.totalScrollRange -> actionBarCollapsed()
                        0 -> actionBarExpanded()
                        else -> actionBarIdle()
                    }
                }
            }
        }
    }

    fun initFragments() {
        replaceFragment(DashboardFragment.newInstance())
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.content, fragment)
        transaction.commitAllowingStateLoss()
    }

    fun actionBarExpanded() {
        toolbarTitle?.clearAnimation()
        toolbarTitle?.visibility = View.GONE
    }

    fun actionBarCollapsed() {
        val animate = AlphaAnimation(0f, 1f)
        animate.duration = 500
        animate.fillAfter = true
        toolbarTitle?.startAnimation(animate)
        toolbarTitle?.visibility = View.VISIBLE
    }

    fun actionBarIdle() {
    }
}
