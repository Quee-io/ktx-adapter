package io.quee.ktx.adapter.core

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
fun RecyclerView.Adapter<*>.into(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
) = apply {
    recyclerView.layoutManager = layoutManager ?: LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = this
    when (this) {
        is LifecycleAdapter -> when (val context = recyclerView.context) {
            is LifecycleOwner -> {
                context.lifecycle.addObserver(this)
            }
        }
    }
}