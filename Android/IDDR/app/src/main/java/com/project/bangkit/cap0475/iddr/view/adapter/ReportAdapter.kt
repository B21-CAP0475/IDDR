package com.project.bangkit.cap0475.iddr.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.bangkit.cap0475.iddr.databinding.ItemReportBinding
import com.project.bangkit.cap0475.iddr.model.data.ReportEntity

class ReportAdapter : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var listReport = ArrayList<ReportEntity>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setReport(reports: List<ReportEntity>?) {
        if (reports.isNullOrEmpty()) return
        this.listReport.clear()
        this.listReport.addAll(reports)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) =
        holder.bind(report = listReport[position])

    override fun getItemCount(): Int = listReport.size

    inner class ReportViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity) {
            binding.apply {

                tvTitleReport.text = report.title
                tvDetailReport.text = report.desc
                tvLocation.text = report.location
                tvTime.text = report.time

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(report.id.toString())
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }

}