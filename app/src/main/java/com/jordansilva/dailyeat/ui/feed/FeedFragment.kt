package com.jordansilva.dailyeat.ui.feed

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.gson.Gson
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.adapters.FeedAdapter
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.ui.BaseFragment
import com.jordansilva.dailyeat.ui.recipe.RecipeDetailActivity
import com.jordansilva.dailyeat.util.Mock
import com.jordansilva.dailyeat.util.mapToTypedArray
import com.jordansilva.dailyeat.util.whenNotNull
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel


class FeedFragment : BaseFragment(), FeedAdapter.FeedListener {

    private lateinit var adapter: FeedAdapter
    private val viewModel by viewModel<FeedViewModel>()

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFeeds().observe(this, Observer { updateFeeds(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserFeeds()
    }

    fun getUserFeeds() {
        val jsonData = ArrayList(Mock(context!!).mockList())
        val json = Gson().toJson(jsonData)

        val data = viewModel.getFeeds().value ?: listOf()

        adapter = FeedAdapter(context!!, data, this)
        recyclerDashBoard.adapter = adapter
        recyclerDashBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerDashBoard.layoutAnimation = AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)
        recyclerDashBoard.scheduleLayoutAnimation()
    }

    private fun updateFeeds(feeds: List<FeedView>?) {
        var data = listOf<FeedView>()
        whenNotNull(feeds) { data = it }

        adapter.updateItems(data)
    }


    override fun onRecipeClick(view: View, item: FeedView) {
        activity?.let {
            val intent = Intent(activity, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, item.recipeId)

            val pairViewString = ArrayList<Pair<View, String>>()

            val p1 = Pair.create(view.find<View>(R.id.imageRecipe), "recipe")
            pairViewString.add(p1)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val decor = it.window.decorView
                val statusBar = decor.findViewById<View>(android.R.id.statusBarBackground)
                val navBar = decor.findViewById<View>(android.R.id.navigationBarBackground)
                val actionBar = decor.findViewById<View>(resources.getIdentifier("action_bar_container", "id", "android"))
            }


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, *pairViewString.mapToTypedArray { it })

            startActivity(intent, options.toBundle())
        }
    }

    override fun onLikeClick(item: FeedView) {
        toast(if (item.liked) "Liked" else "Disliked!")
    }

}
