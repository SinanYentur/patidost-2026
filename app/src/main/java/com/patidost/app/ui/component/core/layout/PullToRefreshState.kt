package com.patidost.app.ui.component.core.layout

import androidx.compose.runtime.*

/**
 * PullToRefreshState - V10000.5001 Rule 101.
 * RVWL: Stable state management with JVM Signature conflict fix.
 */
@Stable
class PullToRefreshState(
    val refreshThreshold: Float = 80f,
    val maxOffset: Float = 150f
) {
    var offset by mutableFloatStateOf(0f)
        private set
    
    // Rule: Avoid manual setRefreshing if var isRefreshing is public, 
    // or use private var + explicit getter/setter to avoid JVM clash.
    var isRefreshing by mutableStateOf(false)

    internal fun onPull(delta: Float): Float {
        if (isRefreshing) return 0f
        val newOffset = (offset + delta).coerceIn(0f, maxOffset)
        val consumed = newOffset - offset
        offset = newOffset
        return consumed
    }

    internal fun onRelease() {
        if (offset > refreshThreshold) {
            isRefreshing = true
        } else {
            offset = 0f
        }
    }

    // JVM Signature Clash Fixed: setRefreshing(Boolean) logic merged into property setter or renamed.
    fun resetOffset() {
        if (!isRefreshing) offset = 0f
    }
}
