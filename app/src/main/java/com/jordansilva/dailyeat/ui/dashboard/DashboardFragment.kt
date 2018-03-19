package com.jordansilva.dailyeat.ui.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.adapters.DashboardPostAdapter
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.ui.BaseFragment
import com.jordansilva.dailyeat.ui.recipe.RecipeDetailActivity
import com.jordansilva.dailyeat.util.Mock
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

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

        adapter = DashboardPostAdapter(context!!, data, this)
        recyclerDashBoard.adapter = adapter
        recyclerDashBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    override fun onRecipeClick(item: DashboardPost) {
        startActivity<RecipeDetailActivity>()
    }

    override fun onLikeClick(item: DashboardPost) {
        toast(if (item.liked) "Liked" else "Disliked!")
    }

}
