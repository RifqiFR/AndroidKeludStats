package com.keludstats.modul.updateinfografi

import com.keludstats.shared.model.Infografi
import com.keludstats.shared.modul.inputinfografi.InputInfografi

class UpdateInfografiModel(infografi: Infografi) : InputInfografi() {
    override val isValid : Boolean get() = !(
            title.isBlank() || caption.isBlank() || date.isBlank()
    )

    init {
        id = infografi.id
        title = infografi.title
        caption = infografi.caption
        pictureLink = infografi.pictureLink
        date = infografi.date
    }
}