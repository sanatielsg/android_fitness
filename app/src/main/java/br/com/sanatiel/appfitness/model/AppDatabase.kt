package br.com.sanatiel.appfitness.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.sanatiel.appfitness.util.DateConverter

@Database(entities = [Calc::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun calcDao():ICalcDAO

    companion object {
        private var INSTANCE : AppDatabase? = null
        fun getDatabase(context:Context): AppDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "fitness_tracker"
                    ).build()
                }
                return INSTANCE as AppDatabase
            }else{
                return INSTANCE as AppDatabase
            }
        }
    }
}