package io.quee.ktx.adapter.paging


import androidx.recyclerview.widget.DiffUtil.ItemCallback
import io.quee.ktx.adapter.core.SameModel
import io.quee.ktx.adapter.core.ViewModelType

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
class DiffViewModelCallBack : ItemCallback<ViewModelType>() {
    override fun areItemsTheSame(
        oldItem: ViewModelType,
        newItem: ViewModelType,
    ): Boolean {
        return (oldItem.model as? SameModel)?.isSameModelAs(newItem.model as SameModel) ?: false
    }

    override fun areContentsTheSame(
        oldItem: ViewModelType,
        newItem: ViewModelType,
    ): Boolean {
        return (oldItem.model as? SameModel)?.isContentTheSameAs(newItem.model as SameModel)
            ?: false
    }

    override fun getChangePayload(oldItem: ViewModelType, newItem: ViewModelType): Any? {
        return (oldItem.model as? SameModel)?.getChangePayload(oldItem.model as SameModel)
    }
}