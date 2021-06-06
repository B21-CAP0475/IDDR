package com.project.bangkit.cap0475.iddr.model.utils

import com.project.bangkit.cap0475.iddr.model.data.ReportEntity

object DataDummy {

    fun generateDummyReport(): List<ReportEntity> {

        val reports = ArrayList<ReportEntity>()

        for (i in 1..10) {
            reports.add(
                ReportEntity(
                    i,
                    "Jalan Rusak di Berngam",
                    "Laporan jalan rusak di Berngam pada tanggal 02 Juni 2021 dengan kerusakan tingkat parah (D44) dan harus segera mendapat perbaikan.",
                    "Berngam",
                    "15:30"
                )
            )
        }

        return reports
    }
}