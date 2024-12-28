package br.com.sanatiel.appfitness

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem(
    val id: Int,
    @DrawableRes val drawableId: Int,
    @StringRes var textStringId: Int,
    val color: Int
)
