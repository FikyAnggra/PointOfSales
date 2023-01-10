package com.example.wlpicture.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.wlpicture.R
import com.example.wlpicture.activity.admin.FormTimKamiActivity
import com.example.wlpicture.model.TimKamiAdminModel
import com.google.firebase.firestore.FirebaseFirestore

class TimKamiAdminAdapter(private val timKamiAdmin:List<TimKamiAdminModel>) : RecyclerView.Adapter<TimKamiAdminHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimKamiAdminHolder {
        return TimKamiAdminHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tim_kami_admin, parent, false))
    }

    override fun getItemCount(): Int = timKamiAdmin.size

    override fun onBindViewHolder(holder: TimKamiAdminHolder, position: Int) {
        holder.bindTimKami(timKamiAdmin[position])
    }
}


class TimKamiAdminHolder (view : View) : RecyclerView.ViewHolder(view) {

    private val txtNamaLengkap : TextView = view.findViewById(R.id.txtNamaLengkap)
    private val txtEmail : TextView = view.findViewById(R.id.txtEmail)
    private val txtAlamat : TextView = view.findViewById(R.id.txtAlamat)
    private val txtPhone : TextView = view.findViewById(R.id.txtPhone)
    private val txtDescriptions : TextView = view.findViewById(R.id.txtDescriptions)

    fun bindTimKami(timKamiAdmin : TimKamiAdminModel) {

        val namaLengkap     = "Nama          : ${timKamiAdmin.namaLengkap}"
        val email           = "Email             : ${timKamiAdmin.email}"
        val alamat          = "Alamat           : ${timKamiAdmin.alamat}"
        val phone           = "Phone            : ${timKamiAdmin.phone}"
        val descriptions    = "Descriptions \n${timKamiAdmin.descriptions}"

        txtNamaLengkap.text = namaLengkap
        txtEmail.text = email
        txtAlamat.text = alamat
        txtPhone.text = phone
        txtDescriptions.text = descriptions

    }
}