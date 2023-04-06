package com.example.composeclickergame.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeclickergame.ui.theme.ComposeClickerGameTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
) {
    val count by gameViewModel.count.collectAsState()

    ComposeClickerGameTheme(
        darkTheme = true
    ) {
        Scaffold { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CountText(count)
                        Text(
                            text = "123/s",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            FilledIconButton(
                                modifier = Modifier.size(80.dp),
                                onClick = { gameViewModel.onIncrementButtonClicked() },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    modifier = Modifier.size(56.dp),
                                )
                            }
                        }
                        FilledTonalButton(
                            modifier = Modifier.padding(16.dp),
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Store",
                            )
                            Box(modifier = Modifier.size(8.dp))
                            Text(text = "Store")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountText(count: Int) {
    Text(
        text = count.toString(),
        style = MaterialTheme.typography.displayMedium,
    )
}