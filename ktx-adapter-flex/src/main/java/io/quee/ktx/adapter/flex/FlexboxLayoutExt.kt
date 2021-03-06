package io.quee.ktx.adapter.flex

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*

/**
 * Created By [*Ibrahim AlTamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 31, **Sun January, 2021**
 * Project *ktx-adapter* [Quee.IO]
 */
fun Context.flexboxLayoutMangerDefault(block: FlexboxLayoutManager.() -> Unit = {}): FlexboxLayoutManager {
    val layoutManager = SafeFlexboxLayoutManager(this).apply {
        flexWrap = FlexWrap.WRAP
        alignItems = AlignItems.BASELINE
        justifyContent = JustifyContent.FLEX_START
    }
    layoutManager.block()
    return layoutManager
}

fun Context.flexboxLayoutManager(block: FlexboxLayoutManager.() -> Unit = {}): FlexboxLayoutManager {
    val layoutManager = SafeFlexboxLayoutManager(this)
    layoutManager.block()
    return layoutManager
}

fun Context.flexboxLayout(block: FlexboxLayout.() -> Unit = {}): FlexboxLayout {
    val layoutManager = FlexboxLayout(this)
    layoutManager.block()
    return layoutManager
}

class SafeFlexboxLayoutManager : FlexboxLayoutManager {

    constructor(context: Context) : super(context)

    constructor(context: Context, flexDirection: Int) : super(context, flexDirection)

    constructor(context: Context, flexDirection: Int, flexWrap: Int) : super(
        context,
        flexDirection,
        flexWrap
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams): RecyclerView.LayoutParams {
        return LayoutParams(lp)
    }

}
