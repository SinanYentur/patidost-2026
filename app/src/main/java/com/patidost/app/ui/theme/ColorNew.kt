package com.patidost.app.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object PatidostColors {
    val Primary = Color(0xFFF59E0B)
    val PrimaryDark = Color(0xFFD97706)
    val PrimaryLight = Color(0xFFFEF3C7)
    val BackgroundLight = Color(0xFFF3F6F9)
    val BackgroundDark = Color(0xFF0F172A)
    val SurfaceLight = Color(0xFFFFFFFF)
    val SurfaceDark = Color(0xFF1E293B)
    val TextMainLight = Color(0xFF1F2937)
    val TextMainDark = Color(0xFFF9FAFB)
    val TextSubLight = Color(0xFF6B7280)
    val TextSubDark = Color(0xFF9CA3AF)
    val Success = Color(0xFF22C55E)
    val Error = Color(0xFFEF4444)
}

val PatiGold = PatidostColors.Primary
val SnowyWhite = PatidostColors.BackgroundLight
val TextDark = PatidostColors.TextMainLight
val TextGray = PatidostColors.TextSubLight
val GlassBorder = Color(0xFFE5E7EB)

val SnowyGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFFEFF6FF), PatidostColors.BackgroundLight)
)

fun calculateSnowyGradient() = SnowyGradient
