package com.example.wlpicture.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.wlpicture.databinding.ActivityFormTimKamiBinding
import com.example.wlpicture.model.TimKamiAdminModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FormTimKamiActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val REQ_EDIT = "req_edit"
    }

    lateinit var binding : ActivityFormTimKamiBinding
    val db = Firebase.firestore
    private val mFirestore = FirebaseFirestore.getInstance()
    private var timKamiAdmin : TimKamiAdminModel ?= null
    private var isEdit = false
    private val mTimKamiCollection = mFirestore.collection("TimKami")

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityFormTimKamiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        isEdit = intent.getBooleanExtra(REQ_EDIT, false)
        timKamiAdmin = intent.getParcelableExtra(EXTRA_DATA)

        binding.editTextNamaLengkapTimKami.requestFocus()

        binding.btnTambahkanFormTimKami.setOnClickListener(){
            val namaLengkap = binding.editTextNamaLengkapTimKami.text.toString()
            val email = binding.editTextEmailTimKami.text.toString()
            val alamat = binding.editTextAlamatTimKami.text.toString()
            val phone = binding.editTextPhoneTimKami.text.toString()
            val descriptions = binding.editTextDescriptionsTimKami.text.toString()

            //validasi tidak kosong
            if (namaLengkap.isEmpty()) {
                binding.editTextNamaLengkapTimKami.error = "Nama Lengkap Tidak Boleh Kosong"
                binding.editTextNamaLengkapTimKami.requestFocus()
                return@setOnClickListener
            } else if (email.isEmpty()) {
                binding.editTextEmailTimKami.error = "Email Tidak Boleh Kosong"
                binding.editTextEmailTimKami.requestFocus()
                return@setOnClickListener
            } else if (alamat.isEmpty()) {
                binding.editTextAlamatTimKami.error = "Alamat Tidak Boleh Kosong"
                binding.editTextAlamatTimKami.requestFocus()
                return@setOnClickListener
            } else if (phone.isEmpty()) {
                binding.editTextPhoneTimKami.error = "Phone Tidak Boleh Kosong"
                binding.editTextPhoneTimKami.requestFocus()
                return@setOnClickListener
            } else if (descriptions.isEmpty()) {
                binding.editTextPhoneTimKami.error = "Descriptions Tidak Boleh Kosong"
                binding.editTextPhoneTimKami.requestFocus()
                return@setOnClickListener
            }else {
                if (namaLengkap.length < 2) {
                    binding.editTextNamaLengkapTimKami.error = "Nama Lengkap Minimal 2 Characters"
                    binding.editTextNamaLengkapTimKami.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.editTextEmailTimKami.error = "Email Tidak Valid"
                    binding.editTextEmailTimKami.requestFocus()
                    return@setOnClickListener
                }else if (phone.length < 10 && phone.length < 15) {
                    binding.editTextPhoneTimKami.error = "Phone Number Minimal 10 Karakter"
                    binding.editTextPhoneTimKami.requestFocus()
                    return@setOnClickListener
                } else if (phone.length > 15) {
                    binding.editTextPhoneTimKami.error = "Phone Number Maksimal 15 Karakter"
                    binding.editTextPhoneTimKami.requestFocus()
                    return@setOnClickListener
                } else {
                    saveData()
                    startActivity(Intent(this@FormTimKamiActivity, TimKamiAdminActivity::class.java))
                    finish()
                }
            }

        }

        binding.btnBack.setOnClickListener(){
            startActivity(Intent(this@FormTimKamiActivity, TimKamiAdminActivity::class.java))
            finish()
        }
    }

    private fun saveData() {
        setData(timKamiAdmin?.id)
    }

    private fun setData(id: String?) {
        CreateTimKami(id).addOnCompleteListener {
            if (it.isSuccessful) {
                if (isEdit) {
                    Toast.makeText(this, "Sukses perbarui data", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Sukses tambah data", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Gagal tambah data", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error Added data ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }



    private fun CreateTimKami(id: String?): Task<Void> {
        val writeBatch = mFirestore.batch()
        val path = binding.editTextEmailTimKami.text.toString()
        val id = id ?: path

        val namaLengkap = binding.editTextNamaLengkapTimKami.text.toString()
        val email = binding.editTextEmailTimKami.text.toString()
        val alamat = binding.editTextAlamatTimKami.text.toString()
        val phone = binding.editTextPhoneTimKami.text.toString()
        val descriptions = binding.editTextDescriptionsTimKami.text.toString()

        val timKamiAdmin = TimKamiAdminModel(id,namaLengkap,email,alamat,phone,descriptions)

        writeBatch.set(mTimKamiCollection.document(id), timKamiAdmin)

        return writeBatch.commit()

        }
}