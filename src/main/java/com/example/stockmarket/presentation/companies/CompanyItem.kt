package com.example.stockmarket.presentation.companies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.domain.model.CompanyListing
import com.example.stockmarket.ui.theme.CardBackgroundColor
import com.example.stockmarket.ui.theme.SubTitleColor

@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(3.dp).padding(5.dp).fillMaxWidth(),
        backgroundColor = CardBackgroundColor,
        shape = RoundedCornerShape(10.dp), elevation = 10.dp
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = company.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = company.exchange,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = company.symbol,
                    fontStyle = FontStyle.Italic,
                    color = SubTitleColor
                )
            }
        }
    }
}