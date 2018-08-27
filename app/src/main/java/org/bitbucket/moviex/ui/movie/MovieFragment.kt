package org.bitbucket.moviex.ui.movie

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.movie_fragment.*
import org.bitbucket.moviex.R
import org.bitbucket.moviex.adapter.MovieAdapter
import org.bitbucket.moviex.databinding.MovieFragmentBinding
import org.bitbucket.moviex.ui.base.BaseFragment
import org.bitbucket.moviex.utils.ViewModelFactory
import org.bitbucket.moviex.utils.extensions.DataState
import org.bitbucket.moviex.utils.extensions.getAppInjector
import org.bitbucket.moviex.utils.extensions.getViewModel
import javax.inject.Inject

class MovieFragment : BaseFragment<MovieFragmentBinding>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mMovieViewModel: MovieViewModel
    private lateinit var mBinding: MovieFragmentBinding

    private lateinit var mMovieAdapter: MovieAdapter

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
    }

    override fun configureBehavior() {
        this.mMovieViewModel.getPopular()
                .apply {
                    observe(this@MovieFragment, Observer {
                        this@MovieFragment.mBinding.data = it
                        if (it.dataState != DataState.LOADING) {
                            this@MovieFragment.srMovieRefresh.isRefreshing = false
                        }
                    })
                }

        this.srMovieRefresh.setOnRefreshListener {
            this.mMovieViewModel.getPopular()
        }
    }

}
