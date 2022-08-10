package com.piappstudio.giftregister.ui.event

import java.sql.Date

data class EventScreenViewModel(
    var name:String?,
    var date:String="jan 21st,2022",
    var numberOfPeople:Int=320,
    var gold:Double=5.3,
    var cashAmount:Double=1.23000,
    var others:Int=50

)

