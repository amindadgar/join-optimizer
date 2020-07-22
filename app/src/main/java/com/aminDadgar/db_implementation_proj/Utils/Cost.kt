package com.aminDadgar.db_implementation_proj.Utils

import android.util.Log
import com.aminDadgar.db_implementation_proj.Application
import com.aminDadgar.db_implementation_proj.model.CoupleData
import com.aminDadgar.db_implementation_proj.model.datamodel

class Cost {
    fun twoBytwo(Data :MutableList<datamodel>){  // this function is used to compare and find tables common attribute
        Log.d("function","started")
        var temp: datamodel?
        var temp2: datamodel?
        var result = -1
        val coupleData:MutableList<CoupleData> = mutableListOf()
        for (i in 0..4){
            temp = Data[i]
            for (j in i+1 ..4){
                temp2 = Data[j]
                if (temp.attr1 == temp2.attr1){
                    if (temp.firstAttribute > temp2.firstAttribute){
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp.firstAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }else{
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp2.firstAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }
                }else if (temp.attr1 == temp2.attr2){
                    if (temp.firstAttribute > temp2.secondAttribute){
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp.firstAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }else{
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp2.secondAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }
                }else if (temp.attr2 == temp2.attr1){
                    if (temp.secondAttribute > temp2.firstAttribute){
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp.secondAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }else{
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp2.firstAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }
                }else if (temp.attr2 == temp2.attr2){
                    if (temp.secondAttribute > temp2.secondAttribute){
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp.secondAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }else{
                        result = ( temp.tupleCount * temp2.tupleCount ) / temp2.secondAttribute
                        coupleData.add(CoupleData(temp,temp2,result))
                    }
                }
            }
        }
        var Best :CoupleData?= null
        var mtemp:Int = -1
        coupleData.forEach {
                if (mtemp < it.JoinResult){
                    mtemp = it.JoinResult
                    Best = it
                }
        }
        Application().BestTwoByTwo = Best
        Log.d("first","${Best!!.JoinResult}  ${Best!!.table1.TABLE_NAME}  ${Best!!.table2.TABLE_NAME}")
    }
}