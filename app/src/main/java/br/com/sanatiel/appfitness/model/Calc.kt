package br.com.sanatiel.appfitness.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Calc(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val reslt: Double,
    @ColumnInfo(name = "created_date") val createdAt: Date = Date()
) {
}