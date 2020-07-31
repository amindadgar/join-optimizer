package com.aminDadgar.db_implementation_proj.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aminDadgar.db_implementation_proj.R
import com.aminDadgar.db_implementation_proj.Utils.Cost
import com.aminDadgar.db_implementation_proj.Utils.ResultRecyclerAdapter
import com.aminDadgar.db_implementation_proj.model.CoupleData
import com.aminDadgar.db_implementation_proj.model.Quadruple
import com.aminDadgar.db_implementation_proj.model.TripleData
import com.aminDadgar.db_implementation_proj.model.datamodel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    var TABLE_COUNT = -1
    var TableNames : MutableList<Char> = mutableListOf<Char>()
    var index = 0


    private var Attr11 = 'a'
    private var FirstAttribute = -1
    private var SecondAttribute = -1
    private var Attr12 = 'a'
    private var TupleCount = -1
    private var TableName:Char = 'a'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        alertDialog()


        var cost = Cost()

        val Data :MutableList<datamodel> = mutableListOf()

//        Data.add(datamodel('R','a',100,'b',50,1000))   /* for debug purposes */
//        Data.add(datamodel('S','b',200,'c',500,2000))
//        Data.add(datamodel('T','c',50,'d',20,2000))
//        Data.add(datamodel('U','d',500,'e',100,2000))
//        Data.add(datamodel('Z','a',50,'e',200,1000))



        reset_button.setOnClickListener {
            table_name.text = "Enter a char for Table name and for attributes"
            table_name_text.setText("")
            attr11.setText("")
            attr1.setText("")
            attr2.setText("")
            attr21.setText("")
            attr3.setText("")
            cost = Cost()

            TableNames.removeAll(TableNames)

            result_button.visibility = View.GONE
            alertDialog()
            index = 0


            val layout = layoutInflater.inflate(R.layout.custom_toast,findViewById<ViewGroup>(R.id.toast_layout_root))
            val textview = layout.findViewById<TextView>(R.id.toast_text)
            textview.text = "Everything was reset!"
            val toast = Toast(this)
            toast.view = layout
            toast.duration = Toast.LENGTH_LONG
            toast.show()
        }

        submit_button.setOnClickListener {


            val animation = AlphaAnimation(1.0f,0.0f)  //for animating TextView
            animation.repeatMode = Animation.REVERSE
            animation.repeatCount = 1
            animation.duration = 200



            var inputError = false
            try {

                TableName = table_name_text.text.toString()[0]
                Attr11 = attr11.text.toString()[0]
                FirstAttribute = attr1.text.toString().toInt()
                SecondAttribute = attr2.text.toString().toInt()
                Attr12 = attr21.text.toString()[0]
                TupleCount = attr3.text.toString().toInt()
                if (Attr11.isDigit() || Attr12.isDigit()) throw IllegalArgumentException("Data is not Character")

            }catch (ex:ArrayIndexOutOfBoundsException){
                Toast.makeText(
                    this, "Table Limit Reached !\nNo More Table Available"
                    , Toast.LENGTH_LONG * 2
                ).show()
            }
            catch (ex:Exception){
                inputError = true
                Toast.makeText(this,"Error : Wrong Data input\nTry entering Numbers" +
                        "\nError Code:${ex.printStackTrace()}",Toast.LENGTH_LONG).show()
            }
            if (!inputError) {

                if (index < TABLE_COUNT) {
                    Log.d("INDEX",index.toString())

                    index++
                    table_name.startAnimation(animation)
                    Data.add(
                        datamodel(
                            TableName,
                            Attr11,
                            FirstAttribute,
                            Attr12,
                            SecondAttribute,
                            TupleCount
                        )
                    )
                    TableNames.add(TableName)

                    table_name_text.setText("")
                    attr11.setText("")
                    attr1.setText("")
                    attr2.setText("")
                    attr21.setText("")
                    attr3.setText("")
                }
            }

                animation.setAnimationListener(object :Animation.AnimationListener {
                    var bestThreeJoin:TripleData? =null
                    var bestFourJoin:Quadruple?=null
                    var bestTwoJoin:CoupleData?=null
                    override fun onAnimationRepeat(animation: Animation?) {
                        if (index < TABLE_COUNT ) {
                            val stringBuilder = StringBuilder()
                            for (name in TableNames) stringBuilder.append("$name ")
                            table_name.text = "$stringBuilder  :tables are added \nPlease enter the next table data "
                            Log.d("tablename",TableName.toString())
                        }
                        else {
                            val stringBuilder = StringBuilder()
                            for (name in TableNames) stringBuilder.append("$name ")
                            table_name.text = "$stringBuilder tables are added\nResults can be seen via result button"
                            submit_button.text = "Calculate"
                            if (TABLE_COUNT <=2){
                                bestTwoJoin = cost.twoBytwo(Data,TABLE_COUNT)
                            }else if (TABLE_COUNT <= 3){

                                bestTwoJoin = cost.twoBytwo(Data,TABLE_COUNT)
                                bestThreeJoin = cost.threeBythree(Data,bestTwoJoin!!,TABLE_COUNT)

                            }else if (TABLE_COUNT <= 4){

                                bestTwoJoin = cost.twoBytwo(Data,TABLE_COUNT)
                                bestThreeJoin = cost.threeBythree(Data,bestTwoJoin!!,TABLE_COUNT)
                                bestFourJoin = cost.fourJoin(Data,bestThreeJoin!!,TABLE_COUNT)

                            }else if (TABLE_COUNT <=5) {

                                bestTwoJoin = cost.twoBytwo(Data,TABLE_COUNT)
                                bestThreeJoin = cost.threeBythree(Data,bestTwoJoin!!,TABLE_COUNT)
                                bestFourJoin = cost.fourJoin(Data,bestThreeJoin!!,TABLE_COUNT)
                                cost.FiveJoin(Data, bestFourJoin!!,TABLE_COUNT)

                            }
                            result_button.visibility = View.VISIBLE

                        }
                    }

                    override fun onAnimationEnd(animation: Animation?) {}
                    override fun onAnimationStart(animation: Animation?) {}
                })

        }

        result_button.setOnClickListener {
            resultAlert(cost)
        }

    }



    fun alertDialog(){
        val alertDialog = AlertDialog.Builder(this).create()
        val alertDialogLayout = LayoutInflater.from(this).inflate(R.layout.table_count_alert,null)
        alertDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation

        alertDialog.setView(alertDialogLayout)
        alertDialog.setCancelable(false)
        val alertDialogEditText = alertDialogLayout.findViewById<EditText>(R.id.table_count_edit_text)
        val alertDialogButton = alertDialogLayout.findViewById<Button>(R.id.table_count_Submit)

        alertDialog.show()

        alertDialogButton.setOnClickListener {
            var error = false
            try {
                TABLE_COUNT = alertDialogEditText.text.toString().toInt()
                if (TABLE_COUNT > 5){
                    val layout = layoutInflater.inflate(R.layout.custom_toast,findViewById<ViewGroup>(R.id.toast_layout_root))
                    val textview = layout.findViewById<TextView>(R.id.toast_text)
                    textview.text = "more than 5 table join is not supported\nTry entering right numbers!"
                    val toast = Toast(this)
                    toast.view = layout
                    toast.duration = Toast.LENGTH_LONG
                    toast.show()
                    error = true

                }else if(TABLE_COUNT < 2){
                    val layout = layoutInflater.inflate(R.layout.custom_toast,findViewById<ViewGroup>(R.id.toast_layout_root))
                    val textview = layout.findViewById<TextView>(R.id.toast_text)
                    textview.text = "less than 2 table join is not supported\nTry entering right numbers!"
                    val toast = Toast(this)
                    toast.view = layout
                    toast.duration = Toast.LENGTH_LONG
                    toast.show()
                    error = true

                }
            }catch (ex:Exception){
                error = true

                val layout = layoutInflater.inflate(R.layout.custom_toast,findViewById<ViewGroup>(R.id.toast_layout_root))
                val textview = layout.findViewById<TextView>(R.id.toast_text)
                textview.text = "Error : Check Input\nTry entering Numbers\nError Code:${ex.printStackTrace()}"
                val toast = Toast(this)
                toast.view = layout
                toast.duration = Toast.LENGTH_LONG
                toast.show()

            }
            if(!error)
                alertDialog.cancel()
        }
    }

    fun resultAlert(Data:Cost){
        val alertDialog = AlertDialog.Builder(this).create()

        val resultLayout = LayoutInflater.from(this).inflate(R.layout.result_alert,null)
        alertDialog.setView(resultLayout)
        alertDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation

        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView = resultLayout.findViewById<RecyclerView>(R.id.result_recycler_view)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = ResultRecyclerAdapter(Data)

        alertDialog.show()
    }

    
}