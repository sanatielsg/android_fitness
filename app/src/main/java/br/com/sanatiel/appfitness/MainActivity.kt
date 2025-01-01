package br.com.sanatiel.appfitness

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(id = 1,
                drawableId = R.drawable.baseline_wb_sunny_24,
                textStringId =  R.string.label_imc ,
                color = Color.GREEN)
        )
        mainItems.add(
            MainItem(id = 2,
                drawableId = R.drawable.baseline_wb_sunny_24,
                textStringId =  R.string.tmb ,
                color = Color.GREEN)
        )
        for (i in 1..2){
            mainItems.add(mainItems[0])
        }

        val adapter = MainAdapter(mainItems)
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = GridLayoutManager(this,3)
    }

    private inner class MainAdapter(private val mainItens: List<MainItem>): RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val currentItem = mainItens[position]
            holder.bind(currentItem)
        }

        override fun getItemCount(): Int {
            return mainItens.size
        }
    }

    private class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(currentItem: MainItem) {
            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name : TextView = itemView.findViewById(R.id.item_txt_name)
            val container: LinearLayout = itemView.findViewById(R.id.item_container_imc)

            img.setImageResource(currentItem.drawableId)
            name.setText(currentItem.textStringId)
            container.setBackgroundColor(currentItem.color)
        }
    }
}