package org.bitbucket.moviex.utils.databinding

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.bitbucket.moviex.R
import org.bitbucket.moviex.adapter.MovieAdapter
import org.bitbucket.moviex.data.entities.Movie

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.ic_movie)
    requestOptions.error(R.drawable.ic_movie)
    Glide.with(imageView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(url).into(imageView)
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