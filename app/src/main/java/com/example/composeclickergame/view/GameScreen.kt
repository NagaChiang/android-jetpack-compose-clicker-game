package com.example.composeclickergame.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GameScreen(
    parentScope: CoroutineScope = rememberCoroutineScope(),
    storeBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    gameViewModel: GameViewModel = hiltViewModel(),
) {
    val score by gameViewModel.score.collectAsState()

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
                    ScoreText(score)
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
                    StoreButton(
                        onClick = { parentScope.launch { storeBottomSheetState.show() } },
                    )
                }
            }
        }
    }
}

@Composable
private fun ScoreText(score: Int) {
    Text(
        text = score.toString(),
        style = MaterialTheme.typography.displayMedium,
    )
}

@Composable
private fun StoreButton(onClick: () -> Unit) {
    FilledTonalButton(
        modifier = Modifier.padding(16.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Store",
        )
        Box(modifier = Modifier.size(8.dp))
        Text(text = "Store")
    }
}