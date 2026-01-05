package com.patidost.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.patidost.app.ui.screen.pet.list.PetListViewModel
import com.patidost.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenNew(
    viewModel: PetListViewModel,
    onPetClick: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToDiscover: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Pets, null, tint = Color(0xFFF59E0B), modifier = Modifier.size(28.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Patidost", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = Color(0xFF1F2937))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        BadgedBox(badge = { Badge(containerColor = Color.Red) }) {
                            Icon(Icons.Outlined.Notifications, null, tint = Color(0xFF6B7280))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.CalendarToday, null, tint = Color(0xFF6B7280))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            SovereignBottomNavV2(onNavigateToProfile, onNavigateToDiscover, onNavigateToCart)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF3F6F9)) // HTML'deki background-light
        ) {
            // 🏆 Pati Puan Kartı (HTML'deki Gradient Tasarım)
            item {
                PatiPuanCardV2(850, 1000)
            }

            // ⭐ Haftanın Yıldızları (HTML'deki Madalyalı Tasarım)
            item {
                SectionHeaderV2("Haftanın Yıldızları", "Tümünü Gör")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    itemsIndexed(listOf("Serkan", "Eda", "Melis", "Can", "Burak")) { index, name ->
                        StarUserItemV2(name, index + 1)
                    }
                }
            }

            // 💭 Paylaşım Alanı
            item {
                ShareThoughtsAreaV2()
            }

            // 📱 Feed Posts
            items(uiState.pets) { pet ->
                FeedPostItemV2(pet)
            }

            // 🐾 En Çok Sevilen Pati Dostlar (HTML'deki Yeni Bölüm)
            item {
                SectionHeaderV2("En Çok Sevilen Pati Dostlar", "")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    itemsIndexed(uiState.pets.take(3)) { index, pet ->
                        PopularPetCard(pet, index + 1)
                    }
                }
            }
        }
    }
}

@Composable
fun StarUserItemV2(name: String, rank: Int) {
    val borderColor = when(rank) {
        1 -> Brush.sweepGradient(listOf(Color(0xFFFFD700), Color(0xFFFFA500)))
        2 -> Brush.sweepGradient(listOf(Color(0xFFC0C0C0), Color(0xFF808080)))
        3 -> Brush.sweepGradient(listOf(Color(0xFFCD7F32), Color(0xFF8B4513)))
        else -> Brush.sweepGradient(listOf(Color(0xFFF59E0B), Color(0xFFFEF3C7)))
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .border(3.dp, borderColor, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            if (rank <= 3) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(if(rank==1) Color(0xFFF59E0B) else if(rank==2) Color(0xFF9CA3AF) else Color(0xFFD97706))
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("$rank", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Text(name, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1F2937), modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun PopularPetCard(pet: com.patidost.app.domain.model.Pet, rank: Int) {
    Card(
        modifier = Modifier.width(150.dp).shadow(4.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box {
                AsyncImage(
                    model = pet.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.padding(4.dp).clip(CircleShape).background(Color(0xFFF59E0B)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                    Text("#$rank", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(pet.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(pet.breed, fontSize = 10.sp, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFF59E0B), modifier = Modifier.size(12.dp))
                    Text("4.9", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun PatiPuanCardV2(current: Int, total: Int) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.background(Brush.horizontalGradient(listOf(Color(0xFFEFF6FF), Color(0xFFEEF2FF))))) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Icon(Icons.Filled.EmojiEvents, null, tint = Color(0xFFF59E0B))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("$current", color = Color(0xFFF59E0B), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        Text(" / $total Pati Puan", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp, bottom = 2.dp))
                    }
                    LinearProgressIndicator(
                        progress = { current.toFloat() / total.toFloat() },
                        modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape),
                        color = Color(0xFFF59E0B),
                        trackColor = Color(0xFFE5E7EB)
                    )
                }
                TextButton(onClick = {}) {
                    Text("Detaylar", color = Color(0xFF6366F1), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun ShareThoughtsAreaV2() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp).clip(RoundedCornerShape(28.dp)).background(Color.White).padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFFED7AA)))
        Text("Düşüncelerini paylaş...", color = Color.Gray, modifier = Modifier.weight(1f).padding(start = 12.dp), fontSize = 14.sp)
        IconButton(onClick = {}, modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFFF59E0B))) {
            Icon(Icons.Filled.Send, null, tint = Color.White, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun FeedPostItemV2(pet: com.patidost.app.domain.model.Pet) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(32.dp), // HTML'deki rounded-3xl
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray))
                    Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(Color(0xFF22C55E)).border(2.dp, Color.White, CircleShape))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Zeynep & Leo", fontWeight = FontWeight.Bold, color = Color(0xFF1F2937), fontSize = 14.sp)
                    Text("10 dk önce • İstanbul", color = Color.Gray, fontSize = 11.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Filled.MoreVert, null, tint = Color.Gray)
            }
            Text("Sabah yürüyüşünde parkın tadını çıkardık. Leo bugün çok enerjikti! 🐾☀️", color = Color(0xFF374151), fontSize = 14.sp, modifier = Modifier.padding(vertical = 12.dp))
            AsyncImage(
                model = pet.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(250.dp).clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.fillMaxWidth().padding(top = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.FavoriteBorder, null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Outlined.ChatBubbleOutline, null, tint = Color.Gray)
                    Text("12", modifier = Modifier.padding(start = 4.dp), fontSize = 12.dp.value.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Outlined.Send, null, tint = Color.Gray)
                }
                Icon(Icons.Outlined.BookmarkBorder, null, tint = Color.Gray)
            }
        }
    }
}

@Composable
fun SectionHeaderV2(title: String, action: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1F2937))
        if (action.isNotEmpty()) Text(action, color = Color(0xFFF59E0B), fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SovereignBottomNavV2(onProfile: () -> Unit, onDiscover: () -> Unit, onCart: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(90.dp)) {
        Surface(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(70.dp),
            color = Color.White,
            shadowElevation = 20.dp
        ) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                BottomNavItemV2(Icons.Filled.Pets, "Evim", true) {}
                BottomNavItemV2(Icons.Filled.Search, "Keşfet", false) { onDiscover() }
                Spacer(modifier = Modifier.width(40.dp))
                BottomNavItemV2(Icons.Filled.ChatBubble, "Mesajlar", false) { onCart() }
                BottomNavItemV2(Icons.Filled.Person, "Profil", false) { onProfile() }
            }
        }
        FloatingActionButton(
            onClick = { onDiscover() },
            modifier = Modifier.align(Alignment.TopCenter).size(60.dp).offset(y = (-5).dp),
            containerColor = Color(0xFFF59E0B),
            contentColor = Color.White,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(Icons.Filled.Add, null, modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun BottomNavItemV2(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onClick() }) {
        Icon(icon, null, tint = if (selected) Color(0xFFF59E0B) else Color.Gray, modifier = Modifier.size(24.dp))
        Text(label, fontSize = 10.sp, color = if (selected) Color(0xFFF59E0B) else Color.Gray)
    }
}
