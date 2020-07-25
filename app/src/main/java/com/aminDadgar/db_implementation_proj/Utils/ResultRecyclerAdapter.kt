package com.aminDadgar.db_implementation_proj.Utils


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ResultRecyclerAdapter(val Data:Cost):RecyclerView.Adapter<dataHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataHolder {
        val layout = LayoutInflater.from(parent.context)
        return dataHolder(layout,parent)
    }

    override fun getItemCount(): Int {
        return Data.tripleData.size + Data.quintuple.size + Data.quadData.size + Data.coupleData.size
    }

    override fun onBindViewHolder(holder: dataHolder, position: Int) {
        var temp = -1
        if (position < Data.coupleData.size){

            holder.bind(Data.coupleData[position],Data.Best2!!)
            temp = Data.coupleData.size

        }else if (position < Data.tripleData.size + Data.coupleData.size){
            temp = position % Data.coupleData.size
            holder.bind(Data.tripleData[temp],Data.Best3!!,Data.Best2!!)

            Log.d("table 3","${Data.tripleData[temp].table1} ${Data.tripleData[temp].table2}" +
                    " ${Data.tripleData[temp].table3}  ${Data.tripleData[ temp].JoinResult}")

        }else if (position < Data.quadData.size + Data.tripleData.size + Data.coupleData.size ){

            temp = position % (Data.tripleData.size + Data.coupleData.size)
            holder.bind(Data.quadData[ temp],Data.Best4!!,Data.Best3!!)


        }else if (position < Data.quintuple.size + Data.quadData.size + Data.tripleData.size + Data.coupleData.size ){
            temp = position % (Data.quadData.size + Data.tripleData.size + Data.coupleData.size)
            holder.bind(Data.quintuple[temp],Data.Best5!!,Data.Best4!!)

        }else{
            Log.e("table","Error")
        }

    }
}

