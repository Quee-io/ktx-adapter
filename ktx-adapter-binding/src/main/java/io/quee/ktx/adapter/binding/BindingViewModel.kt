package io.quee.ktx.adapter.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.quee.ktx.adapter.core.DefaultViewHolder
import io.quee.ktx.adapter.core.DefaultViewModel

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
open class BindingViewModel<M>(override val layoutRes: Int, private val variableId: Int) :
    DefaultViewModel<M>() {

    override fun getHolderItemView(parent: ViewGroup, layoutInflater: LayoutInflater): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutRes,
            parent,
            false
        )
        val view = binding.root
        view.setTag(R.id.list_adapter_binding, binding)
        return view
    }

    override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
        viewHolder.getBinding().setVariable(variableId, model)
        viewHolder.getBinding().executePendingBindings()
    }

    override fun unBindVH(viewHolder: DefaultViewHolder) {
        viewHolder.getBinding().unbind()
    }

}