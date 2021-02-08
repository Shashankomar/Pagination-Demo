package com.e.paginatorkotlindemo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.paginatorkotlindemo.R
import com.e.paginatorkotlindemo.adapter.PaginationAdapter
import com.e.paginatorkotlindemo.model.DataModel
import com.e.paginatorkotlindemo.utils.PaginationListener
import com.e.paginatorkotlindemo.utils.PaginationListener.Companion.PAGE_START
import kotlinx.android.synthetic.main.activity_pagination.*

var adapter: PaginationAdapter? = null

class PaginationActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private var currentPage = PAGE_START
    private val totalPage = 10
    private var itemCount = 0
    private var isLastPage = false
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)

        setRecyclerView()

        doApiCall()

        setScrollListener()
    }

    /*
    PaginationListener is a user defined class with three abstract methods
    to call more data from api. For more detail Please Refer to the class.
    (To open a class, for windows: Press ctrl and click on PaginationListener)
     */
    private fun setScrollListener() {
        rv_view.addOnScrollListener(object : PaginationListener(layoutManager) {

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                currentPage++

                doApiCall()
            }
        })
    }

    /*
    Setting Linear Layout Manager and Adapter in RecyclerView with a New ArrayList.
     */
    private fun setRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        rv_view.layoutManager = layoutManager
        adapter = PaginationAdapter(
            ArrayList()
        )
        rv_view.adapter = adapter
    }

    /*
    Handler and Runnable for 1.5 second delay.
    Static data created.
    data added in adapter
    checking current page to total page To stop pagination.
    isLoading is bool to call loadMoreItems() with setting bool to true.
    After load and add data from api it will be set as false. OnScroll method is called so many times
    so to prevent loading data again and again this check works fine.
     */
    private fun doApiCall() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            val list: ArrayList<DataModel> = ArrayList()

            for (i in 1..10) {
                itemCount++
                val d = DataModel(
                    "Text item count = $itemCount",
                    "Text fixed"
                )
                list.add(d)
            }
            adapter?.addItems(list)

            if (currentPage >= totalPage) {
                isLastPage = true
            }
            isLoading = false
        }
        handler.postDelayed(runnable, 1500)
    }
}