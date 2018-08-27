package org.bitbucket.moviex

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.xrojan.rxbus.RxBus
import kotlinx.android.synthetic.main.activity_main.*
import org.bitbucket.moviex.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                RxBus.post(newText!!)
                return false
            }
        })

        return true
    }

    override fun configureViewModel() {

    }

    override fun configureUI() {
        setSupportActionBar(this.toolbar)
    }

}
