package com.keludstats.modul.dashboard.fragment.indicator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keludstats.R
import com.keludstats.databinding.DashboardIndikatorFragmentBinding
import com.keludstats.shared.model.Indikator
import com.simple.pos.shared.extension.showToast

class IndicatorFragment: Fragment(), IndicatorContract.View {
    private lateinit var binding: DashboardIndikatorFragmentBinding
    private val presenter: IndicatorContract.Presenter =
        IndicatorPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dashboard_indikator_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.showIndicators()
    }

    override fun showIndicators(indicators: Array<Indikator>){
        binding.indicatorsRv.apply {
            setHasFixedSize(true)
            adapter =
                IndicatorItemRecyclerAdapter(
                    indicators
                )
        }
    }

    override fun showErrorMessage(message: String) {
        showToast(message)
    }
}