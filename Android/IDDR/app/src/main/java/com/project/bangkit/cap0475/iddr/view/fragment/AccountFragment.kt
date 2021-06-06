package com.project.bangkit.cap0475.iddr.view.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.project.bangkit.cap0475.iddr.databinding.FragmentAccountBinding
import com.project.bangkit.cap0475.iddr.databinding.LayoutHeaderProfileBinding
import com.project.bangkit.cap0475.iddr.R
import com.project.bangkit.cap0475.iddr.view.LoginActivity
import com.project.bangkit.cap0475.iddr.view.adapter.SectionsPagerAdapter
import java.util.*

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        val sectionsPagerAdapter = context?.let { SectionsPagerAdapter(it, childFragmentManager) }
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        val profileHeaderBinding: LayoutHeaderProfileBinding = binding.profileHeader
        profileHeaderBinding.ibLogout.setOnClickListener {
            alertLogout()
        }
    }

    private fun alertLogout() {
        AlertDialog.Builder(context)
            .setTitle("Keluar")
            .setMessage("Kamu yakin ingin keluar dari akun saat ini?")
            .setPositiveButton("Ya") { _, _ ->
                firebaseAuth.signOut()
                checkUser()
            }
            .setNegativeButton("Tidak") { _, _ -> }
            .show()
    }

    private fun convertDate(regDate: String) : String {
        val dateTime = regDate.substring(8,10)
        val dateMonth = regDate.substring(4,7)
        val dateYear = regDate.substring(30)
        val timeRegist = regDate.substring(11,19)

        return "$dateTime $dateMonth $dateYear $timeRegist"
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            context?.startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        } else {
            val registDate: String =
                firebaseUser.metadata?.creationTimestamp?.let { Date(it) }.toString()
            Log.d("Data Tanggal", "checkUser: $registDate")
            val userRegistDate = convertDate(registDate)

            binding.apply {
                tvName.text = firebaseUser.displayName
                tvCreationDate.text =
                    this@AccountFragment.resources.getString(R.string.regist_date, userRegistDate)

                Glide.with(this@AccountFragment)
                    .load(firebaseUser.photoUrl)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(civProfile)
            }
        }
    }

}