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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dimoraapp.R
import com.example.dimoraapp.data.Datasource
import com.example.dimoraapp.model.Picture
import com.example.dimoraapp.navigation.BottomNavBar
import com.example.dimoraapp.ui.theme.DMserif

@Composable
fun MoreHouseScreen(navController: NavController) {
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

                item { Heading("Houses") }
                item { Cardlist(navController) }

            }

            // Bottom navigation bar
            BottomNavBar(navController = navController)
        }
    }
}


@Composable
fun HouseCard(onClick: () -> Unit, picture:Painter, name:String, price:String) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 16.dp,8.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clickable{onClick()}
            .shadow(3.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = picture,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(100.dp).height(100.dp).clip(shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = price, fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}

@Composable
fun Cardlist(navController: NavController) {
    HouseCard (
        onClick = {navController.navigate("infoscreen")},
        painterResource(R.drawable.image1),
        stringResource(R.string.Heading1),
        stringResource(R.string.price1)
    )
    HouseCard (
        onClick = {navController.navigate("infoscreen")},
        painterResource(R.drawable.image2),
        stringResource(R.string.Heading2),
        stringResource(R.string.price2)
    )
    HouseCard (
        onClick = {navController.navigate("infoscreen")},
        painterResource(R.drawable.image3),
        stringResource(R.string.Heading3),
        stringResource(R.string.price3)
    )
    HouseCard (
        onClick = {navController.navigate("infoscreen")},
        painterResource(R.drawable.image4),
        stringResource(R.string.Heading4),
        stringResource(R.string.price4)
    )
}