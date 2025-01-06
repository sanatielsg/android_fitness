package br.com.sanatiel.appfitness.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ICalcDAO {
    @Insert
    fun insert(calc: Calc)
}