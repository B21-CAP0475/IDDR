package com.project.bangkit21.cap0475.iddr.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.project.bangkit21.cap0475.iddr.databinding.FragmentDetailProfileBinding

class ProfileDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        displayValue()
    }

    private fun displayValue() {
        val firebaseUser = firebaseAuth.currentUser
        with(binding) {
            tvEmail.text = firebaseUser?.email
        }
    }
}