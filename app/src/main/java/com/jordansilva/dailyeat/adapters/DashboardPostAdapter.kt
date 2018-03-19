package com.jordansilva.dailyeat.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.support.transition.TransitionManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.util.loadUrlCenterCrop
import kotlinx.android.synthetic.main.item_dashboard_recipe.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import unimedbh.app.prestador.util.relativeTime


/**
 * Created by jordansilva on 18/03/18.
 */
class DashboardPostAdapter(private val context: Context,
                           private val data: List<DashboardPost>,
                           private val listener: DashboardPostListener?) : RecyclerView.Adapter<DashboardPostAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_recipe, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.bindView(item)
        holder.name.onClick { listener?.onRecipeClick(item) }
        holder.description.onClick { listener?.onRecipeClick(item) }
        holder.image.onClick { listener?.onRecipeClick(item) }
        holder.btnLike.onClick {
            item.liked = !item.liked
            holder.animateLikeButton(it, item.liked, true)
            listener?.onLikeClick(item)
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recipe
        val name = itemView.textName
        val description = itemView.textDescription
        val image = itemView.imageRecipe

        //
        val author = itemView.textAuthor
        val avatar = itemView.imageAvatar
        val date = itemView.textDate

        //stats
        //val liked = itemView.textTotalLiked
        //var cooked = itemView.textTotalCooked
        var rate = itemView.ratingBar

        //actions
        //var btnShare = itemView.buttonShare
        var btnLike = itemView.buttonLike
        //var btnSave = itemView.buttonSave

        @SuppressLint("RestrictedApi")
        fun bindView(item: DashboardPost) {

            name.text = item.name
            description.text = item.description
            image.loadUrlCenterCrop(item.imageUrl)
            author.text = item.author
            avatar.loadUrlCenterCrop(item.avatar)
            date.text = item.date.relativeTime
            rate.rating = item.rating
            animateLikeButton(btnLike, item.liked)

        }

        @SuppressLint("RestrictedApi")
        fun animateLikeButton(it: View?, liked: Boolean, animate: Boolean = false) {
            it as LottieAnimationView

            val keyPath = KeyPath("**")
            val callback: LottieValueCallback<ColorFilter>
            if (liked) {
                callback = LottieValueCallback(SimpleColorFilter(Color.parseColor("#fe3144")))
                it.setBackgroundResource(R.drawable.tag_white)
            } else {
                callback = LottieValueCallback(SimpleColorFilter(Color.WHITE))
                it.setBackgroundResource(R.drawable.tag_pink)
            }

            it.addValueCallback<ColorFilter>(keyPath, LottieProperty.COLOR_FILTER, callback)

            if (animate) {
                TransitionManager.beginDelayedTransition(btnLike.parent as ViewGroup)
                it.playAnimation()
            }
        }
    }

    interface DashboardPostListener {
        fun onRecipeClick(item: DashboardPost)
        fun onLikeClick(item: DashboardPost)
    }
}