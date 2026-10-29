
package com.example.mobile_iqbal.Statistic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class StatistikChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val labels = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "Mei", "Jun"
    )

    private val data1 = floatArrayOf(58f, 37f, 81f, 42f, 34f, 60f)
    private val data2 = floatArrayOf(72f, 36f, 39f, 89f, 95f, 88f)
    private val data3 = floatArrayOf(82f, 36f, 51f, 50f, 86f, 72f)

    fun setData(
        labelsBaru: Array<String>,
        dataBaru1: FloatArray,
        dataBaru2: FloatArray,
        dataBaru3: FloatArray
    ) {
        var labels = labelsBaru
        var data1 = dataBaru1
        var data2 = dataBaru2
        var data3 = dataBaru3
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val left = 35f
        val top = 12f
        val right = width - 8f
        val bottom = height - 28f

        if (right <= left || bottom <= top) return

        // Bingkai grafik
        paint.color = Color.parseColor("#4F9D69")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1.5f
        canvas.drawRect(left, top, right, bottom, paint)

        // Garis bantu dan label sumbu Y
        paint.color = Color.parseColor("#E4E8E3")
        paint.strokeWidth = 1f
        paint.textSize = 9f
        paint.style = Paint.Style.STROKE

        for (i in 0..4) {
            val y = bottom - (bottom - top) * i / 4f
            canvas.drawLine(left, y, right, y, paint)

            paint.style = Paint.Style.FILL
            paint.color = Color.GRAY
            canvas.drawText("${i * 25}", 4f, y + 3f, paint)
            paint.style = Paint.Style.STROKE
            paint.color = Color.parseColor("#E4E8E3")
        }

        // Batang grafik
        val groupWidth = (right - left) / labels.size
        val barWidth = groupWidth * 0.17f

        for (i in labels.indices) {
            val center = left + groupWidth * (i + 0.5f)

            val values = floatArrayOf(
                data1[i], data2[i], data3[i]
            )

            val colors = intArrayOf(
                Color.parseColor("#1F5D42"),
                Color.parseColor("#4F9D69"),
                Color.parseColor("#B9DDB5")
            )

            for (j in values.indices) {
                val barLeft = center +
                        (j - 1) * barWidth - barWidth / 2f

                val barHeight = (bottom - top) *
                        (values[j] / 100f)

                paint.style = Paint.Style.FILL
                paint.color = colors[j]

                canvas.drawRect(
                    barLeft,
                    bottom - barHeight,
                    barLeft + barWidth,
                    bottom,
                    paint
                )
            }

            paint.color = Color.GRAY
            paint.textSize = 9f
            paint.textAlign = Paint.Align.CENTER

            canvas.drawText(
                labels[i],
                center,
                height - 10f,
                paint
            )
        }

        paint.textAlign = Paint.Align.LEFT
    }
}
