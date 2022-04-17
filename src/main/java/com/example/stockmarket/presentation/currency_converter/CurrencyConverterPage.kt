package com.example.stockmarket.presentation.currency_converter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.R
import com.example.stockmarket.common.CustomBottomNavigation
import com.example.stockmarket.common.util.noRippleClickable
import com.example.stockmarket.di.AppModule.viewModel
import com.example.stockmarket.domain.model.currency_converter.Currency
import com.example.stockmarket.presentation.currency_converter.components.CurrencyConverterEvent
import com.example.stockmarket.presentation.currency_converter.components.ConverterScreenState
import com.example.stockmarket.presentation.destinations.CurrencySelectorScreenDestination
import com.example.stockmarket.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@ExperimentalAnimationApi
@Composable
fun CurrencyConverterScreen(
    navigator: DestinationsNavigator
) {

    lateinit var logic : CurrencyConverterLogic
    val snackbarHostState = SnackbarHostState()
    val state : ConverterScreenState = viewModel
    logic = viewModel

    val baseCurrency: State<Currency> = state.baseCurrency.collectAsState()
    val baseCurrencyDisplay = state.baseCurrencyDisplay.collectAsState()

    val targetCurrency: State<Currency> = state.targetCurrency.collectAsState()
    val targetCurrencyDisplay = state.targetCurrencyDisplay.collectAsState()

    val error = state.error.collectAsState()
    val isLoading = state.isLoading.collectAsState()

    val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

    val focusManager = LocalFocusManager.current

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            CustomBottomNavigation(homeSelected = false, onHomeClick = {
                navigator.popBackStack()
            }) {}
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .noRippleClickable {
                    focusManager.clearFocus()
                }
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            CurrencyCard(
                currency = baseCurrency.value,
                amount = baseCurrencyDisplay.value,
                currencyClickHandler = {
                    logic.onEvent(CurrencyConverterEvent.BaseCurrencyChangeRequested)
                    navigator.navigate(
                        CurrencySelectorScreenDestination
                    )

                },
                textChangeHandler = {
                    logic.onEvent(
                        CurrencyConverterEvent.BaseCurrencyDisplayTextChanged(it)
                    )
                },
                titleId = R.string.convert_from
            )

            Spacer(modifier = Modifier.height(8.dp))

            MiddleSection(
                onSwitchCurrenciesPressed = {
                    logic.onEvent(CurrencyConverterEvent.SwitchCurrenciesPressed)
                },
                onEvaluatePressed = {
                    logic.onEvent(CurrencyConverterEvent.EvaluatePressed)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            CurrencyCard(
                currency = targetCurrency.value,
                amount = targetCurrencyDisplay.value,
                currencyClickHandler = {
                    logic.onEvent(CurrencyConverterEvent.TargetCurrencyChangeRequested)
                    navigator.navigate(
                        CurrencySelectorScreenDestination
                    )
                },
                textChangeHandler = {},
                readonly = true,
                titleId = R.string.convert_to
            )
            if (error.value != null) {
                Spacer(modifier = Modifier.padding(32.dp))
                Text(
                    text = error.value.toString(),
                    color = Color.Red,
                    style = MaterialTheme.typography.body2
                )
            }
        }

        AnimatedVisibility(visible = isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black.copy(alpha = .2f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
            }
        }
    }
}

@Composable
fun MiddleSection(
    onSwitchCurrenciesPressed: () -> Unit,
    onEvaluatePressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        IconButton(modifier = Modifier.background(color = CardBackgroundColor, shape = CircleShape),
            onClick = { onSwitchCurrenciesPressed() }) {
            Icon(imageVector = ImageVector.vectorResource(id =R.drawable.ic_baseline_published_with_changes_24),
                contentDescription = "Exchange", tint = TextWhite)
        }
        Spacer(modifier = Modifier
            .width(150.dp)
            .height(1.dp)
            .background(color = TextWhite)
            .padding(horizontal = 5.dp))

        IconButton(modifier = Modifier.background(color = CardBackgroundColor, shape = CircleShape),
            onClick = {
            focusManager.clearFocus()
            onEvaluatePressed()
        }) {
            Icon(imageVector = ImageVector.vectorResource(id =R.drawable.ic_currency_exchange), contentDescription = "Convert",
                tint = TextWhite)
        }
    }
}


@Composable
fun CurrencyCard(
    currency: Currency,
    amount: String,
    currencyClickHandler: () -> Unit,
    textChangeHandler: (String) -> Unit,
    modifier: Modifier = Modifier,
    readonly: Boolean = false,
    titleId:Int
) {
    val focusManager = LocalFocusManager.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        backgroundColor = CardBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
        ) {
            Text(text = stringResource(titleId), color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(10.dp), style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                ))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(color = BoxBackgroundColor)
                            .clickable {
                                currencyClickHandler()
                            }) {
                        Text(
                            text = currency.code.uppercase(),
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 16.sp
                        )
                        Icon(
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "arrow",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    OutlinedTextField(
                        value = amount,
                        onValueChange = {
                            textChangeHandler(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 60.dp),
                        trailingIcon = {
                            Text(
                                text = java.util.Currency.getInstance(currency.code).symbol,
                                color = BoxBackgroundColor,
                                fontSize = 16.sp
                            )
                        },
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        readOnly = readonly,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        placeholder = {
                            Text(
                                text = "0.00",
                                color = SubTitleColor,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = TextWhite,
                            unfocusedBorderColor = CardBackgroundColor,
                            backgroundColor = CardBackgroundColor,
                            textColor = MaterialTheme.colors.onBackground
                        )
                    )
                }
            Text(text = currency.name,
                color = MaterialTheme.colors.onBackground
            )
            }
        }
    }