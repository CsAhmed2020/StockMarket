package com.example.stockmarket.presentation.company_info


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockmarket.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun CompanyInfoScreen(
    symbol: String,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if(state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(15.dp)
        ) {
            state.company?.let { company ->
                Card(modifier = Modifier
                    .fillMaxWidth(),
                    backgroundColor = CardBackgroundColor,
                    shape = RoundedCornerShape(10.dp), elevation = 10.dp
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)) {
                        Text(
                            text = company.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,

                            )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = SymbolColor,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Symbol: ")
                                }
                                append(company.symbol)
                            },
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                            color = SubTitleColor
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = SymbolColor,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Industry: ")
                                }
                                append(company.industry)
                            },
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text =buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = SymbolColor,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Country: ")
                                }
                                append(company.country)
                            },
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = company.description,
                            fontSize = 14.sp,
                            color = descriptionColor
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text =buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = SymbolColor,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("PERatio: ")
                                    }
                                    append(company.peRatio)
                                },
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text =buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = SymbolColor,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("PEGRatio: ")
                                    }
                                    append(company.pegRatio)
                                },
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text =buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = SymbolColor,
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("EPS: ")
                                    }
                                    append(company.eps)
                                },
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                if(state.stockInfo.isNotEmpty()) {
                    Log.d("Ahmed",state.stockInfo.toString())
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Market Summary", color = SymbolColor)
                    Spacer(modifier = Modifier.height(32.dp))
                    StockChart(
                        infos = state.stockInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .align(CenterHorizontally)
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}