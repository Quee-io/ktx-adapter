package io.quee.ktx.adapter.core

import androidx.recyclerview.widget.RecyclerView

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
interface Subscriber {
    fun onBindViewHolder(position: Int, payloads: List<Any>) {}
    fun unBindViewHolder(position: Int) {}
    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder, position: Int) {}
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder, position: Int) {}
}
