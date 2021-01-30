package io.quee.ktx.adapter.binding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
fun RecyclerView.ViewHolder.getBinding(): ViewDataBinding {
    return itemView.getTag(R.id.list_adapter_binding) as ViewDataBinding
}