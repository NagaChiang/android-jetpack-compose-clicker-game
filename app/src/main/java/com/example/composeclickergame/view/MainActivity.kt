package com.example.composeclickergame.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun setupUi() {
        setContent {
            StoreBottomSheet { scope, bottomSheetState ->
                GameScreen(scope, bottomSheetState)
            }
        }
    }
}
