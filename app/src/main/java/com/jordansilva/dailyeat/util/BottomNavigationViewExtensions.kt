package com.jordansilva.dailyeat.util

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.design.internal.BaselineLayout
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.widget.TextView
import org.jetbrains.anko.forEachChild


@SuppressLint("RestrictedApi")
        /**
 * Created by jordansilva on 13/03/18.
 */

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0 until menuView.childCount) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            // set once again checked value, so view will be updated
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: Exception) {
        Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field")
    } catch (e: IllegalAccessException) {
        Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode")
    }
}

@SuppressLint("RestrictedApi")
fun BottomNavigationView.typeface(typeface: Typeface?,
                                  style: Int = Typeface.NORMAL,
                                  allCaps: Boolean = false) {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    menuView.forEachChild {
        it as BottomNavigationItemView
        //every BottomNavigationItemView has two children, first is an itemIcon and second is an itemTitle
        val itemTitle = it.getChildAt(1) as BaselineLayout
        //every itemTitle has two children, first is a smallLabel and second is a largeLabel. these two are type of AppCompatTextView
        itemTitle.forEachChild {
            it as TextView
            it.setAllCaps(allCaps)
            it.setTypeface(typeface, style)

        }
    }
}