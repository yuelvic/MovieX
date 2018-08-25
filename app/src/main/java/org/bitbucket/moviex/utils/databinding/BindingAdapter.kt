package org.bitbucket.moviex.utils.databinding

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating / 2
}