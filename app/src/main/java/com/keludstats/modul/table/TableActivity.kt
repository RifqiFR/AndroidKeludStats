package com.keludstats.modul.table

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keludstats.R
import com.keludstats.databinding.TableActivityBinding
import com.keludstats.modul.nilaipertahun.NilaiPerTahunActivity
import com.keludstats.shared.model.IndikatorSatuan

class TableActivity: AppCompatActivity(), TableContract.View {
    private lateinit var binding: TableActivityBinding
    private val presenter: TableContract.Presenter = TablePresenter(this)

    companion object {
        const val TABLE_SUBINDICATOR_BUNDLE_KEY = "TABLE_SUBINDICATOR_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.table_activity)

        intent.getIntExtra(TABLE_SUBINDICATOR_BUNDLE_KEY, 0).let {
            presenter.retrieveTable(it)
        }
    }

    private fun addYear() {
    }

    override fun showTable(data: Array<IndikatorSatuan>) {
        for(indikatorSatuan in data) {
            val indikatorTv = TextView(this).apply {
                text = indikatorSatuan.name
            }

            val satuanTv = TextView(this).apply {
                text = indikatorSatuan.satuan
            }

            val toNilaiBtn = Button(this).apply {
                text = "Nilai Per Tahun"
                setOnClickListener {
                    redirectToNilaiPerTahun(indikatorSatuan.id)
                }
            }

            binding.indikatorSatuanTableGl.apply {
                addView(indikatorTv)
                addView(satuanTv)
            }

            binding.nilaiPerTahunTableGl.addView(toNilaiBtn)
        }
    }

    override fun redirectToNilaiPerTahun(id: Int) {
        startActivity(
            Intent(this, NilaiPerTahunActivity::class.java)
                .putExtra(NilaiPerTahunActivity.NILAI_PER_TAHUN_BUNDLE_KEY, id)
        )
    }
}