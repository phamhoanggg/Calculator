package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        val mainInputText = findViewById<TextView>(R.id.mainInputText);

        mainInputText.text = "0"

        button0.setOnClickListener{
            mainInputText.text = addToInputText("0", mainInputText)
        }

        button1.setOnClickListener{
            mainInputText.text = addToInputText("1", mainInputText)
        }

        button2.setOnClickListener{
            mainInputText.text = addToInputText("2", mainInputText)
        }

        button3.setOnClickListener{
            mainInputText.text = addToInputText("3", mainInputText)
        }

        button4.setOnClickListener{
            mainInputText.text = addToInputText("4", mainInputText)
        }

        button5.setOnClickListener{
            mainInputText.text = addToInputText("5", mainInputText)
        }

        button6.setOnClickListener{
            mainInputText.text = addToInputText("6", mainInputText)
        }

        button7.setOnClickListener{
            mainInputText.text = addToInputText("7", mainInputText)
        }

        button8.setOnClickListener{
            mainInputText.text = addToInputText("8", mainInputText)
        }

        button9.setOnClickListener{
            mainInputText.text = addToInputText("9", mainInputText)
        }

        val button_add = findViewById<Button>(R.id.button_add)
        val button_sub = findViewById<Button>(R.id.button_sub)
        val button_mul = findViewById<Button>(R.id.button_multi)
        val button_div = findViewById<Button>(R.id.button_divide)
        val button_eql = findViewById<Button>(R.id.button_equal)

        button_add.setOnClickListener{
            mainInputText.text = addToInputText("+", mainInputText)
        }

        button_sub.setOnClickListener{
            mainInputText.text = addToInputText("-", mainInputText)
        }

        button_mul.setOnClickListener{
            mainInputText.text = addToInputText("x", mainInputText)
        }

        button_div.setOnClickListener{
            mainInputText.text = addToInputText("/", mainInputText)
        }

        button_eql.setOnClickListener {
            showResult()
        }

        val button_CE = findViewById<Button>(R.id.button_CE)
        val button_C = findViewById<Button>(R.id.button_C)
        val button_BS = findViewById<Button>(R.id.button_BS)

        button_CE.setOnClickListener {
            mainInputText.text = handleClearEntry(inputValue = mainInputText.text.toString())
        }

        button_C.setOnClickListener {
            mainInputText.text = "0"
            findViewById<TextView>(R.id.resultText).text = ""
        }

        button_BS.setOnClickListener {
            mainInputText.text = removeLast(mainInputText.text.toString())
        }
    }

    private fun addToInputText(buttonValue: String, mainInputText: TextView): String {

        if( mainInputText.text.toString().last() == '+' ||
            mainInputText.text.toString().last() == '-' ||
            mainInputText.text.toString().last() == '×' ||
            mainInputText.text.toString().last() == '÷' ){
            if(buttonValue == "+" || buttonValue == "-" || buttonValue == "x" || buttonValue == "/"){
                return mainInputText.text.toString()
            }
        }
        if(mainInputText.text.toString() == "0") mainInputText.text = ""
        return "${mainInputText.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = findViewById<TextView>(R.id.mainInputText).text.replace(Regex("x"), "*")

        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                findViewById<TextView>(R.id.resultText).text = "Error"
                findViewById<TextView>(R.id.resultText).setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
                findViewById<TextView>(R.id.resultText).text = DecimalFormat("0.######").format(result).toString()
            }
        } catch (e: Exception) {
            // Show Error Message
            findViewById<TextView>(R.id.resultText).text = "Error"
            findViewById<TextView>(R.id.resultText).setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    private fun removeLast(inputValue: String): String{
        if(inputValue.length <=1) return "0"
        return inputValue.substring(0, inputValue.length - 1)
    }
    private fun handleClearEntry(inputValue: String): String{
        val operators = "+-x/"
        val lastOperatorIndex = inputValue.lastIndexOfAny(operators.toCharArray())

        return if (lastOperatorIndex != -1) {
            inputValue.substring(0,lastOperatorIndex + 1)
        } else {
            "0"
        }
    }
}