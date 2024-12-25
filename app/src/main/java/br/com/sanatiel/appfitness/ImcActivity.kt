package br.com.sanatiel.appfitness

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {
    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        edtWeight = findViewById(R.id.edt_imc_weight)
        edtHeight = findViewById(R.id.edt_imc_height)
        btnCalc = findViewById(R.id.btn_imc_calc)

        btnCalc.setOnClickListener {
            if(!validateForm()){
                Toast.makeText(this, R.string.invalid_fields_form, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }
    }

    private fun validateForm(): Boolean{
       return (edtWeight.text.toString().isNotEmpty()
                && edtHeight.text.toString().isNotEmpty()
                && !edtWeight.text.toString().startsWith("0")
                && !edtHeight.text.toString().startsWith("0")
              )
    }
}