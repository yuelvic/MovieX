package org.bitbucket.moviex.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    protected abstract fun configureViewModel()
    open fun configureUI() {}
    open fun configureBehavior() {}

    override fun onStart() {
        super.onStart()
        configureViewModel()
        configureUI()
        configureBehavior()
    }
}