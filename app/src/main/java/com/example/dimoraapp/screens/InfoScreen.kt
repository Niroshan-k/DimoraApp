package com.example.dimoraapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.dimoraapp.R
import com.example.dimoraapp.data.Datasource
import com.example.dimoraapp.model.PictureInfo
import com.example.dimoraapp.navigation.BottomNavBar
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

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
                item { HouseDetails() }

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
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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
        modifier = Modifier
            .fillMaxSize()
            .height(300.dp)
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
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
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

@Composable
fun HouseDetails() {
    Column {
        Heading(stringResource(R.string.price1))
        Box(){
            Row(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Icon(Icons.Filled.LocationOn, contentDescription = "location")
                Text(
                    text = stringResource(R.string.Heading1),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            text = stringResource(R.string.property_details),
            modifier = Modifier
                .padding(top = 30.dp, start = 16.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        //first row
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "2",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "Bedroom",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "1",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "Bathroom",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.Place, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "45",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "sqft",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
            }
        }
        //second row
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.DateRange, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "2020",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "Year",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.ShoppingCart, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "1",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "Car Park",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(4.dp))
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                        ) {
                            Icon(Icons.Filled.Face, contentDescription = "bedroom")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "1",fontWeight = FontWeight.Medium)
                        }
                        Text(
                            text = "Pool",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = Color.Gray)
                    }
                }
            }
        }
        Text(
            text = stringResource(R.string.location),
            modifier = Modifier
                .padding(top = 30.dp, start = 16.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        PropertyMap(lat = 51.505, lng = -0.09)
        Text(
            text = "Description",
            modifier = Modifier
                .padding(top = 30.dp, start = 16.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PropertyMap(lat: Double, lng: Double) {
    val context = LocalContext.current

    // AndroidView for integrating native views in Jetpack Compose
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ){
        AndroidView(
            modifier = Modifier.padding(16.dp),
            factory = {
                MapView(context).apply {
                    // Set up the map
                    setMultiTouchControls(true)
                    controller.setZoom(15.0)
                    controller.setCenter(GeoPoint(lat, lng))

                    // Add a marker to the map
                    val marker = Marker(this).apply {
                        position = GeoPoint(lat, lng)
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        title = "Property Location"
                    }
                    overlays.add(marker)
                }
            },
            update = { mapView ->
                // Update map center dynamically if needed
                mapView.controller.setCenter(GeoPoint(lat, lng))
            }
        )
    }
}