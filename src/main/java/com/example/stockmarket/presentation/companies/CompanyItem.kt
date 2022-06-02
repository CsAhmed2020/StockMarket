package com.example.stockmarket.presentation.companies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.domain.model.CompanyListing
import com.example.stockmarket.ui.theme.CardBackgroundColor
import com.example.stockmarket.ui.theme.SubTitleColor
import com.example.stockmarket.ui.theme.SymbolColor
import com.example.stockmarket.ui.theme.TextColor



@Composable
fun CompanyItem(
    company: CompanyListing,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(0.dp)
            .padding(5.dp)
            .fillMaxWidth(),
        backgroundColor = CardBackgroundColor,
        shape = RoundedCornerShape(10.dp), elevation = 10.dp
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = CenterVertically
        ) {
           Row(modifier = Modifier.fillMaxWidth(0.25f),
               verticalAlignment = CenterVertically,
               horizontalArrangement = Arrangement.Center
           ){
                   Text(
                       text = company.symbol,
                       color = SymbolColor,
                       fontSize = 25.sp,
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis,
                       modifier = Modifier.padding(end = 2.dp)
                   )
           }
            Column(modifier = Modifier
                .fillMaxWidth().padding(start = 5.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = company.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = company.exchange,
                    fontWeight = FontWeight.Light,
                    color = SubTitleColor
                )
            }
        }
    }
}

@Preview
@Composable
fun MyViewPreview() {
    CompanyItem(
        CompanyListing(
            name = "Ahmed",
            symbol = "A",
            exchange = "AA"
        )
    )
}


/**
 *  Column(
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
 */