package com.example.composeclickergame.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun StoreBottomSheet(
    parentScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable (CoroutineScope, ModalBottomSheetState) -> Unit = { scope, state ->
        PreviewContent(scope, state)
    },
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            LazyColumn {
                items(25) {
                    ListItem(
                        text = { Text("Item $it") },
                    )
                }
            }
        },
    ) {
        content(parentScope, bottomSheetState)
    }
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