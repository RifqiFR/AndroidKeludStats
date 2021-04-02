package com.keludstats.modul.dashboard.fragment.infografis

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keludstats.R
import com.keludstats.databinding.DashboardInfografisFragmentBinding
import com.keludstats.shared.model.Infografi

class InfografisFragment: Fragment(), InfografisContract.View {
    private lateinit var binding: DashboardInfografisFragmentBinding
    private val presenter: InfografisContract.Presenter = InfografisPresenter(this)

    companion object {
        private const val MAX_SHOWN_PICTURE = 5 //only show max 5 infografis' picture
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_infografis_fragment,
                container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.showInfografis()
    }

    override fun showInfografisPicture(infografis: Array<Infografi>) {
        var infografisCopy = infografis

        // create new array with size = Max shown if returned array size > max
        if(infografis.size > MAX_SHOWN_PICTURE){
            infografisCopy = infografis.toList()
                                .subList(0, MAX_SHOWN_PICTURE).toTypedArray()
        }

        binding.infografiPicturesRv.apply {
            adapter = InfografisRecyclerAdapter(infografisCopy)

            setHasFixedSize(true)
        }
    }

    override fun showInfografi(infografi: Infografi) {
        binding.infografi = infografi
        binding.executePendingBindings()
    }
}