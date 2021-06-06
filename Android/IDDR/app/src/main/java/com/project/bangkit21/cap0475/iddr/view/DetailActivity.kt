package com.project.bangkit21.cap0475.iddr.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.project.bangkit21.cap0475.iddr.databinding.ActivityDetailBinding
import com.project.bangkit21.cap0475.iddr.model.data.ReportEntity
import com.project.bangkit21.cap0475.iddr.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val REPORT_ID = "report_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val reportId = extras.getString(REPORT_ID)
            if (reportId != null) {
                detailViewModel.setSelectedReport(reportId)
                populateView(detailViewModel.getSelectedReport())
            }
        }
    }

    private fun populateView(selectedReport: ReportEntity) {
        with(binding) {
            tvTitleDetail.text = selectedReport.title
            tvDescDetail.text = selectedReport.desc
            tvLocation.text = selectedReport.location
            tvTime.text = selectedReport.time
        }
    }


}