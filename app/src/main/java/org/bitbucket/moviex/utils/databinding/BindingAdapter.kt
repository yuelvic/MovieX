package org.bitbucket.moviex.utils.databinding

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bitbucket.moviex.adapter.MovieAdapter
import org.bitbucket.moviex.data.entities.Movie

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("rating")
fun setRating(ratingBar: RatingBar, rating: Float) {
    ratingBar.rating = rating / 2
}

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, dataSet: List<Movie>?) {
    if (recyclerView.adapter is MovieAdapter) {
        (recyclerView.adapter as MovieAdapter).addMovies(dataSet)
    }
}