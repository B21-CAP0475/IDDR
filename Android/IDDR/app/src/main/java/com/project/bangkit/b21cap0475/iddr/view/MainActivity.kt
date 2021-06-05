package com.project.bangkit.b21cap0475.iddr.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.project.bangkit.b21cap0475.iddr.R
import com.project.bangkit.b21cap0475.iddr.databinding.ActivityMainBinding
import com.project.bangkit.b21cap0475.iddr.view.fragment.AccountFragment
import com.project.bangkit.b21cap0475.iddr.view.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.home
            addFragment(HomeFragment())
        }

        binding.bottomNavigationView.background = null

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    addFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    addFragment(AccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        binding.fabAddReport.setOnClickListener {
            startActivity(Intent(this, DetectorActivity::class.java))
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}