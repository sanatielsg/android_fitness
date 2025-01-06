package br.com.sanatiel.appfitness.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ICalcDAO {
    @Insert
    fun insert(calc: Calc)

    @Query("select * from Calc where type = :type")
    fun getRegisterByType(type:String): List<Calc>
}