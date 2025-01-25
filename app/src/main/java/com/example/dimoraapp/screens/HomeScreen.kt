package com.example.dimoraapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
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
        Column(modifier = Modifier.fillMaxSize()) {
            TopNavBar(onMenuClick = { isDrawerOpen = true })
            Spacer(modifier = Modifier.height(16.dp))
            Heading("House")
            Spacer(modifier = Modifier.height(16.dp))
            PicturesApp(navController)
            Spacer(modifier = Modifier.height(16.dp))
            MoreButton(onClick = { /* Navigate to a new page */ })
        }

        if (isDrawerOpen) {
            SideNavBar(onClose = { isDrawerOpen = false }, onAboutUsClick = {
                // Handle About Us button click
                navController.navigate("about_us")
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                    tint = Color(0xFF28302B)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Add your action for the bookmark icon */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Bookmark",
                    tint = Color(0xFF28302B)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFEFEFE9)
        )
    )
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
                    navController.navigate("") // Pass picture ID
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
            .background(Color(0xFFEFEFE9))
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
