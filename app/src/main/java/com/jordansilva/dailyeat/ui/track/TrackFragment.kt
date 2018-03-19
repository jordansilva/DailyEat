package com.jordansilva.dailyeat.ui.track

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_track.*
import kotlinx.android.synthetic.main.row_nutrition.view.*

class TrackFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    companion object {
        fun newInstance(): TrackFragment {
            val fragment = TrackFragment()
            return fragment
        }
    }

    fun init() {
        initStats()
    }

    fun initStats() {
        setupStats(rowCalories, R.string.calories, "250")
        setupStats(rowFats, R.string.fats, "12g")
        setupStats(rowCholesterol, R.string.cholesterol, "30mg")
        setupStats(rowProtein, R.string.protein, "5g")
        setupStats(rowSodium, R.string.sodium, "470mg")
        setupStats(rowCarbohydrates, R.string.carbohydrates, "31g")
    }

    fun setupStats(view: View, @StringRes titleId: Int, value: String) {
        view.title.text = getString(titleId)
        view.value.text = value
    }


}
