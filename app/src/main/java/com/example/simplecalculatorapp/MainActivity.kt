@file:Suppress("DEPRECATION")

package com.example.simplecalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import androidx.lifecycle.ViewModelProviders.*


import kotlinx.android.synthetic.main.activity_main.*

//private const val STATE_PENDING_OPERATION="PendingOperation"
//private const val STATE_OPERAND1="Operand1"
//private const val STATE_OPERAND1_STORED="Operand1_Stored"
class MainActivity : AppCompatActivity() {
//    private lateinit var result: EditText
//    private lateinit var startCalculating: EditText
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.Operation) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel= of(this).get(CalculatorViewModel::class.java)
        viewModel.result.observe(this, { stringResult-> result.setText(stringResult)})
        viewModel.newNumber.observe(this, { sringNumber-> NewNumber.setText(sringNumber)})
        viewModel.operation.observe(this, { sringOperation-> NewNumber.setText(sringOperation)})
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
            viewModel.digitPressed((v as Button).text.toString())

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
            viewModel.operandPressed((v as Button).text.toString())

        }

        btnEquals.setOnClickListener(opListener)
        btnMultiply.setOnClickListener(opListener)
        btnAddition.setOnClickListener(opListener)
        btnSubtract.setOnClickListener(opListener)
        btnDivide.setOnClickListener(opListener)

    }



//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        if(operand1 != null){
//            outState.putDouble(STATE_OPERAND1, operand1!!)
//            outState.putBoolean(STATE_OPERAND1_STORED,true)
//        }
//        outState.putString(STATE_PENDING_OPERATION, pendingOperation!!)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        if (savedInstanceState.getBoolean(STATE_OPERAND1)){
//            operand1= savedInstanceState.getDouble(STATE_OPERAND1)
//        }else{
//            operand1=null
//        }
//
//        pendingOperation= savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
//        Operation.text=pendingOperation
//
//    }
}