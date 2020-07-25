package com.aminDadgar.db_implementation_proj.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aminDadgar.db_implementation_proj.R
import com.aminDadgar.db_implementation_proj.Utils.Cost
import com.aminDadgar.db_implementation_proj.Utils.ResultRecyclerAdapter
import com.aminDadgar.db_implementation_proj.model.datamodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var listView:ListView
    var TABLE_COUNT = -1
    var TableNames : Array<Char> = arrayOf('R','S','T','U','Z')
    var index = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        table_name.text = "Table: "+TableNames[index + 1].toString()
        alertDialog()



        val Data :MutableList<datamodel> = mutableListOf()

        Data.add(datamodel('R','a',100,'b',50,1000))
        Data.add(datamodel('S','b',200,'c',500,2000))
        Data.add(datamodel('T','c',50,'d',20,2000))
        Data.add(datamodel('U','d',500,'e',100,2000))
        Data.add(datamodel('Z','a',50,'e',200,1000))


        button.setOnClickListener {
            val cost = Cost()

            val bestTwoJoin = cost.twoBytwo(Data)
            val bestThreeJoin = cost.threeBythree(Data,bestTwoJoin)
            val bestFourJoin = cost.fourJoin(Data,bestThreeJoin)
            cost.FiveJoin(Data,bestFourJoin)
            resultAlert(cost)
            val animation = AlphaAnimation(1.0f,0.0f)  //for animating TextView
            animation.repeatMode = Animation.REVERSE
            animation.repeatCount = 1
            animation.duration = 200


/*
            var inputError = false
            try {

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

                if (index < 4) {
                    index++
                    table_name.startAnimation(animation)
                    Data.add(
                        datamodel(
                            TableNames[index],
                            Attr11,
                            FirstAttribute,
                            Attr12,
                            SecondAttribute,
                            TupleCount
                        )
                    )
//                    Log.d("table",index.toString())
                }
            }
*/
//                val Cost = Cost()
                animation.setAnimationListener(object :Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                     /*   if (index < 4) {
                                table_name.text = "Table: " + TableNames[index + 1].toString()
                            Log.d("tablename",TableNames[index + 1].toString())
                        } */
//                        else {
                            button.text = "Clculate"

//                            val bestTwoJoin = Cost.twoBytwo(Data)
//                            val bestThreeJoin = Cost.threeBythree(Data,bestTwoJoin)
//                            val bestFourJoin = Cost.fourJoin(Data,bestThreeJoin)
//                            Cost.FiveJoin(Data,bestFourJoin)
//                        }
                    }

                    override fun onAnimationEnd(animation: Animation?) {}
                    override fun onAnimationStart(animation: Animation?) {}
                })

        }


    }



    fun alertDialog(){
        val alertDialog = AlertDialog.Builder(this).create()
        val alertDialogLayout = LayoutInflater.from(this).inflate(R.layout.table_count_alert,null)
        alertDialog.setView(alertDialogLayout)
        alertDialog.setCancelable(false)

        val alertDialogEditText = alertDialogLayout.findViewById<EditText>(R.id.table_count_edit_text)
        val alertDialogButton = alertDialogLayout.findViewById<Button>(R.id.table_count_Submit)

        alertDialog.show()

        alertDialogButton.setOnClickListener {
            var error = false
            try {
                TABLE_COUNT = alertDialogEditText.text.toString().toInt()
            }catch (ex:Exception){
                error = true
                Toast.makeText(this,"Error : Check Input\nTry entering Numbers" +
                        "\nError Code:${ex.printStackTrace()}",Toast.LENGTH_LONG).show()
            }
            if(!error)
                alertDialog.cancel()
        }
    }

    fun resultAlert(Data:Cost){
        val alertDialog = AlertDialog.Builder(this).create()

        val resultLayout = LayoutInflater.from(this).inflate(R.layout.result_alert,null)
        alertDialog.setView(resultLayout)

//        val listAdapter = ListViewAdapter(Data,this)
//        listView = resultLayout.findViewById(R.id.result_ListView)
//        listView.adapter = listAdapter
        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView = resultLayout.findViewById<RecyclerView>(R.id.result_recycler_view)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = ResultRecyclerAdapter(Data)

        alertDialog.show()
    }

    
}