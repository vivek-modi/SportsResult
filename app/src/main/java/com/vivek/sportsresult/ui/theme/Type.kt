package com.vivek.sportsresult.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vivek.sportsresult.R

val customFont = FontFamily(
    Font(R.font.source_sans_variable_400, weight = FontWeight.Normal),
    Font(R.font.source_sans_variable_500, weight = FontWeight.Medium),
    Font(R.font.source_sans_variable_600, weight = FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = customFont,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
)