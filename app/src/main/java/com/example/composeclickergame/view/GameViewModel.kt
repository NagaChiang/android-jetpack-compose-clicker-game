package com.example.composeclickergame.view

import androidx.lifecycle.ViewModel
import com.example.composeclickergame.model.ItemDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val itemDataRepo: ItemDataRepository,
) : ViewModel() {
    val itemDatas get() = itemDataRepo.itemDatas

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    fun onIncrementButtonClicked() {
        _score.value++
    }
}