package com.example.composeclickergame.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeclickergame.model.ItemData
import com.example.composeclickergame.ui.theme.ComposeClickerGameTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun StoreBottomSheet(
    parentScope: CoroutineScope = rememberCoroutineScope(),
    gameViewModel: GameViewModel = hiltViewModel(),
    content: @Composable (CoroutineScope, ModalBottomSheetState) -> Unit = { scope, state ->
        PreviewContent(scope, state)
    },
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val itemDatas = gameViewModel.itemDatas

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.primaryVariant,
        sheetContent = {
            LazyColumn(
                Modifier.padding(top = 16.dp),
                content = {
                    items(
                        items = itemDatas,
                        key = { it.name },
                        itemContent = { itemData ->
                            StoreItem(itemData)
                        }
                    )
                },
            )
        },
    ) {
        content(parentScope, bottomSheetState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun StoreItem(itemData: ItemData) {
    ListItem(
        text = { Text(text = itemData.name) },
        secondaryText = { Text(text = "${itemData.rate}/s") },
        trailing = {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                ElevatedButton(
                    modifier = Modifier.width(100.dp),
                    onClick = {},
                ) {
                    Text(text = "$${itemData.price}")
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun PreviewContent(
    scope: CoroutineScope,
    storeBottomSheetState: ModalBottomSheetState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledIconButton(
            onClick = {
                if (storeBottomSheetState.isVisible) {
                    scope.launch {
                        storeBottomSheetState.hide()
                    }
                } else {
                    scope.launch {
                        storeBottomSheetState.show()
                    }
                }
            },
        ) {
            Icon(
                Icons.Default.KeyboardArrowUp,
                contentDescription = "Open bottom sheet"
            )
        }
    }
}