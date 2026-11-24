package com.example.mobile_iqbal.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mobile_iqbal.data.AppDatabase
import com.example.mobile_iqbal.data.entity.NoteEntity
import com.example.mobile_iqbal.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()

            if (title.isBlank() || content.isBlank() || category.isBlank()) {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false

            lifecycleScope.launch {
                try {
                    db.noteDao().insert(
                        NoteEntity(
                            title = title, content = content, category = category
                        )
                    )

                    Toast.makeText(
                        this@NoteFormActivity, "Catatan berhasil disimpan", Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } catch (e: Exception) {

                    binding.btnSaveNote.isEnabled = true

                    Toast.makeText(
                        this@NoteFormActivity, "Gagal menyimpan catatan", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
