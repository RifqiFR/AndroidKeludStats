package com.keludstats.modul.nilaipertahun

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.keludstats.R
import com.keludstats.databinding.NilaiPerTahunActivityBinding
import com.keludstats.shared.model.NilaiPerTahun

class NilaiPerTahunActivity : AppCompatActivity(), NilaiPerTahunContract.View {
    private lateinit var binding: NilaiPerTahunActivityBinding
    private val presenter: NilaiPerTahunContract.Presenter = NilaiPerTahunPresenter(this)

    companion object {
        const val NILAI_PER_TAHUN_BUNDLE_KEY = "NILAI_PER_TAHUN_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.nilai_per_tahun_activity)

        intent.getIntExtra(NILAI_PER_TAHUN_BUNDLE_KEY, 0).let {
            presenter.retrieveNilaiPerTahun(it)
        }
    }

    override fun showNilaiPerTahuns(data: Array<NilaiPerTahun>) {
        for(nilaiPerTahun in data) {
            val tahunTv = TextView(this).apply {
                text = "${nilaiPerTahun.tahun}"
            }

            val nilaiTv = TextView(this).apply {
                text = "${nilaiPerTahun.nilai}"
            }

            binding.tahunNilaiTableGl.apply{
                addView(tahunTv)
                addView(nilaiTv)
            }
        }
    }
}