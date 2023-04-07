package com.example.composeclickergame.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.composeclickergame.ui.theme.ComposeClickerGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun setupUi() {
        setContent {
            ComposeClickerGameTheme(
                darkTheme = true,
            ) {
                val score by gameViewModel.score.collectAsState()
                val rate by gameViewModel.rate.collectAsState(0)
                val itemCountMap by gameViewModel.itemCountMap.collectAsState()

                StoreBottomSheet(
                    itemDatas = gameViewModel.itemDatas,
                    itemCountMap = itemCountMap,
                    onStoreItemPurchased = gameViewModel::onStoreItemPurchased,
                ) { scope, bottomSheetState ->
                    GameScreen(
                        parentScope = scope,
                        storeBottomSheetState = bottomSheetState,
                        score = score,
                        rate = rate,
                        onIncrementButtonClicked = gameViewModel::onIncrementButtonClicked,
                    )
                }
            }
        }
    }
}
