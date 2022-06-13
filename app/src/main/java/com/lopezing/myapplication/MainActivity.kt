package com.lopezing.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import com.lopezing.myapplication.R.string
import com.lopezing.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : Activity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var bornDate=""
    private val calendar= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view=mainBinding.root
        setContentView(view)

        val dateSetListener =DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val format="dd/MM/yyyy"
            val sf= SimpleDateFormat(format)
            bornDate =sf.format(calendar.time).toString()
            mainBinding.buttonDB.text=bornDate
        }


        with(mainBinding){
            buttonDB.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        registerButton.setOnClickListener {
            val name = nameText.text.toString()
            val email = emailText.text.toString()
            val pasw = passText.text.toString()
            val cpasw = cpassText.text.toString()
            val genero =
            if (radioButton1.isChecked) getString(string.button_f)
            else getString(string.button_m)
            var hobby=""
            if(checkBox1.isChecked)hobby+=getString(string.check_1)+" "
            if(checkbox2.isChecked)hobby+=getString(string.check_2)+" "
            if(checkbox3.isChecked)hobby+=getString(string.check_3)+" "
            if(checkbox4.isChecked)hobby+=getString(string.check_4)

            val cityspinner=spinnerCity.selectedItem.toString()
            if(name=="" || email=="" || pasw=="" || cpasw=="" || buttonDB.text=="dd/mm/aa")
                textOut.text= getText(string.verf1)
            else if (pasw==cpasw)
                textOut.text = getString(string.inf,name+"\n",email+"\n",pasw+"\n",genero+"\n",hobby+"\n",cityspinner+"\n",bornDate)
                else textOut.text=getText(string.verf2)
        }
        }
    }
}