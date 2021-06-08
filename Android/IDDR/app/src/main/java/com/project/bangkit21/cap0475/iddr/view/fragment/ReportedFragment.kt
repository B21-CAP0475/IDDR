package com.project.bangkit21.cap0475.iddr.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.project.bangkit21.cap0475.iddr.databinding.FragmentReportedBinding
import com.project.bangkit21.cap0475.iddr.model.data.ReportEntity
import com.project.bangkit21.cap0475.iddr.view.DetailActivity
import com.project.bangkit21.cap0475.iddr.view.adapter.ReportAdapter

class ReportedFragment : Fragment(), ReportAdapter.OnItemClickCallback {

    private lateinit var binding: FragmentReportedBinding
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var queryCollection: CollectionReference
    private lateinit var reportAdapter: ReportAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        userEmail = firebaseAuth.currentUser?.email.toString()

        queryCollection = db.collection("reports")

        if (activity != null) {
            setupData()
        }
    }

    private fun setupData() {
        val query: Query = queryCollection.whereEqualTo("user", userEmail)
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<ReportEntity> =
            FirestoreRecyclerOptions.Builder<ReportEntity>()
                .setQuery(query, ReportEntity::class.java)
                .build()

        reportAdapter = ReportAdapter(firestoreRecyclerOptions)
        reportAdapter.setOnItemClickCallback(this)

        with(binding.rvReports) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = reportAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        reportAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        reportAdapter.stopListening()
    }

    override fun onItemClicked(report: ReportEntity) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.REPORT_ID, report)
            context?.startActivity(it)
        }
    }
}