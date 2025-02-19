package br.com.sanatiel.appfitness

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.sanatiel.appfitness.model.Calc

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
            val weight = edtWeight.text.toString().toInt()
            val height = edtHeight.text.toString().toInt()
            val result = calculateIMC(weight, height)

            val imcResponseId = imcResponse(result)

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    //not implemented yet
                }
                .setNegativeButton(R.string.save){dialog,whitch->
                    Thread{
                        val app = (application as App)
                        app.db.calcDao().insert(Calc(type = "imc", reslt = result))
                        runOnUiThread {
                            startActivity(Intent(this@ImcActivity,
                                ListCalcActivity::class.java)
                                .putExtra("type", "imc")
                            )
                        }
                    }.start()
                }
                .create()
                .show()

            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    @StringRes
    private fun imcResponse(imc: Double):Int{
        return when{
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    private fun calculateIMC(weight:Int, height:Int):Double{
        return (weight*1.0)/((height*0.01)*(height*0.01))
    }

    private fun validateForm(): Boolean{
       return (edtWeight.text.toString().isNotEmpty()
                && edtHeight.text.toString().isNotEmpty()
                && !edtWeight.text.toString().startsWith("0")
                && !edtHeight.text.toString().startsWith("0")
              )
    }
}