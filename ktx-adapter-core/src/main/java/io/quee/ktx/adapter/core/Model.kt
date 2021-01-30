package io.quee.ktx.adapter.core

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
interface SameModel {
    var uniqueId: String
    fun <T : SameModel> isSameModelAs(model: T): Boolean {
        return this.uniqueId == model.uniqueId
    }

    fun <T : SameModel> isContentTheSameAs(model: T): Boolean {
        return this == model
    }

    fun <T : SameModel> getChangePayload(newItem: T): Any? = null
}
