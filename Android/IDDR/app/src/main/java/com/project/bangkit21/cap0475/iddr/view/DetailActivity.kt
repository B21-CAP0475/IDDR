package com.project.bangkit21.cap0475.iddr.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.bangkit21.cap0475.iddr.R
import com.project.bangkit21.cap0475.iddr.databinding.ActivityDetailBinding
import com.project.bangkit21.cap0475.iddr.model.data.ReportEntity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val REPORT_ID = "report_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.getParcelableExtra<ReportEntity>(REPORT_ID)
        if (extras != null) {
            populateView(extras)
        }
    }

    private fun populateView(extras: ReportEntity?) {
        Glide.with(this)
            .load(extras?.image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivDetailImage)

        binding.tvTitleDetail.text = "Kerusakan di ${extras?.address}"
        binding.tvDescDetail.text = "Kerusakan jalan dengan klasifikasi ${extras?.label}"
        binding.tvLocation.text = extras?.address
        binding.tvTime.text = extras?.time
    }


}