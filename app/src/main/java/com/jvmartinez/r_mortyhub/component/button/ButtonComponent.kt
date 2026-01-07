package com.jvmartinez.r_mortyhub.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvmartinez.r_mortyhub.R
import com.jvmartinez.r_mortyhub.ui.theme.VibrantCyan

@Composable
fun ButtonComponent(
    modifier: Modifier? = null,
    titleButton: String,
    isEnabled: Boolean = false,
    action: () -> Unit = { },
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                action()
            },
            enabled = isEnabled,
            modifier = (modifier ?: Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = (if (isEnabled) VibrantCyan else Color.LightGray))
        ) {
            Text(
                text = titleButton,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ButtonComponentPreview() {
    Box {
        ButtonComponent(titleButton = stringResource(id = R.string.retry_error), isEnabled = true)
    }
}