package org.bitbucket.moviex

import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import org.bitbucket.moviex.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun configureViewModel() {

    }

    override fun configureUI() {
        setSupportActionBar(this.toolbar)
    }

}
