
package com.example.mobile_iqbal.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_iqbal.databinding.ItemActivityBinding

class ActivityRecyclerAdapter(
    private val context: Context,
    private val activityList: List<ActivityModel>,
    private val onItemClick: (ActivityModel) -> Unit,
    private val onItemLongClick: (ActivityModel) -> Unit
) : RecyclerView.Adapter<ActivityRecyclerAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(
        val binding: ItemActivityBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActivityViewHolder {

        val binding = ItemActivityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ActivityViewHolder,
        position: Int
    ) {
        val data = activityList[position]

        with(holder.binding) {
            txtActivityName.text = data.nama
            txtActivityDescription.text = data.deskripsi

            val gambar = try {
                context.assets
                    .open("drawable-activity/${data.gambar}")
                    .use {
                        BitmapFactory.decodeStream(it)
                    }
            } catch (e: Exception) {
                Log.e(
                    "ActivityRecyclerAdapter",
                    "Gagal memuat gambar: ${data.gambar}",
                    e
                )

                try {
                    context.assets
                        .open("drawable-activity/ic_sampah.png")
                        .use {
                            BitmapFactory.decodeStream(it)
                        }
                } catch (error: Exception) {
                    null
                }
            }

            imgActivityIcon.setImageBitmap(gambar)

            root.setOnClickListener {
                onItemClick(data)
            }

            root.setOnLongClickListener {
                onItemLongClick(data)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return activityList.size
    }
}
