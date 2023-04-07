package com.example.composeclickergame.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemDataRepository @Inject constructor() {
    val itemDatas = listOf(
        ItemData("Grandma", 1, 10),
        ItemData("Farm", 10, 100),
        ItemData("Mine", 100, 1000),
        ItemData("Factory", 1000, 10000),
    )
}