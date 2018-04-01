package com.jordansilva.dailyeat.ui.recipe

import android.arch.lifecycle.Observer
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
import com.jordansilva.dailyeat.model.RecipeIngredientView
import com.jordansilva.dailyeat.model.RecipeView
import com.jordansilva.dailyeat.ui.BaseActivity
import com.jordansilva.dailyeat.util.Mock
import com.jordansilva.dailyeat.util.format
import com.jordansilva.dailyeat.util.loadUrl
import com.jordansilva.dailyeat.util.notNull
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.content_recipe_detail.*
import kotlinx.android.synthetic.main.layout_recipe_header.*
import kotlinx.android.synthetic.main.layout_recipe_ingredients.*
import kotlinx.android.synthetic.main.layout_similar_recipes.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.koin.android.architecture.ext.viewModel

class RecipeDetailActivity : BaseActivity(), RecipeAdapter.RecipeListener {

    companion object {
        val EXTRA_RECIPE = "RecipeDetailActivity.EXTRA_RECIPE"
    }

    private lateinit var recipeId: String
    private val viewModel by viewModel<RecipeDetailViewModel>()
    private var recipe: RecipeView? = null
    private var nestedViewTopMargin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        supportPostponeEnterTransition()

        recipeId = intent.getStringExtra(EXTRA_RECIPE)
        recipe = viewModel.getRecipeById(recipeId).value
        viewModel.recipe.observe(this, Observer {
            recipe = it
            configureRecipe()
        })
        setupUI()
    }

    private fun setupUI() {
        configureRecipe()
        loadIngredients()
        loadSimilarRecipes()
    }

    private fun configureRecipe() {
        recipe.notNull {
            textTitle.text = it.name
            ratingBar.rating = it.rating
            ratingBar.invalidate()
            textRating.text = it.rating.format("%.1f")
            textReviews.text = String.format("(%d %s)", it.amountRatings, getString(R.string.reviews))
            tagGroup.setTags(it.tags)

            imageRecipe.loadUrl(it.imageUrl,
                    { supportStartPostponedEnterTransition() },
                    { supportStartPostponedEnterTransition() })
        }
    }

    private fun loadIngredients() {
        val animation = AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)

        val ingredients = ArrayList<RecipeIngredientView>()
        for (i in 0..7) {
            ingredients.add(RecipeIngredientView("Ingredient $i", i + 1.toFloat(), "can"))
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

        recyclerSimilarRecipes.adapter = RecipeAdapter(this@RecipeDetailActivity, listOf(), this)
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
        recipe.notNull { supportActionBar?.title = it.name }
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

    override fun onRecipeClick(view: View, item: RecipeView) {
        toast(item.name).show()
    }

}
