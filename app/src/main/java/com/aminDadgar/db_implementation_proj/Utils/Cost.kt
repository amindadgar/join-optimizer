package com.aminDadgar.db_implementation_proj.Utils

import android.util.Log
import com.aminDadgar.db_implementation_proj.model.*

class Cost() {
    var Best2 :CoupleData?= null //The best two attribute join
    var Best3:TripleData?= null
    var Best4:Quadruple?= null
    var Best5:Quintuple?= null

    val coupleData:MutableList<CoupleData> = mutableListOf()
    val tripleData= mutableListOf<TripleData>()
    val quadData = mutableListOf<Quadruple>()
    val quintuple = mutableListOf<Quintuple>()


    fun twoBytwo(Data :MutableList<datamodel>,TABLE_COUNT:Int): CoupleData {  // this function is used to compare and find tables common attribute

        Log.d("function_twoBytwo","started ! tableCount:$TABLE_COUNT")

        var temp: datamodel?
        var temp2: datamodel?
        var result = -1
        for (i in 0 until TABLE_COUNT){
            Log.d("function_twoBytwo","in for loop")
            temp = Data[i]

            for (j in i+1 until TABLE_COUNT){
                Log.d("function_twoBytwo","in second for loop")

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
                }else {
                    result = ( temp.tupleCount * temp2.tupleCount )
                    coupleData.add(CoupleData(temp,temp2,result))
                }
            }
        }
        var mtemp:Long = 999999999999
        coupleData.forEach {
                if (mtemp > it.JoinResult){  // find the best join order
                    mtemp = it.JoinResult.toLong()
                    Best2 = it
                }
        }

        Log.d("function_twoBytwo","ended !${coupleData.size}")

        Log.d("first","${Best2!!.JoinResult}  ${Best2!!.table1.TABLE_NAME}  ${Best2!!.table2.TABLE_NAME}")

        Log.d("Best Two","${Best2!!.table1}  ${Best2!!.table2} ")

