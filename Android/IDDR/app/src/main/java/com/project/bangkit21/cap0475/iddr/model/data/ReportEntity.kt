package com.project.bangkit21.cap0475.iddr.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity (
    var id: String? ="",
    var label: String? ="",
    var image: String? ="",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0,
    var address: String? = "",
    var time: String? ="",
    var email: String? ="",
) : Parcelable
