package com.jordansilva.dailyeat.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jordansilva.dailyeat.R
import com.jordansilva.dailyeat.data.model.DashboardPost
import com.jordansilva.dailyeat.data.model.RecipeIngredient
import com.jordansilva.dailyeat.util.loadUrl
import com.jordansilva.dailyeat.util.loadUrlCenterCrop
import com.jordansilva.dailyeat.util.loadUrlWithTransformation
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_dashboard_recipe.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import unimedbh.app.prestador.util.dp
import unimedbh.app.prestador.util.px
import kotlin.math.round


/**
 * Created by jordansilva on 18/03/18.
 */
class IngredientAdapter(private val context: Context,
                        private val data: List<RecipeIngredient>) : RecyclerView.Adapter<IngredientAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        holder.bindView(context, item)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //recipe
        val name = itemView.textName

        @SuppressLint("RestrictedApi")
        fun bindView(context: Context, item: RecipeIngredient) {
            val text = String.format("<b>%.1f %s</b> %s", item.amount, item.amountType, item.name)

            name.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(text)
            }
        }

    }
}