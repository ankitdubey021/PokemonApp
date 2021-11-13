package com.ankitdubey.pokemonapp.extensions

import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ankit Dubey on 13,November,2021
 */

fun RecyclerView.addDivider(){
    addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
}

fun RecyclerView.onScrolledToBottom( callback : () -> Unit){
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                callback()
            }
        }
    })
}
