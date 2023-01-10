package com.example.wlpicture.activity.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wlpicture.R
import com.example.wlpicture.adapter.TimKamiAdminAdapter
import com.example.wlpicture.databinding.ActivityTimKamiAdminBinding
import com.example.wlpicture.model.TimKamiAdminModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TimKamiAdminActivity : AppCompatActivity() {

    lateinit var binding: ActivityTimKamiAdminBinding

    var db = Firebase.firestore

    lateinit var btnDelete : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityTimKamiAdminBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnDelete = findViewById(R.id.btnHapus)

        db = Firebase.firestore
        getData()

        btnDelete.setOnClickListener() {
            deleteTimKami
        }


        binding.btnTambahkanTim.setOnClickListener(){
            startActivity(Intent(this@TimKamiAdminActivity, FormTimKamiActivity::class.java))
            finish()
        }


    }


    private fun getData() {
        db.collection("TimKami")
            .get()
            .addOnSuccessListener {
                var listTimKamiAdmin : ArrayList<TimKamiAdminModel> = ArrayList()
                listTimKamiAdmin.clear()

                for (document in it) {
                    listTimKamiAdmin.add((TimKamiAdminModel(
                        document.id as String,
                        document.data.get("namaLengkap") as String,
                        document.data.get("email") as String,
                        document.data.get("alamat") as String,
                        document.data.get("phone") as String,
                        document.data.get("descriptions") as String
                    )))
                }

                var timKamiAdminAdapter = TimKamiAdminAdapter(listTimKamiAdmin)
                binding.recyclerViewTimKami.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = timKamiAdminAdapter
                }
            }
            .addOnFailureListener {
                Log.v("Hai", "gagal mengambil data")
            }
    }

     fun deleteTimKami(id : String) {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setTitle("Hapus Data Tim Kami")
            .setMessage("Yakin Mau Hapus?")
            .setPositiveButton(android.R.string.yes){dialog, which ->
                deleteById(id)
            }
            .setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }

    private fun deleteById(id: String) {

        val mFirestore = FirebaseFirestore.getInstance()
        val mTimKamiCollection = mFirestore.collection("TimKami")
        //menghapus data berdasarkan id
        mTimKamiCollection.document(id)
            .delete()
            .addOnCompleteListener { Toast.makeText(this, "Succes Hapus data", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { Toast.makeText(this, "Gagal Hapus data", Toast.LENGTH_SHORT).show() }
    }



}
