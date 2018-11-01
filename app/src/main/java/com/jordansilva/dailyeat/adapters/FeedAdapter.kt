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
import com.jordansilva.dailyeat.model.FeedView
import com.jordansilva.dailyeat.util.loadUrlCenterCrop
import com.jordansilva.dailyeat.util.relativeTime
import kotlinx.android.synthetic.main.item_dashboard_recipe.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


/**
 * Created by jordansilva on 18/03/18.
 */
class FeedAdapter(private val context: Context,
                  private var data: List<FeedView>,
                  private val listener: FeedListener?) : RecyclerView.Adapter<FeedAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_recipe, parent, false)
        return ItemViewHolder(view)
    }

    fun updateItems(items: List<FeedView>) {
        data = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.bindView(item)
        holder.name.setOnClickListener { listener?.onRecipeClick(holder.itemView, item) }
        holder.description.setOnClickListener { listener?.onRecipeClick(holder.itemView, item) }
        holder.image.setOnClickListener { listener?.onRecipeClick(holder.itemView, item) }
        holder.btnLike.setOnClickListener {
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

        val authorName = itemView.textAuthor
        val authorAvatar = itemView.imageAvatar
        val created = itemView.textDate

        //stats

        //actions
        //var btnShare = itemView.buttonShare
        var btnLike = itemView.buttonLike
        //var btnSave = itemView.buttonSave

        @SuppressLint("RestrictedApi")
        fun bindView(item: FeedView) {

            name.text = item.name
            description.text = item.description
            image.loadUrlCenterCrop(item.imageUrl)
            authorName.text = item.authorName
            authorAvatar.loadUrlCenterCrop(item.authorAvatar)
            created.text = item.created.relativeTime
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

    interface FeedListener {
        fun onRecipeClick(view : View, item: FeedView)
        fun onLikeClick(item: FeedView)
    }
}