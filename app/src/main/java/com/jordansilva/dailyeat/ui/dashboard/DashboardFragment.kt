package com.jordansilva.dailyeat.ui.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.adapters.DashboardPostAdapter
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.data.model.User
import com.jordansilva.dailyeat.ui.BaseFragment
import com.jordansilva.dailyeat.util.Mock
import kotlinx.android.synthetic.main.fragment_dashboard.*
import unimedbh.app.prestador.util.random
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
        recyclerDashBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    fun mockList(): List<DashboardPost> {
        val data = ArrayList<DashboardPost>()

        //Mock
        val mock = Mock()
        val images = mock.mockImages()
        val users = mock.mockUsers()
        val calendar = Calendar.getInstance(Locale.getDefault())

        for (i in 0..16) {
            calendar.add(Calendar.HOUR_OF_DAY, i * -1)
            val user = users.random()!!
            val item = DashboardPost(UUID.randomUUID(),
                    name = "Food $i",
                    description = getString(R.string.lorem_ipsum),
                    imageUrl = images[i],
                    authorId = UUID.randomUUID(),
                    author = user.name,
                    avatar = user.avatar!!,
                    date = calendar.time)
            item.rateAmount = Random().nextFloat() * 5f
            data.add(item)
        }

        return data
    }

    class UserBuilder {

        private var user: User = User(UUID.randomUUID().toString(), "", "")

        fun name(name: String): UserBuilder {
            user.name = name
            return this
        }

        fun avatar(avatar: String): UserBuilder {
            user.avatar = avatar
            return this
        }

        fun build() = user
    }

}
