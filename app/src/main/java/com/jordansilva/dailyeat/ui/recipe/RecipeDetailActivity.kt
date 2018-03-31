package com.jordansilva.dailyeat.ui.recipe

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.adapters.IngredientAdapter
import com.jordansilva.dailyeat.adapters.RecipeAdapter
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.data.model.RecipeIngredient
import com.jordansilva.dailyeat.ui.BaseActivity
import com.jordansilva.dailyeat.util.Mock
import com.jordansilva.dailyeat.util.loadUrl
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.content_recipe_detail.*
import kotlinx.android.synthetic.main.layout_recipe_header.*
import kotlinx.android.synthetic.main.layout_recipe_ingredients.*
import kotlinx.android.synthetic.main.layout_similar_recipes.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import unimedbh.app.prestador.util.format

class RecipeDetailActivity : BaseActivity(), RecipeAdapter.RecipeListener {
    companion object {
        val EXTRA_RECIPE = "RecipeDetailActivity.EXTRA_RECIPE"
    }

    private lateinit var mock: DashboardPost
    private var nestedViewTopMargin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//
//            val transition = Fade()
//            transition.excludeTarget(R.id.navigation, true)
//            transition.excludeTarget(android.R.id.statusBarBackground, true)
//            transition.excludeTarget(android.R.id.navigationBarBackground, true)
//            transition.excludeTarget(resources.getIdentifier("action_bar_container", "id", "android"), true)
//
//            window.enterTransition = transition
//            window.exitTransition = transition
//        }

        setContentView(R.layout.activity_recipe_detail)
        supportPostponeEnterTransition()

        mock = intent.getSerializableExtra(EXTRA_RECIPE) as DashboardPost

        setupUI()
    }

    fun setupUI() {
        loadHeader()
        loadIngredients()
        loadSimilarRecipes()
    }

    private fun loadHeader() {
        textTitle.text = mock.name
        ratingBar.rating = mock.rating
        ratingBar.invalidate()
        textRating.text = mock.rating.format("%.1f")
        textReviews.text = String.format("(%d %s)", mock.amountRatings, getString(R.string.reviews))
        tagGroup.setTags(mock.tags)

        imageRecipe.loadUrl(mock.imageUrl,
                { supportStartPostponedEnterTransition() },
                { supportStartPostponedEnterTransition() })
    }

    private fun loadIngredients() {
        val animation = AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)

        val ingredients = ArrayList<RecipeIngredient>()
        for (i in 0..7) {
            ingredients.add(RecipeIngredient("Ingredient $i", i + 1.toFloat(), "can"))
        }

        recyclerIngredients.adapter = IngredientAdapter(this@RecipeDetailActivity, ingredients)
        recyclerIngredients.layoutManager = LinearLayoutManager(this@RecipeDetailActivity, LinearLayoutManager.VERTICAL, false)
        recyclerIngredients.layoutAnimation = animation
        recyclerIngredients.scheduleLayoutAnimation()


    }

    private fun loadSimilarRecipes() {
        val data = Mock(this@RecipeDetailActivity).mockList()
        val numberOfSimilarRecipes = 4

        textNumSimilarRecipes.text = String.format(getString(R.string.num_recipes), numberOfSimilarRecipes)

        val animation = AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)

        recyclerSimilarRecipes.adapter = RecipeAdapter(this@RecipeDetailActivity, data.take(numberOfSimilarRecipes), this)
        recyclerSimilarRecipes.layoutManager = LinearLayoutManager(this@RecipeDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerSimilarRecipes.layoutAnimation = animation
        recyclerSimilarRecipes.scheduleLayoutAnimation()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRecipeClick(view: View, item: DashboardPost) {
        toast(item.name).show()
    }

}
