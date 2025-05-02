package com.example.cashlab20.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cashlab20.R
import com.example.cashlab20.databinding.FragmentHomeBinding
import com.example.cashlab20.network.StockApiService
import com.example.cashlab20.network.StockData
import com.example.cashlab20.network.StockHistoryResponse
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var lineChart: LineChart
    private lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        lineChart = view.findViewById(R.id.lineChart)
        spinner = view.findViewById(R.id.symbolSpinner)

        setupSpinner()
        return view
    }


    private fun setupSpinner() {
        val stockSymbols = listOf("AAPL", "MSFT", "GOOGL", "TSLA", "AMZN")

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item_white, stockSymbols)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedSymbol = stockSymbols[position]
                fetchStockHistory(selectedSymbol)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }



    private fun fetchStockHistory(symbol: String) {
        RetrofitInstance.apiService.getStockHistory(symbol).enqueue(object :
            Callback<StockHistoryResponse> {
            override fun onResponse(call: Call<StockHistoryResponse>, response: Response<StockHistoryResponse>) {
                if (response.isSuccessful) {
                    val stockHistory = response.body()
                    if (stockHistory != null) {
                        showDataOnChart(stockHistory.values)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<StockHistoryResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Fallo de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDataOnChart(stockData: List<StockData>) {
        val entries = stockData.mapIndexed { index, data ->
            Entry(index.toFloat(), data.close.toFloat())
        }

        val dataSet = LineDataSet(entries, "Historial de Acción")
        dataSet.color = resources.getColor(R.color.blue, null)
        dataSet.valueTextColor = resources.getColor(R.color.white, null)
        dataSet.setCircleColor(resources.getColor(R.color.teal_200, null))

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.description.isEnabled = false

        lineChart.invalidate()
    }

    object RetrofitInstance {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.twelvedata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: StockApiService = retrofit.create(StockApiService::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}