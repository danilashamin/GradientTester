package com.chat.betrothal.single.service.gradienttest

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gradientDrawable = LinearGradientDrawable(
            colors = intArrayOf(
                resources.getColor(R.color.colorGradientStart),
                resources.getColor(R.color.colorGradientEnd)
            ),
            offsets = floatArrayOf(0F, 1F),
            cornersRadius = resources.getDimensionPixelSize(R.dimen.d_12common)
        )

        testContainer.background = gradientDrawable


        angleSeekBar
            .setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    gradientDrawable.angle = progress.toFloat()
                    tvAngle.text = "Угол = $progress"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            })

        firstOffsetSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val offset = progress.toFloat() / 100
                val offsets = gradientDrawable.offsets
                val newOffsets = floatArrayOf(offset, offsets?.get(1) ?: 0F)
                gradientDrawable.offsets = newOffsets
                tvFirstOffset.text = "Первый отступ = $offset"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        secondOffsetSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val offset = progress.toFloat() / 100
                val offsets = gradientDrawable.offsets
                val newOffsets = floatArrayOf(offsets?.get(0) ?: 0F, offset)
                gradientDrawable.offsets = newOffsets
                tvSecondOffset.text = "Второй отступ = $offset"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        scaleXSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val scaleX = progress.toFloat() / 100
                gradientDrawable.scale(scaleX, gradientDrawable.scaleY)
                tvScaleX.text = "ScaleX = $scaleX"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        scaleYSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val scaleY = progress.toFloat() / 100
                gradientDrawable.scale(gradientDrawable.scaleX, scaleY)
                tvScaleY.text = "ScaleY = $scaleY"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }
}
