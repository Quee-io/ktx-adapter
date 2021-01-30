package io.quee.ktx.adapter.core

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
inline fun listAdapter(block: ListAdapter.() -> Unit): ListAdapter = ListAdapter().apply {
    block()
}

inline fun <M> layoutViewModelDsl(
    layoutRes: Int,
    model: M,
    crossinline init: DefaultViewHolder.() -> Unit,
) = LayoutViewModel<M>(layoutRes).apply {
    this.model = model
    onCreateViewHolder {
        init.invoke(this)
    }
}
