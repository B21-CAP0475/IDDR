package com.project.bangkit.b21cap0475.iddr.viewmodel

import androidx.lifecycle.ViewModel
import com.project.bangkit.b21cap0475.iddr.model.data.ReportEntity
import com.project.bangkit.b21cap0475.iddr.model.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var id : String

    fun setSelectedReport(id: String) {
        this.id = id
    }

    fun getSelectedReport() : ReportEntity {
        lateinit var reports : ReportEntity

        val reportGenerate = DataDummy.generateDummyReport()

        for (report in reportGenerate) {
            if (report.id.toString() == id) reports = report
        }

        return reports
    }
}