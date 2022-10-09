package com.example.spacextestapp.presentation.list.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.abs


class ScaleLayoutManager(
    val context: Context?,
    var orientationB: Int,
    reverseLayout: Boolean = false
) :
    LinearLayoutManager(context) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.width = width
        return true
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleMiddleItem()
    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollVerticallyBy(dy, recycler, state)
        if(orientationB == RecyclerView.VERTICAL) {
            scaleMiddleItem()
            return scrolled
        }
        else {
            return 0
        }
    }

    private fun scaleMiddleItem() {
        val mid = height/2f
        val d1 = 0.9f * mid
        for(i in 0..childCount)  {
            val child: View? = getChildAt(i)
            child?.let {
                val childMid = (getDecoratedBottom(child) + getDecoratedTop(child))/2f
                val d = d1.coerceAtMost(abs(mid - childMid))
                val scaleY: Float = 1f - 0.9f * d/d1
                val scaleX: Float = 1f - 0.3f * d/d1
                val alpha: Float = 1f - 0.5f * d/d1

                child.scaleX = scaleX
                child.scaleY = scaleY
                child.alpha = alpha
            }
        }
    }
}