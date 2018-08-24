package org.bitbucket.moviex.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Emmanuel Victor Garcia
 */

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    protected abstract fun configureViewModel()
    protected abstract fun configureLayout(): Int
    protected abstract fun passDataBinding(binding: T)
    open fun configureUI(view: View) {}
    open fun configureBehavior() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: T = DataBindingUtil.inflate(inflater, configureLayout(), container, false)
        passDataBinding(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()
        configureUI(view)
        configureBehavior()
    }
}