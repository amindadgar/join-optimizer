package com.aminDadgar.db_implementation_proj.Utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aminDadgar.db_implementation_proj.R
import com.aminDadgar.db_implementation_proj.model.CoupleData
import com.aminDadgar.db_implementation_proj.model.Quadruple
import com.aminDadgar.db_implementation_proj.model.Quintuple
import com.aminDadgar.db_implementation_proj.model.TripleData

class dataHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.result_alert_recycler,parent,false)){

    private var Relations: TextView?=null
    private var size: TextView?=null
    private var cost: TextView?=null
    private var bestOrder: TextView?=null

    init {
        Relations = itemView.findViewById(R.id.Relation_name_text)
        size = itemView.findViewById(R.id.size_text)
        cost = itemView.findViewById(R.id.result_cost_text)
        bestOrder = itemView.findViewById(R.id.Best_order_text)
    }

    @SuppressLint("SetTextI18n")
    fun bind(datamodel: CoupleData, best: CoupleData){
        Relations!!.text = "${datamodel.table1.TABLE_NAME},${datamodel.table2.TABLE_NAME}"
        size!!.text = "0"
        cost!!.text = "${datamodel.JoinResult}"
        bestOrder!!.text = "${best.table1.TABLE_NAME},${best.table2.TABLE_NAME}"
    }
    @SuppressLint("SetTextI18n")
    fun bind(datamodel: TripleData, best3: TripleData, best2: CoupleData){
        Relations!!.text = "${datamodel.table1.TABLE_NAME},${datamodel.table2.TABLE_NAME},${datamodel.table3.TABLE_NAME}"
        size!!.text = "${best2.JoinResult}"
        cost!!.text = "${datamodel.JoinResult}"
        bestOrder!!.text = "${best3.table1.TABLE_NAME},${best3.table2.TABLE_NAME},${best3.table3.TABLE_NAME}"
    }
    @SuppressLint("SetTextI18n")
    fun bind(datamodel: Quadruple, best4: Quadruple, best3: TripleData){

        Relations!!.text = "${datamodel.table1.TABLE_NAME}," +
                "${datamodel.table2.TABLE_NAME}" +
                ",${datamodel.table3.TABLE_NAME},${datamodel.table4.TABLE_NAME}"

        size!!.text = "${best3.JoinResult}"
        cost!!.text = "${datamodel.JoinResult}"

        bestOrder!!.text = "${best4.table1.TABLE_NAME},${best4.table2.TABLE_NAME}" +
                ",${best4.table3.TABLE_NAME},${best4.table4.TABLE_NAME}"
    }
    @SuppressLint("SetTextI18n")
    fun bind(datamodel: Quintuple, best5: Quintuple, best4: Quadruple){
        Relations!!.text = "${datamodel.table1.TABLE_NAME}," +
                "${datamodel.table2.TABLE_NAME}" +
                ",${datamodel.table3.TABLE_NAME}" +
                ",${datamodel.table4.TABLE_NAME}" +
                ",${datamodel.table5.TABLE_NAME}"

        size!!.text = "${best4.JoinResult}"
        cost!!.text = "${datamodel.JoinResult}"

        bestOrder!!.text = "${best5.table1.TABLE_NAME},${best5.table2.TABLE_NAME}," +
                "${best5.table3.TABLE_NAME},${best5.table4.TABLE_NAME},${best5.table5.TABLE_NAME}"
    }



}