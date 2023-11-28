package com.app.linechart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lineChart: LineChart = findViewById(R.id.lineChart)

        // Set up the data and customize the chart as needed

        // Customize the X-axis
        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(health("Health", 10f, 50f, 10f, Color.BLUE))
        dataSets.add(health("Recovery", 5f, 65f, 5f, Color.GREEN))
        dataSets.add(health("Workload", 1f, 35f, 20f, Color.RED))

        val lineData = LineData(dataSets)
        lineChart.data = lineData
        lineChart.apply {
            // Customize the LineChart appearance
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)

            setDrawGridBackground(false)
            isDoubleTapToZoomEnabled = false
            setDrawBorders(false)
            description.isEnabled = false
            legend.isEnabled = false

            xAxis.apply {
                textColor = Color.WHITE
                granularity = 1f
                setDrawLabels(false)
            }
            axisLeft.textColor = Color.GRAY
            axisRight.setDrawLabels(false)
            animateX(1800, Easing.EaseInCubic)
            notifyDataSetChanged()
        }
        // Refresh the chart
        lineChart.invalidate()
    }

    fun health(label: String, x: Float, y: Float, z: Float, colorValue: Int): LineDataSet {
        // Add data to the LineChart
        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 0f))
        entries.add(Entry(2f, y))
        entries.add(Entry(3f, z))
        entries.add(Entry(4f, x))
        entries.add(Entry(5f, y))
        entries.add(Entry(6f, z))
        val dataset = LineDataSet(entries, label)
        dataset.apply {
            color = colorValue
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawValues(false)
            setDrawCircles(false)
            lineWidth = 3f
        }
        return dataset
    }
}