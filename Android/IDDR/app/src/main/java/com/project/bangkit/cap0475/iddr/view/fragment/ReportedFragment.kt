package com.project.bangkit.cap0475.iddr.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.bangkit.cap0475.iddr.databinding.FragmentReportedBinding

class ReportedFragment : Fragment() {

    private lateinit var binding : FragmentReportedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}