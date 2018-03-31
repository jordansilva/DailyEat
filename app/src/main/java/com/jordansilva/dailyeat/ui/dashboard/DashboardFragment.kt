package com.jordansilva.dailyeat.ui.dashboard

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
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.adapters.DashboardPostAdapter
import app.jordansilva.domain.model.DashboardPost
import com.jordansilva.dailyeat.ui.BaseFragment
import com.jordansilva.dailyeat.ui.recipe.RecipeDetailActivity
import com.jordansilva.dailyeat.util.Mock
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import com.jordansilva.dailyeat.util.mapToTypedArray


class DashboardFragment : BaseFragment(), DashboardPostAdapter.DashboardPostListener {
    private lateinit var adapter: DashboardPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    companion object {
        fun newInstance(): DashboardFragment {
            val fragment = DashboardFragment()
            return fragment
        }
    }


    fun init() {
        val data = Mock(context!!).mockList()
        val animation = AnimationUtils.loadLayoutAnimation(ctx, R.anim.layout_animation_fall_down)

        adapter = DashboardPostAdapter(context!!, data, this)
        recyclerDashBoard.adapter = adapter
        recyclerDashBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerDashBoard.layoutAnimation = animation
        recyclerDashBoard.scheduleLayoutAnimation()
    }


    override fun onRecipeClick(view: View, item: DashboardPost) {
        activity?.let {
            val intent = Intent(activity, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, item)

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

    override fun onLikeClick(item: DashboardPost) {
        toast(if (item.liked) "Liked" else "Disliked!")
    }

}
