package io.quee.ktx.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import io.quee.ktx.adapter.core.ListAdapter

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
fun ListAdapter.calculateDiff(
    newItems: List<ViewModelDiffType>,
) {
    val result = DiffUtil.calculateDiff(
        ArrayListAdapterCallBack(
            oldItems = dataList as? List<ViewModelDiffType>
                ?: throw Exception("please let model implements SameModel"),
            newItems = newItems
        )
    )
    replayAll(newItems)
    result.dispatchUpdatesTo(this)
}
