package com.example.dimoraapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dimoraapp.R
import com.example.dimoraapp.navigation.BottomNavBar
import com.example.dimoraapp.ui.theme.DMserif

@Composable
fun SearchScreen (navController: NavController) {
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
                item { SearchBar(valueChange = { query ->
                    // Handle search action
                    println("Searching for: $query")
                })}
                item { Recommandations(
                    onClick = {navController.navigate("infoscreen")}
                ) }

            }

            // Bottom navigation bar
            BottomNavBar(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SearchBar(valueChange: (String) -> Unit) {
    var query by remember { mutableStateOf("") } // Use `var` with `remember` for state
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row {
            //drop down
            var expanded by remember { mutableStateOf(false) } // State to control dropdown visibility
            var selectedOption by remember { mutableStateOf("") } // State to hold selected value
            val options = listOf("Kandy", "Anuradhapura", "Matale") // Dropdown options

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded } // Toggle the dropdown
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true, // Prevent manual text input
                    label = { Text("District") }, // Label for the text field
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown, // Dropdown icon
                            contentDescription = "Dropdown icon"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .menuAnchor(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Background color
                        focusedIndicatorColor = Color.Transparent, // Remove underline
                        unfocusedIndicatorColor = Color.Transparent // Remove underline
                    )
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false } // Close the dropdown when clicked outside
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option // Update selected value
                                expanded = false // Close the dropdown
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //drop down 2
            var expanded2 by remember { mutableStateOf(false) } // State to control dropdown visibility
            var selectedOption2 by remember { mutableStateOf("") } // State to hold selected value
            val options2 = listOf("Kandy", "Anuradhapura", "Matale") // Dropdown options

            ExposedDropdownMenuBox(
                expanded = expanded2,
                onExpandedChange = { expanded2 = !expanded2 } // Toggle the dropdown
            ) {
                TextField(
                    value = selectedOption2,
                    onValueChange = {},
                    readOnly = true, // Prevent manual text input
                    label = { Text("City") }, // Label for the text field
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown, // Dropdown icon
                            contentDescription = "Dropdown icon"
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .menuAnchor(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant, // Background color
                        focusedIndicatorColor = Color.Transparent, // Remove underline
                        unfocusedIndicatorColor = Color.Transparent // Remove underline
                    )
                )

                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false } // Close the dropdown when clicked outside
                ) {
                    options.forEach { option2 ->
                        DropdownMenuItem(
                            text = { Text(option2) },
                            onClick = {
                                selectedOption2 = option2 // Update selected value
                                expanded2 = false // Close the dropdown
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            TextField(
                value = query, // Correct type (String)
                onValueChange = { newValue ->
                    query = newValue // Update query state
                    valueChange(newValue) // Trigger search action with the query
                },
                placeholder = {
                    Text(text = "Search for a property") // Placeholder text
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, // Search icon
                        contentDescription = "Search icon"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp), // Rounded corners
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant, // Background color
                    focusedIndicatorColor = Color.Transparent, // Remove underline
                    unfocusedIndicatorColor = Color.Transparent // Remove underline
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                contentAlignment = Alignment.CenterEnd
            ){
                Button(
                    modifier = Modifier
                        .height(60.dp),
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF523D35),
                        contentColor = Color.White
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                }
            }
        }
    }

}

@Composable
fun Recommandations (onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(32.dp))
    Heading("Recommendations")
    Spacer(modifier = Modifier.height(16.dp))
    Card(
        modifier = Modifier .fillMaxWidth() .height(200.dp) .padding(16.dp) .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(R.drawable.image2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ){
                Text(
                    text = stringResource(R.string.Heading1),
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMserif
                )
                Text(
                    text = stringResource(R.string.price1),
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = DMserif
                )
            }
        }
    }

}
