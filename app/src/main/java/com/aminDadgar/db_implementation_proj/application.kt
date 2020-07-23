package com.aminDadgar.db_implementation_proj

import android.app.Application
import com.aminDadgar.db_implementation_proj.model.CoupleData
import com.aminDadgar.db_implementation_proj.model.TripleData

class Application:Application() {
        var BestTwoJoin: CoupleData? = null  //The best two attribute join
        var TwoJoinList: MutableList<CoupleData>? = null  // all two attribute join

        var BestThreeJoin: TripleData? = null
        var ThreeJoinList: MutableList<TripleData>? = null
    fun BestTwoJoin(data: CoupleData){
        this.BestTwoJoin = data
    }
    fun BestThreeJoin(data: TripleData){
        this.BestThreeJoin = data
    }
    fun TwoJoinList(data:MutableList<CoupleData>){
        this.TwoJoinList = data
    }
    fun ThreeJoinList(data: MutableList<TripleData>){
        this.ThreeJoinList = data
    }
    fun GetBestTwoJoin():CoupleData{
        return this.BestTwoJoin!!
    }
    fun GetBestThreeJoin():TripleData{
        return this.BestThreeJoin!!
    }
    fun GetThreeJoinList(): MutableList<TripleData>{
        return this.ThreeJoinList!!
    }
    fun GetTwoJoinList(): MutableList<CoupleData>{
        return this.TwoJoinList!!
    }
}