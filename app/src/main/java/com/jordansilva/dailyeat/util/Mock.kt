package com.jordansilva.dailyeat.util

import android.content.Context
import com.jordansilva.dailyeat.R
import app.jordansilva.domain.model.DashboardPost
import app.jordansilva.domain.model.User
import java.util.*

/**
 * Created by jordansilva on 19/03/18.
 */
class Mock(var context: Context) {
    fun mockUsers(): List<User> {
        val users = ArrayList<User>()

        users.add(UserBuilder()
                .name("Jordan Silva")
                .avatar("https://scontent.fsdu11-1.fna.fbcdn.net/v/t1.0-9/18403748_10212881587212658_7977014779724222797_n.jpg?oh=3584704ff3559c4fef00e9c3a1fd66d9&oe=5B29BD30")
                .build())
        users.add(UserBuilder()
                .name("Gabriela Boaventura")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-9/26229367_10215722312197353_72208779691992113_n.jpg?oh=57077cb7e23b2c517586450ddf4a6ea2&oe=5B346889")
                .build())
        users.add(UserBuilder()
                .name("Gibran Silva")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-1/p160x160/12193340_1064666226906758_2247095994875982561_n.jpg?_nc_cat=0&oh=dd63556ab0f89f477a8e9dd062870734&oe=5B3AFDFB")
                .build())
        users.add(UserBuilder()
                .name("Douglas Aguiar")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-9/23621727_10155805826406838_361168776498155417_n.jpg?oh=90a8db7f859860fb661ddf0a716650f0&oe=5B3239CB")
                .build())
        users.add(UserBuilder()
                .name("Rafael Glater")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-9/29177235_1766531816744267_5727017002892001280_n.jpg?oh=9a7761d74f45384018dcc57d30dfdbbc&oe=5B34AE81")
                .build())
        users.add(UserBuilder()
                .name("Ana Paula Gomes")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-9/17904451_1504394522927904_8378189893043632896_n.jpg?_nc_cat=0&oh=7530ad5d092c92b3e38ec1732e9f229c&oe=5B346577")
                .build())
        users.add(UserBuilder()
                .name("Raissa Guerra")
                .avatar("https://scontent.fplu4-1.fna.fbcdn.net/v/t1.0-9/19399978_1400278856720551_3922684003310603527_n.jpg?oh=12ccf2caddc512e79d6786c6f559990b&oe=5B4CB614")
                .build())


        return users
    }

    fun mockImages(): List<String> {
        val images = ArrayList<String>()
        images.add("https://images.unsplash.com/photo-1465014925804-7b9ede58d0d7?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=19ffb3aea41bfd3c8179441e70b34e90&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1460306855393-0410f61241c7?ixlib=rb-0.3.5&s=d87994e9265127c6cea87d9ee4b87f18&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1507120366498-4656eaece7fa?ixlib=rb-0.3.5&s=52f1d00dac6e03118fb3332c64082d1f&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-0.3.5&s=dea3b438cb6e62d3e522e8e4886ec9a5&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1455853828816-0c301a011711?ixlib=rb-0.3.5&s=f087ed54c63956580923b24bfaa07db7&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1450152021501-598b36b17449?ixlib=rb-0.3.5&s=1786f60181943c844af34c8fa1de65fe&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1472926373053-51b220987527?ixlib=rb-0.3.5&s=eef0314e9f1903dac39e9587e3ab170f&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1506224477000-07aa8a76be20?ixlib=rb-0.3.5&s=694866771d4464a3081af9537015e73f&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1466637574441-749b8f19452f?ixlib=rb-0.3.5&s=47a504a636bd9a4b93ee74e4d5b3af38&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1520218576172-c1a2df3fa5fc?ixlib=rb-0.3.5&s=0a0cd3b2d02209eb9efb15325386d4c8&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1490717064594-3bd2c4081693?ixlib=rb-0.3.5&s=a744dc4940571ee2b42a997df096abe9&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1428259067396-2d6bd3827878?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b6a129d823bf9cf046d1136372589d27&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1470338950318-40320a722782?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=5e2b1709169e30ccc86c0d4ab31bb438&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1508709315803-6342a28d32a1?ixlib=rb-0.3.5&s=edfa7412f3aa832cd56b9a62240be4a2&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1445979323117-80453f573b71?ixlib=rb-0.3.5&s=19f33e332971b8d32225d913d989b2fa&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1499125562588-29fb8a56b5d5?ixlib=rb-0.3.5&s=d4284647e39ebde1312288fc0ec990f7&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")
        images.add("https://images.unsplash.com/photo-1464347744102-11db6282f854?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=1ddae735d7eb8c88b1c07f8b919140bf&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb")

        return images
    }

    fun mockList(): List<DashboardPost> {
        val data = ArrayList<DashboardPost>()

        //Mock
        val images = mockImages()
        val users = mockUsers()
        val calendar = Calendar.getInstance(Locale.getDefault())

        for (i in 0..16) {
            calendar.add(Calendar.HOUR_OF_DAY, i * -1)
            val user = users.random()!!
            val item = DashboardPost(UUID.randomUUID(),
                    name = "Food $i",
                    description = context.getString(R.string.lorem_ipsum),
                    imageUrl = images[i],
                    authorId = UUID.randomUUID(),
                    author = user.name,
                    avatar = user.avatar!!,
                    date = calendar.time)
            item.rating = Random().nextFloat() * 5f
            item.amountRatings = Random().nextInt(500)
            item.tags = arrayListOf("Vegan", "Italian", "Brazilian")
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