package com.keludstats.modul.dashboard.fragment.infografis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keludstats.R
import com.keludstats.databinding.DashboardInfografisFragmentBinding
import com.keludstats.modul.detailinfografi.DetailInfografiActivity
import com.keludstats.shared.model.Infografi

class InfografisFragment: Fragment(), InfografisContract.View {
    private lateinit var binding: DashboardInfografisFragmentBinding
    private val presenter: InfografisContract.Presenter = InfografisPresenter(this)

    companion object {
        private const val MAX_SHOWN_PICTURE = 5 //only show max 5 infografis' picture
        private const val DETAIL_INFOGRAFI_REQ_CODE = 100
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            DETAIL_INFOGRAFI_REQ_CODE -> {
                if(resultCode == Activity.RESULT_OK)
                    presenter.showInfografis() //refresh list
            }
        }
    }

    override fun showInfografisPicture(infografis: Array<Infografi>) {
        var infografisCopy = infografis

        // create new array with size = Max shown if returned array size > max
        if(infografis.size > MAX_SHOWN_PICTURE){
            infografisCopy = infografis.toList()
                                .subList(0, MAX_SHOWN_PICTURE).toTypedArray()
        }

        binding.infografiPicturesRv.apply {
            adapter = InfografisRecyclerAdapter(infografisCopy, this@InfografisFragment)

            setHasFixedSize(true)
        }
    }

    override fun showInfografi(infografi: Infografi) {
        binding.infografi = infografi
        binding.dayDate = infografi.dayDate
        binding.month = infografi.monthDate.substring(0, 3)
        binding.executePendingBindings()
    }

    override fun redirectToDetailInfografi(infografi: Infografi) {
        context?.let {
            startActivityForResult(
                Intent(it, DetailInfografiActivity::class.java)
                        .putExtra(DetailInfografiActivity.DETAIL_INFOGRAFI_BUNDLE_KEY, infografi)
                , DETAIL_INFOGRAFI_REQ_CODE
            )
        }
    }
}