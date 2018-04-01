package com.jordansilva.dailyeat.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.model.RecipeView
import com.jordansilva.dailyeat.util.loadUrlWithTransformation
import com.jordansilva.dailyeat.util.px
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_dashboard_recipe.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by jordansilva on 18/03/18.
 */
class RecipeAdapter(private val context: Context,
                    private val data: List<RecipeView>,
                    private val listener: RecipeListener?) : RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recipe_small, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.bindView(context, item)
        holder.name.onClick { listener?.onRecipeClick(holder.itemView!!, item) }
        holder.description.onClick { listener?.onRecipeClick(holder.itemView!!, item) }
        holder.image.onClick { listener?.onRecipeClick(holder.itemView!!, item) }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recipe
        val name = itemView.textName
        val description = itemView.textDescription
        val image = itemView.imageRecipe

        //
        val author = itemView.textAuthor

        @SuppressLint("RestrictedApi")
        fun bindView(context: Context, item: RecipeView) {

            name.text = item.name
            description.text = item.description
            image.loadUrlWithTransformation(item.imageUrl, RoundedCornersTransformation(4.px, 0))
            author.text = item.authorName
        }

    }

    interface RecipeListener {
        fun onRecipeClick(view: View, item: RecipeView)
    }
}