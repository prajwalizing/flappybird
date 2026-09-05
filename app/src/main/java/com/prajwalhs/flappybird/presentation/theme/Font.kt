package com.prajwalhs.flappybird.presentation.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.prajwalhs.flappybird.R

private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val OutfitGoogleFont = GoogleFont("Outfit")
private val IBMPlexMonoGoogleFont = GoogleFont("IBM Plex Mono")

val OutfitFontFamily = FontFamily(
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Light),
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold),
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Bold),
    Font(googleFont = OutfitGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.ExtraBold)
)

val IBMPlexMonoFontFamily = FontFamily(
    Font(googleFont = IBMPlexMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
    Font(googleFont = IBMPlexMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
    Font(googleFont = IBMPlexMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold)
)
