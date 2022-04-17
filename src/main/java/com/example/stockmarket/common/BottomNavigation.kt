package com.example.stockmarket.common

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.stockmarket.R
import androidx.compose.material.BottomNavigation
import com.example.stockmarket.ui.theme.CardBackgroundColor

@Composable
fun CustomBottomNavigation(
     homeSelected:Boolean,
     onHomeClick :() -> Unit,
     onConverterClick:() -> Unit
){
    BottomAppBar(
        cutoutShape = CutCornerShape(30),
        backgroundColor = CardBackgroundColor,
        content = {
            BottomNavigation(backgroundColor = CardBackgroundColor) {
                BottomNavigationItem(
                    selected = homeSelected,
                    onClick = {
                        onHomeClick.invoke()
                    },
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "home")
                    },
                    label = { Text(text = "Home") },
                    alwaysShowLabel = true
                )

                BottomNavigationItem(
                    selected = !homeSelected,
                    onClick = {
                        onConverterClick.invoke()
                    },
                    icon = {
                        Icon(imageVector = ImageVector.vectorResource(id =R.drawable.ic_currency_exchange),
                            contentDescription = "currency")
                    },
                    label = {
                        Text(text = "Currency Converter")
                    },
                    alwaysShowLabel = true
                )
            }
        }
    )

}