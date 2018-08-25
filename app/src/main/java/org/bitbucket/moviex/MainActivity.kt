package org.bitbucket.moviex

import android.os.Bundle
import org.bitbucket.moviex.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun configureViewModel() {

    }

}
