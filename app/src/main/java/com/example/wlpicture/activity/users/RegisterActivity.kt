package com.example.wlpicture.activity.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.wlpicture.databinding.ActivityRegisterBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editTextNamaLengkap.requestFocus()

        binding.txtMasuk.setOnClickListener(){
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }

        binding.btnDaftar.setOnClickListener(){
            val namaLengkap = binding.editTextNamaLengkap.text.toString()
            val email = binding.editTextEmailRegister.text.toString()
            val password = binding.editTextPasswordRegister.text.toString()

            //validasi tidak kosong
            if (namaLengkap.isEmpty()) {
                binding.editTextNamaLengkap.error = "Nama Lengkap Tidak Boleh Kosong"
                binding.editTextNamaLengkap.requestFocus()
                return@setOnClickListener
            } else if (email.isEmpty()) {
                binding.editTextEmailRegister.error = "Email Tidak Boleh Kosong"
                binding.editTextEmailRegister.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                binding.editTextPasswordRegister.error = "Password Tidak Boleh Kosong"
                binding.editTextPasswordRegister.requestFocus()
                return@setOnClickListener
            } else {
                if (namaLengkap.length < 2) {
                    binding.editTextNamaLengkap.error = "Nama Lengkap Minimal 2 Characters"
                    binding.editTextNamaLengkap.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.editTextEmailRegister.error = "Email Tidak Valid"
                    binding.editTextEmailRegister.requestFocus()
                    return@setOnClickListener
                }else if (password.length < 6) {
                    binding.editTextPasswordRegister.error = "Password Minimal 6 Characters"
                    binding.editTextPasswordRegister.requestFocus()
                    return@setOnClickListener
                } else {
                    Register(namaLengkap, email, password)
                }
            }

        }
    }

    private fun Register(namaLengkap: String, email: String, password: String) {
        val users = hashMapOf(
            "nama_lengkap" to namaLengkap,
            "email" to email,
            "password" to password,
            "role" to "Users"
        )

        db.collection("users")
            .add(users)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(baseContext, "Register berhasil\nSilahkan Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
                println(e.message)
            }
    }

}