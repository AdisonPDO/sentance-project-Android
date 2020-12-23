package com.sentance

import Player
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListPlayers(var listPlyers : MutableList<Player>) : Parcelable {
}