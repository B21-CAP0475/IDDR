package com.project.bangkit21.cap0475.iddr.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.bangkit21.cap0475.iddr.databinding.FragmentHomeBinding
import com.project.bangkit21.cap0475.iddr.view.DetailActivity
import com.project.bangkit21.cap0475.iddr.view.adapter.ReportAdapter
import com.project.bangkit21.cap0475.iddr.viewmodel.ReportViewModel

class HomeFragment : Fragment(), ReportAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val reportsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ReportViewModel::class.java]
            val report = reportsViewModel.getReport()
            val reportAdapter = ReportAdapter()

            with(reportAdapter) {
                setReport(report)
                setOnItemClickCallback(this@HomeFragment)
            }

            with(binding.rvReports) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = reportAdapter
            }
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.REPORT_ID, id)
            context?.startActivity(it)
        }
    }
}