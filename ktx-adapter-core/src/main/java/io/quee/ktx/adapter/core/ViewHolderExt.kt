@file:Suppress("UNCHECKED_CAST")

package io.quee.ktx.adapter.core

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
inline fun <reified Adapter> RecyclerView.ViewHolder.getAdapter(): Adapter? {
    return itemView.getTag(R.id.adapter) as? Adapter
}

fun RecyclerView.ViewHolder.getRecyclerView(): RecyclerView? {
    return itemView.getTag(R.id.adapter_recyclerView) as? RecyclerView
}

inline fun <reified VM : ViewModel<*, *>> RecyclerView.ViewHolder.getViewModel(): VM? {
    return itemView.getTag(R.id.adapter_item) as? VM
}

inline fun <reified M> RecyclerView.ViewHolder.getModel(): M? {
    return (itemView.getTag(R.id.adapter_item) as? ViewModel<M, *>)?.model
}

inline fun <reified T : View> RecyclerView.ViewHolder.getView(@IdRes viewId: Int): T {
    return itemView.findViewById(viewId) as T
}

internal typealias BindView = DefaultViewHolder.(payloads: List<Any>) -> Unit
internal typealias ViewHolderType = DefaultViewHolder.() -> Unit

open class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Subscriber {

    private var bindView: BindView? = null
    private var unBindView: ViewHolderType? = null
    private var onViewAttached: ViewHolderType? = null
    private var onViewDetached: ViewHolderType? = null

    fun onBindViewHolder(f: BindView) {
        bindView = f
    }

    fun onUnBindViewHolder(f: ViewHolderType) {
        unBindView = f
    }

    fun onViewAttachedToWindow(f: ViewHolderType) {
        onViewAttached = f
    }

    fun onViewDetachedFromWindow(f: ViewHolderType) {
        onViewAttached = f
    }

    override fun onBindViewHolder(position: Int, payloads: List<Any>) {
        bindView?.invoke(this, payloads)
    }

    override fun unBindViewHolder(position: Int) {
        unBindView?.invoke(this)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewAttached?.invoke(holder as DefaultViewHolder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewDetached?.invoke(holder as DefaultViewHolder)
    }

}
