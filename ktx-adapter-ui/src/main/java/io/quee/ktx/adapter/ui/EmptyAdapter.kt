package io.quee.ktx.adapter.ui

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.quee.ktx.adapter.core.DefaultViewHolder
import io.quee.ktx.adapter.core.LayoutViewModel
import io.quee.ktx.adapter.core.ViewModel
import io.quee.ktx.adapter.core.getView
import io.quee.ktx.adapter.ui.common.WrapAdapter

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
class DefaultEmptyViewModel : LayoutViewModel<EmptyState>(R.layout.item_empty_layout) {
    init {
        model = EmptyState.NotLoading
    }

    override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
        val text = viewHolder.getView<TextView>(R.id.item_empty_text)
        val progress = viewHolder.getView<ProgressBar>(R.id.item_empty_progress)
        when (model) {
            EmptyState.NotLoading -> {
                text.visibility = View.VISIBLE
                progress.visibility = View.INVISIBLE
                text.text = "数据为空，点我加载"
            }
            EmptyState.Loading -> {
                text.visibility = View.INVISIBLE
                progress.visibility = View.VISIBLE
                text.text = "加载中"
            }
            EmptyState.Loaded -> {
                text.visibility = View.VISIBLE
                progress.visibility = View.INVISIBLE
                text.text = "加载成功"
            }
            EmptyState.Error -> {
                text.visibility = View.VISIBLE
                progress.visibility = View.INVISIBLE
                text.text = "加载失败，点我重试"
            }
        }
    }
}

open class EmptyAdapter(
    mWrappedAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    private val viewModel: ViewModel<EmptyState, DefaultViewHolder> = DefaultEmptyViewModel(),
) : WrapAdapter<RecyclerView.ViewHolder>(mWrappedAdapter) {

    var emptyState: EmptyState = EmptyState.NotLoading
        set(value) {
            if (field != value) {
                notifyDataSetChanged()
            }
            field = value
            viewModel.model = value
        }

    override fun getItemCount(): Int {
        return if (displayEmptyView(emptyState)) {
            1
        } else {
            super.getItemCount()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (displayEmptyView(emptyState)) {
            viewModel.itemViewType
        } else {
            super.getItemViewType(position)
        }
    }

    override fun getItemId(position: Int): Long {
        return if (displayEmptyView(emptyState)) {
            super.getItemId(position * 2)
        } else {
            super.getItemId(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (displayEmptyView(emptyState)) {
            viewModel.getViewHolder(parent, LayoutInflater.from(parent.context)).apply {
                itemView.setOnClickListener {
                    if (emptyState == EmptyState.NotLoading || emptyState == EmptyState.Error) {
                        emptyState = EmptyState.Loading
                        Handler().postDelayed({
                            emptyState = EmptyState.Loaded
                        }, 2000)
                    }
                }
            }
        } else {
            super.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>,
    ) {
        if (displayEmptyView(emptyState)) {
            viewModel.bindVH(holder as DefaultViewHolder, payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    open fun displayEmptyView(emptyState: EmptyState): Boolean {
        return emptyState == EmptyState.Loading || emptyState == EmptyState.Error || emptyState == EmptyState.NotLoading
    }

}

enum class EmptyState {
    NotLoading, Loading, Loaded, Error
}
