package io.quee.ktx.adapter.sorted

import io.quee.ktx.adapter.core.SameModel

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
interface SortedModel : SameModel {
    fun <T : SortedModel> compare(model: T): Int
}
