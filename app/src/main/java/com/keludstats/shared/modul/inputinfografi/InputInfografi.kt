package com.keludstats.shared.modul.inputinfografi

import com.keludstats.shared.model.Infografi
import java.io.File

open class InputInfografi : Infografi(){
    open var picture: File = File("")
    //if no property is just empty string return true
    open val isValid : Boolean get() = !(
            title.isBlank() || !picture.isFile|| caption.isBlank() || date.isBlank()
            )
}