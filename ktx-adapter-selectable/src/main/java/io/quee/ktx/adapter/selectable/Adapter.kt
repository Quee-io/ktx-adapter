package io.quee.ktx.adapter.selectable

import android.util.SparseArray
import android.util.SparseBooleanArray
import androidx.recyclerview.widget.RecyclerView

private val selectedItemsCache = SparseArray<SparseBooleanArray>()
private val selectConfigCache = SparseArray<SparseArray<Any>>()

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
private val defaultSelectedConfig by lazy {
    SparseArray<Any>().apply {
        append(0, true) // is Multi Selectable
        append(1, Int.MAX_VALUE) // Selectable Max Size Default Int.Max
    }
}

private fun getSelectedItems(key: Int): SparseBooleanArray {
    return selectedItemsCache[key] ?: SparseBooleanArray().apply {
        selectedItemsCache.append(key, this)
    }
}

private fun getSelectConfig(key: Int): SparseArray<Any> {
    return selectConfigCache[key] ?: defaultSelectedConfig.apply {
        selectConfigCache.append(key, this)
    }
}

var RecyclerView.Adapter<*>.isMultiSelect
    get() = getSelectConfig(hashCode())[0] as Boolean
    private set(value) {}

var RecyclerView.Adapter<*>.selectedMaxSize: Int
    get() = getSelectConfig(hashCode())[1] as Int
    private set(value) {}

var RecyclerView.Adapter<*>.selectedCount: Int
    get() = getSelectedItems(hashCode()).size()
    private set(value) {}

fun RecyclerView.Adapter<*>.setMultiSelectable(enable: Boolean) {
    getSelectConfig(hashCode()).setValueAt(0, enable)
    if (!enable && selectedCount > 1) {
        clearSelection()
    }
}

fun RecyclerView.Adapter<*>.setSelectableMaxSize(size: Int) {
    getSelectConfig(hashCode()).setValueAt(1, size)
}

fun RecyclerView.Adapter<*>.getSelectedItems(): List<Int> {
    val si = getSelectedItems(hashCode())
    val itemSize = si.size()
    val items: MutableList<Int> = ArrayList(itemSize)
    for (i in 0 until itemSize) {
        items.add(si.keyAt(i))
    }
    return items
}

fun RecyclerView.Adapter<*>.isSelected(position: Int) = getSelectedItems().contains(position)

fun RecyclerView.Adapter<*>.clearSelection() {
    val selection = getSelectedItems()
    getSelectedItems(hashCode()).clear()
    for (i in selection) {
        notifyItemChanged(i)
    }
}

fun RecyclerView.Adapter<*>.toggleSelection(
    position: Int,
    isMaxSelect: ((Boolean) -> Unit)? = null,
) {
    val si = getSelectedItems(hashCode())
    val isSelect = si.get(position, false)
    if (selectedCount >= selectedMaxSize && !isSelect) {
        isMaxSelect?.invoke(true)
        return
    }
    isMaxSelect?.invoke(false)
    if (!isMultiSelect) {
        clearSelection()
    }
    if (isSelect) {
        si.delete(position)
    } else {
        si.put(position, true)
    }
    notifyItemChanged(position)
}

fun RecyclerView.Adapter<*>.onDestroy() {
    val key = hashCode()
    selectedItemsCache[key]?.clear()
    selectConfigCache[key]?.clear()
}