        return Best2!!
    }




    fun threeBythree(Data :MutableList<datamodel>,BestTwoByTwo:CoupleData,TABLE_COUNT:Int):TripleData{
        var result = -1

        Log.d("function 3 started","$TABLE_COUNT")
        for (i in 0  until TABLE_COUNT){
            Log.d("Function3","in for loop")
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
        }

        var mtemp:Long = 99999999
        Log.d("Three join finished !",tripleData.size.toString())
        tripleData.forEach {  // find the best join order
            Log.d("table","${it.table1} ${it.table2} ${it.table3}  ${it.JoinResult}")
            if (mtemp > it.JoinResult){
                mtemp = it.JoinResult.toLong()
                Best3 = it
            }
        }

//        Log.d("Best Three","${Best3!!.table1}  ${Best3!!.table2}  ${Best3!!.table3} ")

        return Best3!!
    }
    fun fourJoin(Data: MutableList<datamodel>, BestThreeJoin: TripleData,TABLE_COUNT:Int):Quadruple{

        var result = -1
        for (i in 0 until TABLE_COUNT){
            if (BestThreeJoin.table1.TABLE_NAME != Data[i].TABLE_NAME &&
                    BestThreeJoin.table2.TABLE_NAME != Data[i].TABLE_NAME &&
                    BestThreeJoin.table3.TABLE_NAME != Data[i].TABLE_NAME){
                var tempData:datamodel = BestThreeJoin.table1  /** initialize with table 1*/
                for (j in 0..2){
                    when(j){
                        0 -> tempData = BestThreeJoin.table1
                        1 -> tempData = BestThreeJoin.table2
                        2 -> tempData = BestThreeJoin.table3
                    }
                    /** Table 1 and 2 comparing others Data */

                    if (tempData.attr1 == Data[i].attr1) {   /** compare table(1 or 2)  attribute 1 with Data attribute 1 */

                        if (tempData.firstAttribute > Data[i].firstAttribute) {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / tempData.firstAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr2 == Data[i].attr1) {  /** compare table(1 or 2)  attribute 2 with Data attribute 1 */

                        if (tempData.secondAttribute > Data[i].firstAttribute) {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / tempData.secondAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr1 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 1 with Data attribute 2 */

                        if (tempData.firstAttribute > Data[i].secondAttribute) {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / tempData.firstAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr2 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 2 with Data attribute 2 */
                        if (tempData.secondAttribute > Data[i].secondAttribute) {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / tempData.secondAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (BestThreeJoin.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    }else {          /**  if no attributes were equal concat tables  */
                        result = BestThreeJoin.JoinResult * Data[i].tupleCount
                        quadData.add(
                                Quadruple(
                                    BestThreeJoin.table1,
                                    BestThreeJoin.table2,
                                    BestThreeJoin.table3,
                                    Data[i],
                                    result
                                )
                            )
                    }

                }
            }
        }
        var mtemp:Long = 99999999
        Log.d("Four join finished !",quadData.size.toString())
        quadData.forEach {  // find the best join order
            Log.d("table","${it.table1} ${it.table2} ${it.table3} ${it.table4} ${it.JoinResult}")
            if (mtemp > it.JoinResult){
                mtemp = it.JoinResult.toLong()
                Best4 = it
            }
        }
        Log.d("Best Four","${Best4!!.table1}  ${Best4!!.table2}  ${Best4!!.table3}  ${Best4!!.table4}")


        return Best4!!
    }
    fun FiveJoin(Data: MutableList<datamodel>, bestFourJoin: Quadruple,TABLE_COUNT:Int){
        var result = -1

        for (i in 0 until TABLE_COUNT){
            if (bestFourJoin.table1.TABLE_NAME != Data[i].TABLE_NAME &&
                bestFourJoin.table2.TABLE_NAME != Data[i].TABLE_NAME &&
                bestFourJoin.table3.TABLE_NAME != Data[i].TABLE_NAME &&
                bestFourJoin.table4.TABLE_NAME != Data[i].TABLE_NAME){

                var tempData:datamodel = bestFourJoin.table1
                for (j in 0..3){
                    when(j){
                        0 -> tempData = bestFourJoin.table1
                        1 -> tempData = bestFourJoin.table2
                        2 -> tempData = bestFourJoin.table3
                        3 -> tempData = bestFourJoin.table4
                    }

                    /** Table 1 and 2 comparing others Data */

                    if (tempData.attr1 == Data[i].attr1) {   /** compare table(1 or 2)  attribute 1 with Data attribute 1 */

                        if (tempData.firstAttribute > Data[i].firstAttribute) {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / tempData.firstAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr2 == Data[i].attr1) {  /** compare table(1 or 2)  attribute 2 with Data attribute 1 */

                        if (tempData.secondAttribute > Data[i].firstAttribute) {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / tempData.secondAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / Data[i].firstAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr1 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 1 with Data attribute 2 */

                        if (tempData.firstAttribute > Data[i].secondAttribute) {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / tempData.firstAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    } else if (tempData.attr2 == Data[i].attr2) {  /** compare table(1 or 2)  attribute 2 with Data attribute 2 */
                        if (tempData.secondAttribute > Data[i].secondAttribute) {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / tempData.secondAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        } else {
                            result =
                                (bestFourJoin.JoinResult * Data[i].tupleCount) / Data[i].secondAttribute

                            quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                        }
                    }else {          /**  if no attributes were equal concat tables  */
                        result = bestFourJoin.JoinResult * Data[i].tupleCount
                        quintuple.add(
                                Quintuple(
                                    bestFourJoin.table1,
                                    bestFourJoin.table2,
                                    bestFourJoin.table3,
                                    bestFourJoin.table4,
                                    Data[i],
                                    result
                                )
                            )
                    }
                }

                var mtemp:Long = 99999999
                Log.d("Five join finished !",quintuple.size.toString())
                quintuple.forEach {  // find the best join order
                    Log.d("table","${it.table1} ${it.table2} ${it.table3} ${it.table4} ${it.table5} ${it.JoinResult}")
                    if (mtemp > it.JoinResult){
                        mtemp = it.JoinResult.toLong()
                        Best5 = it
                    }
                }
                Log.d("Best Five","${Best5!!.table1}  ${Best5!!.table2}  ${Best5!!.table3}  ${Best5!!.table4}  ${Best5!!.table5} ${Best5!!.JoinResult}")




            }
        }
    }
}