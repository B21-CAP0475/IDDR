package com.project.bangkit21.cap0475.iddr.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.bangkit21.cap0475.iddr.R
import com.project.bangkit21.cap0475.iddr.databinding.ActivityReportBinding
import com.project.bangkit21.cap0475.iddr.model.data.ReportEntity
import java.util.*


class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var item : ReportEntity

    companion object {
        const val DATA_REPORTED = "data_reported"
        const val INTENT_CONFIRM_SEND = 26
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        userEmail = firebaseAuth.currentUser?.email.toString()

        item = intent.getParcelableExtra(DATA_REPORTED)!!

        progressBarLoading(true)
        setData()

        binding.btnConfirmSend.setOnClickListener {
            sendData()
        }

    }

    private fun sendData() {
        progressBarLoading(true)

        val dbDoc = db.collection("reports").document()
        dbDoc.set(
            mapOf(
                "image" to item.image,
                "label" to item.label,
                "location" to mapOf(
                    "latitude" to item.latitude,
                    "longitude" to item.longitude
                ),
                "address" to item.address,
                "time" to item.time,
                "user" to userEmail
            )
        ).addOnSuccessListener {
            progressBarLoading(false)
            Toast.makeText(this, "Laporan berhasil dikirim!", Toast.LENGTH_SHORT).show()
            startActivityForResult(Intent(this@ReportActivity, MainActivity::class.java), INTENT_CONFIRM_SEND)
        }.addOnFailureListener {
            Toast.makeText(this, "Laporan gagal dikirim ${it.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setData() {

        Glide.with(this)
            .load(item.image)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivConfirmImage)

        binding.tvLabel.text = item.label
        binding.tvLocation.text = item.address
        binding.tvTime.text = item.time

        progressBarLoading(false)
    }

    private fun progressBarLoading(value: Boolean) {
        if (value) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnConfirmSend.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnConfirmSend.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_CONFIRM_SEND) {
            startActivity(data)
        }
    }
}