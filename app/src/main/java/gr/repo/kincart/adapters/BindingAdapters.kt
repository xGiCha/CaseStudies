package gr.repo.kincart.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import gr.repo.kincart.R
import java.util.*


object BindingAdapters {

    @BindingAdapter("isVisible")
    @JvmStatic
    fun MaterialCardView.isVisible(item: String?) {
        if (item.isNullOrEmpty())
            this.visibility = View.GONE
        else
            this.visibility = View.VISIBLE
    }

    @BindingAdapter("imageRaw")
    @JvmStatic
    fun ImageView.imageRaw(imageRaw: String) {

        val url = imageRaw

        loadImage(context, url, this)

    }

    fun loadImage(context: Context, url: String, imageV: ImageView) {
        val displayMetrics = context.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
        val width = displayMetrics?.widthPixels

        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.blank)
                .resize(width ?: 0, height?.div(4) ?: 0)
                .centerCrop()
                .into(imageV)
    }
}