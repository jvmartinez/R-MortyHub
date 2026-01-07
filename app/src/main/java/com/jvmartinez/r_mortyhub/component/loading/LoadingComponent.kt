package com.jvmartinez.r_mortyhub.component.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jvmartinez.r_mortyhub.ui.theme.LucerneLight

@Composable
fun LoadingComponent(
    color: Color = LucerneLight
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center),
            color = color
        )
    }
}