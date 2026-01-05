package com.patidost.app.ui.screen.pet.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patidost.app.ui.theme.*

/**
 * PetListScreen - V10006.00000 Snowy Glass A+++ Final Polish.
 * Directly based on the provided corrected high-fidelity code.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListScreen(
    viewModel: PetListViewModel,
    onPetClick: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToDiscover: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SnowyBlue, SnowyWhite)
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 🔝 TOP BAR (Corrected with Badge)
            TopAppBar(
                title = {},
                navigationIcon = {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = NotificationRed,
                                content = { Text("3", fontSize = 10.sp, color = Color.White) }
                            )
                        },
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Notifications, null, tint = TextDark)
                        }
                    }
                },
                actions = {
                    // 🏆 Pati Puanı
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(GlassWhite)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("45", color = PatiGold, fontWeight = FontWeight.Bold)
                            Text("/100", color = TextGray, fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(Color.LightGray.copy(alpha = 0.3f))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.45f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(PatiGold)
                                )
                            }
                        }
                    }
                    
                    // 👤 Profil fotoğrafı
                    IconButton(onClick = onNavigateToProfile, modifier = Modifier.padding(end = 8.dp)) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PatiGold)
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                null,
                                tint = Color.Black,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
            
            // 🐾 PET SLOTS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(4) { index ->
                    PetSlot(isEmpty = index > 1)
                }
                
                // + Butonu
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Brush.radialGradient(colors = listOf(PatiGoldLight, PatiGold)))
                        .clickable { /* Add Pet Logic */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 32.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
            
            // 📱 FEED AREA
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {
                item {
                    FeedHeaderLine()
                    EmptyStateArea()
                }
                items(3) { index ->
                    PetPostCard(index)
                }
            }
        }

        // 🚀 BOTTOM NAVIGATION (Floating Cam Bar - Final Corrected)
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            SovereignBottomNav(onNavigateToDiscover, onNavigateToCart, onNavigateToProfile)
        }
    }
}

@Composable
fun FeedHeaderLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.height(1.dp).weight(1f).background(TextGray.copy(alpha = 0.2f)))
        Text(
            "FEED",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = TextDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
        Box(modifier = Modifier.height(1.dp).weight(1f).background(TextGray.copy(alpha = 0.2f)))
    }
}

@Composable
fun EmptyStateArea() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Henüz dostumuz yok", color = TextGray, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier.width(180.dp).height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GlassWhite)
        ) {
            Text("Add Pet", color = TextDark, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PetSlot(isEmpty: Boolean) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .drawWithCache {
                onDrawBehind {
                    drawCircle(color = GlassWhite, radius = size.width / 2)
                    drawCircle(
                        color = GlassBorder,
                        radius = size.width / 2 - 2.dp.toPx(),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        if (!isEmpty) {
            Box(modifier = Modifier.size(50.dp).clip(CircleShape).background(PatiGold)) {
                Icon(Icons.Filled.Pets, null, tint = Color.Black, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun PetPostCard(index: Int) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = GlassWhite.copy(alpha = 0.6f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(PatiGold)) {
                    Icon(Icons.Filled.Person, null, tint = Color.Black, modifier = Modifier.align(Alignment.Center))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Zeynep & Leo", fontWeight = FontWeight.Bold, color = TextDark)
                    Text("10 dk önce", fontSize = 12.sp, color = TextGray)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Morning walk in the park. Leo was so happy!", color = TextDark)
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Favorite, null, tint = NotificationRed, modifier = Modifier.size(20.dp))
                Text("45", modifier = Modifier.padding(start = 4.dp, end = 16.dp), color = TextDark)
                Icon(Icons.Filled.Comment, null, modifier = Modifier.size(20.dp), tint = TextDark)
                Text("12", modifier = Modifier.padding(start = 4.dp), color = TextDark)
            }
        }
    }
}

@Composable
fun SovereignBottomNav(onDiscover: () -> Unit, onMessages: () -> Unit, onProfile: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .height(70.dp),
        shape = RoundedCornerShape(35.dp),
        color = GlassWhite,
        tonalElevation = 8.dp,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(Icons.Filled.Pets, "Pets", true) {}
            BottomNavItem(Icons.Filled.Search, "Discover", false, onDiscover)
            BottomNavItem(Icons.Filled.Message, "Messages", false, onMessages)
            BottomNavItem(Icons.Filled.Person, "Profile", false, onProfile)
        }
    }
}

@Composable
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(icon, null, tint = if (selected) PatiGold else TextDark, modifier = Modifier.size(28.dp))
        Text(label, fontSize = 10.sp, color = if (selected) PatiGold else TextDark)
    }
}
