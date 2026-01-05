package com.patidost.app.ui.component

import android.graphics.BlurMaskFilter
import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.glassEffect(
    blurRadius: Float = 20f
): Modifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    this.graphicsLayer {
        renderEffect = RenderEffect.createBlurEffect(
            blurRadius, blurRadius, android.graphics.Shader.TileMode.CLAMP
        ).asComposeRenderEffect()
    }
} else {
    this // API 31 altı için fallback (V46.10 standardı)
}
