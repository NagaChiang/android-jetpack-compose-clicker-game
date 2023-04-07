package com.example.composeclickergame.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import com.example.composeclickergame.ui.theme.ComposeClickerGameTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                StoreBottomSheet { scope, bottomSheetState ->
                    GameScreen(scope, bottomSheetState)
                }
            }
        }
    }
}
