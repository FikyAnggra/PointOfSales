package com.example.wlpicture.activity.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wlpicture.activity.admin.HomeAdminActivity
import com.example.wlpicture.databinding.ActivityLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editTextEmailLogin.requestFocus()

        binding.txtDaftar.setOnClickListener(){
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }

        binding.btnMasuk.setOnClickListener(){

            //validasi tidak kosong
            if (binding.editTextEmailLogin.text.toString().isEmpty()) {
                binding.editTextEmailLogin.error = "Email Tidak Boleh Kosong"
                binding.editTextEmailLogin.requestFocus()
                return@setOnClickListener
            } else if (binding.editTextPasswordLogin.text.toString().isEmpty()) {
                binding.editTextPasswordLogin.error = "Password Tidak Boleh Kosong"
                binding.editTextPasswordLogin.requestFocus()
                return@setOnClickListener
            } else {
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            var email = document.getString("email")
                            var password = document.getString("password")
                            var role = document.getString("role")


                            if (role == "Users") {
                                if(binding.editTextEmailLogin.text.toString().equals(email) && binding.editTextPasswordLogin.text.toString().equals(password)) {
                                    startActivity(Intent(this@LoginActivity, HomeUsersActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(baseContext, "Email Atau Password Anda Salah", Toast.LENGTH_SHORT).show()
                                }

                            } else {
                                if(binding.editTextEmailLogin.text.toString().equals(email) && binding.editTextPasswordLogin.text.toString().equals(password)) {
                                    startActivity(Intent(this@LoginActivity, HomeAdminActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(baseContext, "Email Atau Password Anda Salah", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(baseContext, exception.message, Toast.LENGTH_SHORT).show()
                        println(exception.message)
                    }
            }
        }
    }
}