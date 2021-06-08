package com.project.bangkit21.cap0475.iddr.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.project.bangkit21.cap0475.iddr.R
import com.project.bangkit21.cap0475.iddr.databinding.ItemReportBinding
import com.project.bangkit21.cap0475.iddr.model.data.ReportEntity

class ReportAdapter(options: FirestoreRecyclerOptions<ReportEntity>) : FirestoreRecyclerAdapter<ReportEntity, ReportAdapter.ReportViewHolder>(
    options
) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

//    fun setReport(reports: List<ReportEntity>?) {
//        if (reports.isNullOrEmpty()) return
//        this.listReport.clear()
//        this.listReport.addAll(reports)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int, model: ReportEntity) {
        holder.bind(report = model)
    }

    inner class ReportViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity) {
            binding.apply {

                Glide.with(itemView)
                    .load(report.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(binding.ivReportImage)

                tvTitleReport.text = "Kerusakan di ${report.address}"
                tvDetailReport.text = "Kerusakan jalan dengan klasifikasi ${report.label}"
                tvLocation.text = report.address
                tvTime.text = report.time

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(report)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(report: ReportEntity)
    }

}