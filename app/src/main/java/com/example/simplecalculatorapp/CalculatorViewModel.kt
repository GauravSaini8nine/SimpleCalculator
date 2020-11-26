package com.example.simplecalculatorapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.NumberFormatException

class CalculatorViewModel : ViewModel(){

    //variable to hold the operands and type of calculations
    private var operand1: Double? = null

    private var pendingOperation = "="

    val result = MutableLiveData<String>()
    val newNumber = MutableLiveData<String>()
    val operation = MutableLiveData<String>()


    fun digitPressed(caption :String){
        if (newNumber.value!= null){
            newNumber.value= newNumber.value + caption
        }else{
            newNumber.value=caption
        }

    }

    fun operandPressed(op: String){
        try {
            val value = newNumber.value?.toDouble()
            if (value != null){
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value=""
        }

        pendingOperation = op
        operation.value= (pendingOperation)
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
        result.value= operand1.toString()
        newNumber.value= ""


    }
}