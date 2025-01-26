package com.example.dimoraapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.ui.theme.DMserif
import com.example.dimoraapp.R
import com.example.dimoraapp.model.Picture
import com.example.dimoraapp.data.Datasource

@Composable
fun HomeScreen(navController: NavController) {
    var isDrawerOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween // Ensures proper spacing
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TopNavBar(onMenuClick = { isDrawerOpen = true })
                Spacer(modifier = Modifier.height(16.dp))
                Heading("House")
                Spacer(modifier = Modifier.height(16.dp))
                PicturesApp(navController)
                Spacer(modifier = Modifier.height(16.dp))
                MoreButton(onClick = { /* Navigate to a new page */ })
            }

            BottomNavBar(navController = navController) // Positioned at the bottom
        }

        if (isDrawerOpen) {
            SideNavBar(
                onClose = { isDrawerOpen = false },
                onAboutUsClick = { navController.navigate("about_us") }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = {},
        actions = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = Color(0xFF28302B),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFEFEFE9)
        )
    )
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFFEFEFE9)
    ) {
        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            },
            label = {
                Text(
                    text = stringResource(R.string.home),
                    fontWeight = FontWeight.Bold)
            },
            selected = false,
            onClick = { navController.navigate("home") }
        )

        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            },
            label = {
                Text(
                    text = stringResource(R.string.search),
                    fontWeight = FontWeight.Bold)
            },
            selected = false,
            onClick = { navController.navigate("search") }
        )

        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            },
            label = {
                Text(
                    text = stringResource(R.string.notifications),
                    fontWeight = FontWeight.Bold)
            },
            selected = false,
            onClick = { navController.navigate("notifications") }
        )

        NavigationBarItem(
            icon = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.LightGray, CircleShape), // Background circle
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image1),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape) // Ensures the image is circular
                    )
                }
            },
            label = {
                Text(
                    text = stringResource(R.string.profile),
                    fontWeight = FontWeight.Bold)
                },
            selected = false,
            onClick = { navController.navigate("profilescreen") }
        )
    }
}

@Composable
fun Heading(title: String) {
    Text(
        text = title,
        fontFamily = DMserif,
        fontSize = 35.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun MoreButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF28302B),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "More", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PictureCard(picture: Picture, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(250.dp)
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = picture.drawableResourseId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(picture.name),
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMserif
                )
                Text(
                    text = stringResource(picture.price),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMserif
                )
            }
        }
    }
}

@Composable
fun PicturesApp(navController: NavController) {
    PictureList(
        pictureList = Datasource().loadPictures(),
        navController = navController
    )
}

@Composable
fun PictureList(pictureList: List<Picture>, navController: NavController, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pictureList.size) { index ->
            PictureCard(
                picture = pictureList[index],
                onClick = {
                    navController.navigate("infoscreen")
                }
            )
        }
        item {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .padding(end = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { /* Handle next page action */ },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            color = Color(0xFFEFEFE9),
                            shape = CircleShape
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Next Page",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun SideNavBar(onClose: () -> Unit, onAboutUsClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color(0xFFEFEFE9)),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = onClose, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color(0xFF28302B)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAboutUsClick() }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "About Us",
                    tint = Color(0xFF28302B)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "About Us", color = Color(0xFF28302B), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = "Dark Mode",
                    tint = Color(0xFF28302B)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Dark Mode", color = Color(0xFF28302B), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = Color(0xFF28302B)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Settings", color = Color(0xFF28302B), fontSize = 18.sp)
            }
        }
    }
}
