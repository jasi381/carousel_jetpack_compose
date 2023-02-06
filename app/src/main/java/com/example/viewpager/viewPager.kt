package com.example.viewpager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
 fun Sample() {
    Scaffold(
        backgroundColor = Color.Cyan,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                backgroundColor = Color.DarkGray,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 150.dp)) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = 10,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 1.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                PagerSampleItem(
                    modifier = Modifier
                        .height(290.dp).fillMaxWidth()
                        .aspectRatio(1f)
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(17.dp),
            )
        }
    }
}

@Composable
internal fun PagerSampleItem(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Image(
            painter = rememberAsyncImagePainter(model = rememberRandomSampleImageUrl(width = 250, height = 250)),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(250.dp).fillMaxWidth()
                .padding(5.dp).clip(RoundedCornerShape(10.dp))
        )
    }
}

private val rangeForRandom = (0..100000)

fun randomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 250,
    height: Int = 250,
): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}

@Composable
fun rememberRandomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 250,
    height: Int = width,

): String = remember { randomSampleImageUrl(seed, width, height) }

@Preview
@Composable
fun Preview() {
    Sample()
}
