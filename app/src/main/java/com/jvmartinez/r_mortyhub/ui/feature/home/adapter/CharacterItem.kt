package com.jvmartinez.r_mortyhub.ui.feature.home.adapter

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.jvmartinez.r_mortyhub.R
import com.jvmartinez.r_mortyhub.ui.theme.LucerneLight

@Composable
private fun ShimmerBox(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerAnimation = infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_animation"
    )
    val brush = Brush.linearGradient(
        colors = listOf(Color.LightGray, Color.White, Color.LightGray),
        start = Offset(shimmerAnimation.value * 1000f, 0f),
        end = Offset(shimmerAnimation.value * 1000f + 1000f, 0f)
    )
    Box(
        modifier = modifier.background(brush),
    )
}

@Composable
fun CharacterItem(
    imageUrl: String,
    name: String,
    status: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(BorderStroke(1.dp, LucerneLight), RoundedCornerShape(10.dp)),
    ) {
        Card(
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, LucerneLight)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.character_image),
                modifier = Modifier.fillMaxSize(),
                loading = {
                    ShimmerBox(modifier = Modifier.fillMaxSize())
                },
                success = {
                    SubcomposeAsyncImageContent()
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                    )
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.name_label) + name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.status_label) + status,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        name = "Rick Sanchez",
        status = "Alive"
    )
}