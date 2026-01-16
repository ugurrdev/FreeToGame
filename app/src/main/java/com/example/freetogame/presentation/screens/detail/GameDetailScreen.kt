package com.example.freetogame.presentation.screens.detail

import android.content.Intent
import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.freetogame.presentation.components.Badge
import com.example.freetogame.presentation.components.RequirementRow
import com.example.freetogame.presentation.components.SectionTitle

@Composable
fun GameDetailScreen(
    navController: NavController,
    viewModel: GameDetailViewModel
) {
    val game by viewModel.selectedGame.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current
    var showSlider by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }

    if (isLoading || game == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    } else {
        val detail = game!!
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)) {
            
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 8.dp), 
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = detail.title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context).data(detail.thumbnail).crossfade(true).build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    Badge(detail.genre)
                    Spacer(modifier = Modifier.width(8.dp))
                    Badge(detail.platform)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, detail.gameUrl.toUri())) },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) { Text("Play Now", fontSize = 18.sp, fontWeight = FontWeight.Bold) }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Check out: ${detail.title}\n${detail.gameUrl}")
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Share"))
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) { Text("Share", fontSize = 18.sp, fontWeight = FontWeight.SemiBold) }

                Spacer(modifier = Modifier.height(32.dp))
                SectionTitle("About")
                Text(
                    text = detail.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))
                SectionTitle("Screenshots")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 16.dp)) {
                    itemsIndexed(items = detail.screenshots) { index, shot ->
                        AsyncImage(
                            model = ImageRequest.Builder(context).data(shot.image).crossfade(true).build(),
                            contentDescription = null,
                            modifier = Modifier
                                .height(180.dp)
                                .width(300.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    selectedImageIndex = index
                                    showSlider = true
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                detail.minimumSystemRequirements?.let { req ->
                    SectionTitle("System Requirements")
                    RequirementRow("OS", req.os)
                    RequirementRow("Processor", req.processor)
                    RequirementRow("Memory", req.memory)
                    RequirementRow("Graphics", req.graphics)
                    RequirementRow("Storage", req.storage)
                }
            }
        }

        if (showSlider) {
            Dialog(
                onDismissRequest = { showSlider = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                val blurModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) Modifier.blur(20.dp) else Modifier.background(Color.Black.copy(alpha = 0.8f))

                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize().then(blurModifier))

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.4f))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { showSlider = false }
                            )
                    ) {
                        val pagerState = rememberPagerState(initialPage = selectedImageIndex, pageCount = { detail.screenshots.size })

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 32.dp),
                            pageSpacing = 16.dp
                        ) { page ->
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                AsyncImage(
                                    model = ImageRequest.Builder(context).data(detail.screenshots[page].image).build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1.6f)
                                        .clip(RoundedCornerShape(16.dp))
                                        .clickable(enabled = false) { },
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        IconButton(
                            onClick = { showSlider = false },
                            modifier = Modifier.align(Alignment.TopEnd).padding(24.dp).background(Color.Black.copy(alpha = 0.6f), CircleShape)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}
