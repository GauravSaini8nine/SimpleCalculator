package com.example.simplecalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import java.lang.NumberFormatException
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_PENDING_OPERATION="PendingOperation"
private const val STATE_OPERAND1="Operand1"
private const val STATE_OPERAND1_STORED="Operand1_Stored"
class MainActivity : AppCompatActivity() {
//    private lateinit var result: EditText
//    private lateinit var startCalculating: EditText
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.Operation) }


    //variable to hold the operands and type of calculations
    private var operand1: Double? = null

    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        result = findViewById(R.id.etResult)
//        startCalculating = findViewById(R.id.etStartCalculation)


//        val button0 = findViewById<Button>(R.id.button0)
//        val button1 = findViewById<Button>(R.id.btn1)
//        val button2 = findViewById<Button>(R.id.btn2)
//        val button3 = findViewById<Button>(R.id.btn3)
//        val button4 = findViewById<Button>(R.id.btn4)
//        val button5 = findViewById<Button>(R.id.btn5)
//        val button6 = findViewById<Button>(R.id.btn6)
//        val button7 = findViewById<Button>(R.id.btn7)
//        val button8 = findViewById<Button>(R.id.btn8)
//        val button9 = findViewById<Button>(R.id.btn9)
//        val buttonDot = findViewById<Button>(R.id.btnDot)
//
//
//        val multiply = findViewById<Button>(R.id.btnMultiply)
//        val divide = findViewById<Button>(R.id.btnDivide)
//        val subtract = findViewById<Button>(R.id.btnSubtract)
//        val addition = findViewById<Button>(R.id.btnAddition)
//        val equals = findViewById<Button>(R.id.btnEquals)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            etStartCalculation.append(b.text)

        }
        btn1.setOnClickListener(listener)
        button0.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
        btn5.setOnClickListener(listener)
        btn6.setOnClickListener(listener)
        btn7.setOnClickListener(listener)
        btn8.setOnClickListener(listener)
        btn9.setOnClickListener(listener)
        btnDot.setOnClickListener(listener)


        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            try {
                val value = etStartCalculation.text.toString().toDouble()

                performOperation(value, op)

            } catch (e: NumberFormatException) {
                etStartCalculation.setText("")
            }

            pendingOperation = op
            Operation.setText(pendingOperation)
        }

        btnEquals.setOnClickListener(opListener)
        btnMultiply.setOnClickListener(opListener)
        btnAddition.setOnClickListener(opListener)
        btnSubtract.setOnClickListener(opListener)
        btnDivide.setOnClickListener(opListener)

    }

    private fun performOperation(value: Double, Operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = Operation
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN  // handling exception of dividing by 0
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "+" -> operand1 = operand1!! + value
                "-" -> operand1 = operand1!! - value

            }
        }
        etResult.setText(operand1.toString())
        etStartCalculation.setText("")


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(operand1 != null){
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED,true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(STATE_OPERAND1)){
            operand1= savedInstanceState.getDouble(STATE_OPERAND1)
        }else{
            operand1=null
        }

        pendingOperation= savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
        Operation.text=pendingOperation

    }
}