package com.example.dimoraapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.R
import com.example.dimoraapp.data.Datasource
import com.example.dimoraapp.model.PictureInfo
import com.example.dimoraapp.navigation.BottomNavBar

@Composable
fun InfoScreen(navController: NavController){
    var isDrawerOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Content layout
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // LazyColumn for scrollable content
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Take up remaining vertical space
                    .fillMaxWidth(),
            ) {
                item { TopNavBarInfo(
                    goToHomePage = { navController.navigate("homescreen")},
                    onMenuClick = { isDrawerOpen = true })
                }
                item { BannerImage() }
                item { PicturesList() }
            }

            // Bottom navigation bar
            BottomNavBar(navController = navController)
        }

        // Side drawer for navigation
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
fun TopNavBarInfo(goToHomePage: () -> Unit, onMenuClick: () -> Unit) {
    TopAppBar(
        title = {Text(text = "About Us", color = Color(0xFF28302B), fontSize = 18.sp)},
        navigationIcon = {
            IconButton(onClick = goToHomePage) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Homepage",
                    tint = Color(0xFF28302B)
                )
            }
        },
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
fun BannerImage(){
    Image(
        painter = painterResource(R.drawable.imageinfo1),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize().height(300.dp)
    )
}

@Composable
fun PictureCardInfo(picture: PictureInfo, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .width(90.dp)
            .height(90.dp)
            .padding(start = 8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = picture.drawableResourseId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun PicturesList() {
    PictureListInfo(
        pictureList = Datasource().loadPicturesInfo(),
    )
}

@Composable
fun PictureListInfo(pictureList: Array<PictureInfo>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(pictureList.size) { index ->
            PictureCardInfo(
                picture = pictureList[index],
                onClick = {

                }
            )
        }
    }
}
