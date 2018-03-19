package com.jordansilva.dailyeat.ui.recipe

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.ui.BaseActivity
import com.jordansilva.dailyeat.util.Mock
import com.jordansilva.dailyeat.util.loadUrlCenterCrop
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.content_recipe_detail.*
import org.jetbrains.anko.find
import unimedbh.app.prestador.util.format
import unimedbh.app.prestador.util.random

class RecipeDetailActivity : BaseActivity() {

    private lateinit var mock: DashboardPost
    private var nestedViewTopMargin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        setupUI()
    }

    fun setupUI() {
        //Loading data
        mock = Mock(this).mockList().random()!!
        textTitle.text = mock.name
        ratingBar.rating = mock.rating
        ratingBar.invalidate()
        textRating.text = mock.rating.format("%.1f")
        textReviews.text = String.format("(%d %s)", mock.amountRatings, getString(R.string.reviews))
        imageRecipeBackground.loadUrlCenterCrop(mock.imageUrl)
    }


    override fun configureToolbar() {
        super.configureToolbar()

        //toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val layoutParams = nestedScrollView.layoutParams as ViewGroup.MarginLayoutParams
        nestedViewTopMargin = layoutParams.topMargin

        val actionBarLayout = find<AppBarLayout>(R.id.app_bar)
        actionBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            run {
                val offset = Math.abs(verticalOffset)
                when (offset) {
                    appBarLayout.totalScrollRange -> actionBarCollapsed()
                    0 -> actionBarExpanded()
                    else -> actionBarSliding(offset, appBarLayout.totalScrollRange)
                }
            }
        }
    }

    private fun actionBarSliding(offset: Int, totalScrollRange: Int) {
        val margin = Math.abs(nestedViewTopMargin) - (Math.abs(nestedViewTopMargin) * (offset / totalScrollRange.toFloat()))

        val layoutParams = nestedScrollView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.topMargin = -margin.toInt()
        nestedScrollView.layoutParams = layoutParams
    }

    private fun actionBarExpanded() {
        supportActionBar?.title = ""
        val layoutParams = nestedScrollView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.topMargin = nestedViewTopMargin
        nestedScrollView.layoutParams = layoutParams
    }

    private fun actionBarCollapsed() {
        supportActionBar?.title = mock.name
        val layoutParams = nestedScrollView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.topMargin = 0
        nestedScrollView.layoutParams = layoutParams
    }
}
