package com.one4ll.calculatorinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    private val displayOperation by lazy { findViewById<TextView>(R.id.operation) }


    private var operand1 :Double? = null
    private var operand2: Double = 0.0
    private var pendingOperation = "="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.new_number)
        val button0 : Button = findViewById(R.id.button0)
        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)
        val button6 : Button = findViewById(R.id.button6)
        val button7 : Button = findViewById(R.id.button7)
        val button8 : Button = findViewById(R.id.button8)
        val button9 : Button = findViewById(R.id.button9)
        val buttonDot : Button = findViewById(R.id.button_dot)


        val buttonMultiply : Button = findViewById(R.id.button_multiply)
        val buttonPlus : Button = findViewById(R.id.button_plus)
        val buttonMinus : Button = findViewById(R.id.button_minus)
        val buttonDivide : Button = findViewById(R.id.button_divide)
        val buttonEqual : Button = findViewById(R.id.button_equals)

        val digitListener  = View.OnClickListener {
            val button = it as Button
            newNumber.append(button.text)

        }
        val operationListener = View.OnClickListener {
            val button = it as Button
            val operation  = button.text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                value.performOperation(operation)

            }catch (e : Exception){
                newNumber.setText("")

            }
            pendingOperation = operation
            displayOperation.text = pendingOperation
        }
        button0.setOnClickListener(digitListener)
        button1.setOnClickListener(digitListener)
        button2.setOnClickListener(digitListener)
        button3.setOnClickListener(digitListener)
        button4.setOnClickListener(digitListener)
        button5.setOnClickListener(digitListener)
        button6.setOnClickListener(digitListener)
        button7.setOnClickListener(digitListener)
        button8.setOnClickListener(digitListener)
        button9.setOnClickListener(digitListener)
        buttonDot.setOnClickListener(digitListener)

        // operation
        buttonMultiply.setOnClickListener(operationListener)
        buttonDivide.setOnClickListener(operationListener)
        buttonMinus.setOnClickListener(operationListener)
        buttonPlus.setOnClickListener(operationListener)
        buttonEqual.setOnClickListener(operationListener)

    }
    private fun Double.performOperation(operation: String){
        if (operand1 == null){
            operand1 = this
        }else{
            operand2 = this
            if (pendingOperation == "="){
                pendingOperation = operation
            }
            when (operation){
                "=" ->{
                    operand1 = operand2
                }
                "*" ->{
                    operand1 = operand1!! * this

                }
                "/" ->{
                    operand1 = if (operand2 == 0.0){
                        Double.NaN
                    }else{
                        operand1?.div(this)
                    }
                }
                "+" ->{
                    operand1 = operand1?.plus(this)
                }
                "-" ->{
                    operand1 = operand1?.minus(this)

                }

            }


        }
        newNumber.setText("")
        result.setText(operand1.toString())


    }

}
