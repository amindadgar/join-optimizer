package com.aminDadgar.db_implementation_proj.Utils

import android.util.Log
import com.aminDadgar.db_implementation_proj.Application
import com.aminDadgar.db_implementation_proj.model.CoupleData
import com.aminDadgar.db_implementation_proj.model.TripleData
import com.aminDadgar.db_implementation_proj.model.datamodel

class Cost {
    fun twoBytwo(Data :MutableList<datamodel>): CoupleData {  // this function is used to compare and find tables common attribute
        var Best :CoupleData?= null //The best two attribute join


        Log.d("function_twoBytwo","started !")

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
        var mtemp:Long = 999999999999
        coupleData.forEach {
                if (mtemp > it.JoinResult){  // find the best join order
                    mtemp = it.JoinResult.toLong()
                    Best = it
                }
        }
        Application().BestTwoJoin(Best!!)// save the best two attribute join
        Application().TwoJoinList(coupleData)  //save all two attribute joins
        Log.d("first","${Best!!.JoinResult}  ${Best!!.table1.TABLE_NAME}  ${Best!!.table2.TABLE_NAME}")
        Log.d("function_twoBytwo","ended !")
        return Best!!
    }




    fun threeBythree(Data :MutableList<datamodel>,BestTwoByTwo:CoupleData){
//        val BestTwoByTwo:CoupleData? = Application().GetBestTwoJoin()
        var result = -1
        val tripleData= mutableListOf<TripleData>()

        for (i in 0..4){
            if (BestTwoByTwo.table1.TABLE_NAME != Data[i].TABLE_NAME
                && BestTwoByTwo.table2.TABLE_NAME != Data[i].TABLE_NAME) {  // check not to join a table to itself
                for (j in 1..2) {
                    var bestTempTable = BestTwoByTwo.table1
                    if (j == 2) bestTempTable = BestTwoByTwo.table2

                    /** Table 1 and 2 comparing others Data */

                    if (bestTempTable.attr1 == Data[i].attr1) {   /** compare table(1 or 2)  attribute 1 with Data attribute 1 */

                        if (bestTempTable.firstAttribute > Data[i].firstAttribute) {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / bestTempTable.firstAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (bestTempTable.attr2 == Data[i].attr1) {  /** compare table(1 or 2)  attribute 2 with Data attribute 1 */

                        if (bestTempTable.secondAttribute > Data[i].firstAttribute) {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / bestTempTable.secondAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (bestTempTable.attr1 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 1 with Data attribute 2 */

                        if (bestTempTable.firstAttribute > Data[i].secondAttribute) {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / bestTempTable.firstAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (bestTempTable.attr2 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 2 with Data attribute 2 */
                        if (bestTempTable.secondAttribute > Data[i].secondAttribute) {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / bestTempTable.secondAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestTwoByTwo.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            tripleData.add(
                                TripleData(
                                    BestTwoByTwo.table1,
                                    BestTwoByTwo.table2,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    }else {          /**  if no attributes were equal concat tables  */
                        result = BestTwoByTwo.JoinResult * Data[i].tupleCount
                        tripleData.add(
                            TripleData(
                                BestTwoByTwo.table1,
                                BestTwoByTwo.table2,
                                Data[i],
                                result
                            )
                        )
                    }
                }
            }
            Application().ThreeJoinList(tripleData)
            var Best:TripleData?= null
            var mtemp:Long = 99999999
            Log.d("Three join finished !",tripleData.size.toString())
            tripleData.forEach {  // find the best join order
                Log.d("table","${it.table1} ${it.table2} ${it.table3}  ${it.JoinResult}")
                if (mtemp > it.JoinResult){
                    mtemp = it.JoinResult.toLong()
                    Best = it
                }
            }
        }
    }
}