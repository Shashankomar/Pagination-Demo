package com.e.paginatorkotlindemo.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
* This class is called all the time when recycler view is scrolled
* Because it extends RecyclerView.OnScrollListener.
* Get LayoutManager to use three methods mentioned below to work pagination.
* layoutManager.childCount : visible items in screen, it may depend on item size or screen size.
* layoutManager.itemCount : total item in recycler view
* layoutManager.findFirstVisibleItemPosition : first visible item position in screen.
* */
abstract class PaginationListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    companion object {
        const val PAGE_SIZE = 10
        const val PAGE_START = 1
    }

    /*
    !isLoading() && !isLastPage()
    if the data is loaded and current page is not last page then check-

    (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
    to stop calling loadMoreItems() till reach last item size of current page.
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

}