package com.example.wlpicture.activity.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wlpicture.adapter.TimKamiAdapter
import com.example.wlpicture.databinding.ActivityTimKamiBinding
import com.example.wlpicture.model.TimKamiModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TimKamiActivity : AppCompatActivity() {

    lateinit var binding : ActivityTimKamiBinding

    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityTimKamiBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Firebase.firestore
        getData()
    }

    private fun getData() {
        db.collection("TimKami")
            .get()
            .addOnSuccessListener {
                var listTimKami : ArrayList<TimKamiModel> = ArrayList()
                listTimKami.clear()

                for (document in it) {
                    listTimKami.add((TimKamiModel(
                        document.id as String,
                        document.data.get("namaLengkap") as String,
                        document.data.get("email") as String,
                        document.data.get("alamat") as String,
                        document.data.get("phone") as String,
                        document.data.get("descriptions") as String
                    )))
                }

                var timKamiAdapter = TimKamiAdapter(listTimKami)
                binding.recyclerViewTimKami.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = timKamiAdapter
                }
            }
            .addOnFailureListener {
                Log.v("Hai", "gagal mengambil data")
            }
    }
}