package br.com.sanatiel.appfitness

import android.app.Application
import br.com.sanatiel.appfitness.model.AppDatabase

class App: Application(){

    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }

}