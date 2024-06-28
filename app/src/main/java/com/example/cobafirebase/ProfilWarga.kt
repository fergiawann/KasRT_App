package com.example.cobafirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cobafirebase.databinding.ActivityProfilWargaBinding
import com.example.cobafirebase.models.Contacts
import com.squareup.picasso.Picasso

class ProfilWarga : AppCompatActivity() {
    private lateinit var binding: ActivityProfilWargaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilWargaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getParcelableExtra<Contacts>("selected_contact")
        contact?.let {
            binding.apply {
                fullName.text = it.name
                nama.text = it.name
                email.text = it.email
                email2.text = it.email
                nomor.text = it.phoneNumber
                alamat.text = it.alamat
                jabatan.text = it.jabatan
                Picasso.get().load(it.imgUri).into(imgProfile)
            }
        } ?: run {
            Toast.makeText(this, "Gagal memuat informasi warga", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

