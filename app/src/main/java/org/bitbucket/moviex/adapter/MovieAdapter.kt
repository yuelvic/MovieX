package org.bitbucket.moviex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.bitbucket.moviex.R
import org.bitbucket.moviex.data.entities.Movie
import org.bitbucket.moviex.databinding.ItemMovieBinding

/**
 * Created by Emmanuel Victor Garcia
 */

class MovieAdapter(val context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var dataSet = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return this.dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = this.dataSet[position]
        holder.apply {
            bind(data)
        }
    }

    fun setMovies(dataSet: List<Movie>?) {
        if (dataSet != null) {
            this.dataSet.clear()
            addMovies(dataSet)
        }
    }

    fun addMovies(dataSet: List<Movie>?) {
        if (dataSet != null) {
            this.dataSet.addAll(dataSet)
            this.notifyDataSetChanged()
        }
    }

    fun clear() {
        this.dataSet.clear()
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Movie) {
            this.binding.apply {
                movie = data
                executePendingBindings()
            }
        }

    }

}