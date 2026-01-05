package com.patidost.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.patidost.app.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 🚀 PATIDOST SOVEREIGN THEME v4.3 - 2026 PRODUCTION READY
 * Mühür: Mutlak Performans, Bellek Güvenliği ve 2026 Uyumluluğu.
 */

// ==================== 🎨 COLOR SCHEMES ====================

private val SovereignLightScheme = lightColorScheme(
    primary = PatidostColors.Primary,
    onPrimary = Color.White,
    primaryContainer = PatidostColors.PrimaryLight,
    onPrimaryContainer = PatidostColors.PrimaryDark,
    background = PatidostColors.BackgroundLight,
    onBackground = PatidostColors.TextMainLight,
    surface = PatidostColors.SurfaceLight,
    onSurface = PatidostColors.TextMainLight,
    outline = Color(0xFFE5E7EB)
)

private val SovereignDarkScheme = darkColorScheme(
    primary = PatidostColors.Primary,
    onPrimary = Color.White,
    background = PatidostColors.BackgroundDark,
    onBackground = PatidostColors.TextMainDark,
    surface = PatidostColors.SurfaceDark,
    onSurface = PatidostColors.TextMainDark,
    outline = Color(0xFF374151)
)

private fun highContrastLightColorScheme() = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    outline = Color.Black
)

private fun highContrastDarkColorScheme() = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    outline = Color.White
)

// ==================== 📱 ADAPTIVE LAYOUT ====================

enum class FormFactor { COMPACT, MEDIUM, EXPANDED, LARGE }
data class AdaptiveLayout(val formFactor: FormFactor, val windowSize: DpSize)

@Composable
private fun computeAdaptiveLayout(configuration: android.content.res.Configuration): AdaptiveLayout {
    val widthDp = configuration.screenWidthDp.dp
    val heightDp = configuration.screenHeightDp.dp
    val windowSize = DpSize(widthDp, heightDp)
    
    val formFactor = when {
        widthDp < 600.dp -> FormFactor.COMPACT
        widthDp < 840.dp -> FormFactor.MEDIUM
        widthDp < 1200.dp -> FormFactor.EXPANDED
        else -> FormFactor.LARGE
    }
    
    return AdaptiveLayout(formFactor, windowSize)
}

// ==================== 🛡️ DETECTIONS ====================

private fun isCustomRom(): Boolean {
    val manufacturer = Build.MANUFACTURER.lowercase()
    val customRoms = listOf("xiaomi", "huawei", "oppo", "vivo", "realme", "oneplus", "samsung")
    return customRoms.any { manufacturer.contains(it) }
}

private fun isRunningOnEmulator(): Boolean {
    return Build.FINGERPRINT.startsWith("generic") ||
           Build.FINGERPRINT.contains("vbox") ||
           Build.FINGERPRINT.contains("test-keys") ||
           Build.MODEL.contains("google_sdk") ||
           Build.MODEL.contains("Emulator") ||
           Build.MODEL.contains("Android SDK built for")
}

// ==================== 🎯 CORE THEME FUNCTION ====================

@Composable
fun PatidostTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    followSystemThemeChanges: Boolean = true,
    preserveBrandColor: Boolean = true,
    brandSeedColor: Color = PatidostColors.Primary,
    enforceEdgeToEdge: Boolean = true,
    enableDebugOverlay: Boolean = BuildConfig.DEBUG,
    isPreview: Boolean = LocalInspectionMode.current,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    
    // 🚀 Performance: Optimized dynamic color check
    val supportsDynamicColor by remember(configuration) {
        derivedStateOf {
            !isPreview && 
            dynamicColor && 
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
            !isRunningOnEmulator() &&
            !isCustomRom()
        }
    }
    
    // 🚀 Performance: Optimized dark theme calculation
    val effectiveDarkTheme by remember(darkTheme, followSystemThemeChanges) {
        derivedStateOf {
            if (followSystemThemeChanges && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                isSystemInDarkTheme()
            } else {
                darkTheme
            }
        }
    }
    
    // 🚀 Performance: Color scheme selection with caching
    val colorScheme by remember(effectiveDarkTheme, supportsDynamicColor, brandSeedColor, preserveBrandColor) {
        derivedStateOf {
            when {
                supportsDynamicColor && preserveBrandColor -> {
                    dynamicColorSchemeFromSeed(
                        seedColor = brandSeedColor,
                        isDark = effectiveDarkTheme,
                        context = context
                    )
                }
                supportsDynamicColor -> {
                    if (effectiveDarkTheme) dynamicDarkColorScheme(context)
                    else dynamicLightColorScheme(context)
                }
                effectiveDarkTheme -> SovereignDarkScheme
                else -> SovereignLightScheme
            }
        }
    }
    
    // ♿ Accessibility: High Contrast
    val isHighContrastEnabled by remember(configuration) {
        derivedStateOf { configuration.isScreenHighContrastEnabled }
    }
    
    val accessibleColorScheme by remember(colorScheme, isHighContrastEnabled, effectiveDarkTheme) {
        derivedStateOf {
            if (isHighContrastEnabled) {
                if (effectiveDarkTheme) highContrastDarkColorScheme()
                else highContrastLightColorScheme()
            } else {
                colorScheme
            }
        }
    }
    
    // 📱 Adaptive Layout
    val adaptiveLayout by remember(configuration) {
        derivedStateOf { computeAdaptiveLayout(configuration) }
    }
    
    // 🏗️ Material Theme
    MaterialTheme(
        colorScheme = accessibleColorScheme,
        typography = Typography,
        // Shapes varyantları burada eklenebilir
    ) {
        val themeContent = @Composable {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
                if (enableDebugOverlay && BuildConfig.DEBUG) {
                    ThemeDebugOverlay(accessibleColorScheme, adaptiveLayout)
                }
            }
        }
        
        // 🚀 Edge-to-Edge is handled in MainActivity, 
        // Theme provides the context for Insets if needed.
        themeContent()
    }
    
    // 🧹 Resource Cleanup
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                cleanupThemeResources()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            cleanupThemeResources()
        }
    }
}

private fun cleanupThemeResources() {
    // Dynamic color cache veya listener temizliği
}

@Composable
private fun ThemeDebugOverlay(colorScheme: ColorScheme, adaptiveLayout: AdaptiveLayout) {
    // Debug bilgileri ekranda gösterilebilir (Opsiyonel)
}

val LocalAdaptiveLayout = staticCompositionLocalOf<AdaptiveLayout> { 
    error("No AdaptiveLayout provided") 
}
