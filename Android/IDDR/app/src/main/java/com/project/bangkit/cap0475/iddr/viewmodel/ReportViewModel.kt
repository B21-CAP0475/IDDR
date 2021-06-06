package com.project.bangkit.cap0475.iddr.viewmodel

import androidx.lifecycle.ViewModel
import com.project.bangkit.cap0475.iddr.model.data.ReportEntity
import com.project.bangkit.cap0475.iddr.model.utils.DataDummy

class ReportViewModel : ViewModel() {

    fun getReport() : List<ReportEntity> = DataDummy.generateDummyReport()
}