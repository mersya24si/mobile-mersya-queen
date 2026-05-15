// RegisterActivity.kt

package com.example.mersya_queen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.mersya_queen.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sharedPref =
            getSharedPreferences("register_pref", MODE_PRIVATE)

        // Ambil email dari halaman gmail
        val email =
            intent.getStringExtra("email")

        binding.etEmailRegister.setText(email)

        // Tombol registrasi

        binding.btnRegister.setOnClickListener {

            val nama =
                binding.etNama.text.toString().trim()

            val emailRegister =
                binding.etEmailRegister.text.toString().trim()

            val username =
                binding.etUsername.text.toString().trim()

            val password =
                binding.etPassword.text.toString().trim()

            when {

                // Semua field wajib diisi

                nama.isEmpty() ||
                        emailRegister.isEmpty() ||
                        username.isEmpty() ||
                        password.isEmpty() -> {

                    showError("Semua field wajib diisi")
                }

                // Password minimal 6 karakter

                password.length < 6 -> {

                    showError("Password minimal 6 karakter")
                }

                // Username tidak boleh ada spasi

                username.contains(" ") -> {

                    showError("Username tidak boleh mengandung spasi")
                }

                else -> {

                    // Simpan ke SharedPreferences

                    sharedPref.edit {

                        putString("nama", nama)
                        putString("email", emailRegister)
                        putString("username", username)
                        putString("password", password)
                    }

                    // Dialog berhasil

                    MaterialAlertDialogBuilder(this)
                        .setTitle("Registrasi Berhasil")
                        .setMessage("Data berhasil disimpan")
                        .setPositiveButton("OK") { _, _ ->

                            startActivity(
                                Intent(
                                    this,
                                    LoginActivity::class.java
                                )
                            )

                            finish()
                        }
                        .show()
                }
            }
        }
    }

    private fun showError(message: String) {

        MaterialAlertDialogBuilder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}