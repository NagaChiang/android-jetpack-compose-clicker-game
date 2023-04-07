package com.example.composeclickergame.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeclickergame.model.ItemData
import com.example.composeclickergame.model.ItemDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val itemDataRepo: ItemDataRepository,
) : ViewModel() {
    val itemDatas get() = itemDataRepo.itemDatas

    private val _itemCountMap = MutableStateFlow<Map<String, Int>>(emptyMap())
    val itemCountMap get() = _itemCountMap.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    val rate = itemCountMap.map { map ->
        itemDatas.sumOf { itemData ->
            itemData.rate * (map[itemData.name] ?: 0)
        }
    }

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                _score.value += rate.first()
            }
        }
    }

    fun onIncrementButtonClicked() {
        _score.value += 1
    }

    fun onStoreItemPurchased(itemData: ItemData) {
        if (_score.value < itemData.price) {
            return
        }

        _score.value -= itemData.price

        val countMap = _itemCountMap.value
        val itemCount = countMap[itemData.name] ?: 0
        _itemCountMap.value = countMap + (itemData.name to itemCount + 1)
    }
}