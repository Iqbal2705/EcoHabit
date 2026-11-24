package com.example.mobile_iqbal.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val title: String, val content: String,

    @ColumnInfo(defaultValue = "'Umum'") val category: String = "Umum",

    val createdAt: Long = System.currentTimeMillis()
)