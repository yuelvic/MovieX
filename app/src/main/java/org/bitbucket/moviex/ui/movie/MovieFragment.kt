package org.bitbucket.moviex.ui.movie

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xrojan.rxbus.RxBus
import kotlinx.android.synthetic.main.movie_fragment.*
import org.bitbucket.moviex.R
import org.bitbucket.moviex.adapter.MovieAdapter
import org.bitbucket.moviex.databinding.MovieFragmentBinding
import org.bitbucket.moviex.ui.base.BaseFragment
import org.bitbucket.moviex.utils.ViewModelFactory
import org.bitbucket.moviex.utils.extensions.DataState
import org.bitbucket.moviex.utils.extensions.getAppInjector
import org.bitbucket.moviex.utils.extensions.getViewModel
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : BaseFragment<MovieFragmentBinding>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mMovieViewModel: MovieViewModel
    private lateinit var mBinding: MovieFragmentBinding

    private lateinit var mMovieAdapter: MovieAdapter

    private var isLoading = false
    private var page = 1
    private var isSearching = false

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel

    override fun configureViewModel() {
        getAppInjector().inject(this)
        this.mMovieViewModel = activity!!.getViewModel(mViewModelFactory)
    }

    override fun configureLayout(): Int {
        return R.layout.movie_fragment
    }

    override fun passDataBinding(binding: MovieFragmentBinding) {
        this.mBinding = binding
    }

    override fun configureUI(view: View) {
        this.mMovieAdapter = MovieAdapter(this.context!!)
        this.rvMovieList.adapter = this.mMovieAdapter

        val gridLayoutManager = GridLayoutManager(context, 2)
        this.rvMovieList.layoutManager = gridLayoutManager

        this.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading && !isSearching) {
                    this@MovieFragment.mMovieViewModel.getPopular(++page)
                    this@MovieFragment.isLoading = true
                }
            }
        })
    }

    override fun configureBehavior() {
        this.mMovieViewModel.getPopular(page)
                .apply {
                    observe(this@MovieFragment, Observer {
                        this@MovieFragment.mBinding.data = it
                        if (it.dataState != DataState.LOADING) {
                            this@MovieFragment.srMovieRefresh.isRefreshing = false
                            this@MovieFragment.isLoading = false
                        }
                    })
                }

        this.srMovieRefresh.setOnRefreshListener {
            this.mMovieAdapter.clear()
            this.mMovieViewModel.getPopular(page = 1)
            this@MovieFragment.isLoading = false
        }
    }

    override fun configureEvent() {
        RxBus.subscribe<String>(this) {
            this.isSearching = !it.isEmpty()
            this.mMovieAdapter.clear()
            this.mMovieViewModel.getMoviesFromDb(it)
        }
    }

    override fun releaseEvent() {
        RxBus.unsubscribe(this)
    }

}
