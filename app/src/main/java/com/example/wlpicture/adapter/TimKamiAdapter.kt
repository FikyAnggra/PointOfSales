package com.example.wlpicture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wlpicture.R
import com.example.wlpicture.model.TimKamiModel

class TimKamiAdapter (private val timKami:List<TimKamiModel>) : RecyclerView.Adapter<TimKamiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimKamiHolder {
        return TimKamiHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tim_kami, parent, false))
    }

    override fun onBindViewHolder(holder: TimKamiHolder, position: Int) {
        holder.bindTimKami(timKami[position])
    }

    override fun getItemCount(): Int = timKami.size

}

class TimKamiHolder (view : View) : RecyclerView.ViewHolder(view) {

    private val txtNamaLengkap : TextView = view.findViewById(R.id.txtNamaLengkap)
    private val txtEmail : TextView = view.findViewById(R.id.txtEmail)
    private val txtAlamat : TextView = view.findViewById(R.id.txtAlamat)
    private val txtPhone : TextView = view.findViewById(R.id.txtPhone)
    private val txtDescriptions : TextView = view.findViewById(R.id.txtDescriptions)

    fun bindTimKami(timKami : TimKamiModel) {

        val namaLengkap     = "Nama          : ${timKami.namaLengkap}"
        val email           = "Email             : ${timKami.email}"
        val alamat          = "Alamat           : ${timKami.alamat}"
        val phone           = "Phone            : ${timKami.phone}"
        val descriptions    = "Descriptions \n${timKami.descriptions}"

        txtNamaLengkap.text = namaLengkap
        txtEmail.text = email
        txtAlamat.text = alamat
        txtPhone.text = phone
        txtDescriptions.text = descriptions

    }
}