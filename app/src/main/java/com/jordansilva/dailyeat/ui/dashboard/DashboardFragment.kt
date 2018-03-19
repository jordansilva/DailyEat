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
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

class DashboardFragment : BaseFragment() {

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
        val data = mockList()

        adapter = DashboardPostAdapter(context!!, data)
        recyclerDashBoard.adapter = adapter
        recyclerDashBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    fun mockList(): List<DashboardPost> {
        val data = ArrayList<DashboardPost>()
        val calendar = Calendar.getInstance(Locale.getDefault())

        for (i in 0..15) {
            calendar.add(Calendar.HOUR_OF_DAY, i * -1)
            val item = DashboardPost(UUID.randomUUID(),
                    name = "Torrada Chique",
                    description = getString(R.string.lorem_ipsum),
                    imageUrl = "https://images.unsplash.com/photo-1465014925804-7b9ede58d0d7?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=19ffb3aea41bfd3c8179441e70b34e90&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb",
                    authorId = UUID.randomUUID(),
                    author = "Jordan Silva",
                    avatar = "https://scontent.fsdu11-1.fna.fbcdn.net/v/t1.0-9/18403748_10212881587212658_7977014779724222797_n.jpg?oh=3584704ff3559c4fef00e9c3a1fd66d9&oe=5B29BD30",
                    date = calendar.time)
            data.add(item)
        }

        return data
    }


}
