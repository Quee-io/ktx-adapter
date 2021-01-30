package io.quee.ktx.adapter.binding

import io.quee.ktx.adapter.core.DefaultViewHolder

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
inline fun <M> bindingViewModelDsl(
    layoutRes: Int,
    variableId: Int,
    model: M,
    crossinline init: DefaultViewHolder.() -> Unit,
): BindingViewModel<M> {
    return BindingViewModel<M>(layoutRes, variableId).apply {
        this.model = model
        onCreateViewHolder {
            init.invoke(this)
        }
    }
}