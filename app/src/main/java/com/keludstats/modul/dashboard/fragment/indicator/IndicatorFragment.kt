package com.keludstats.modul.dashboard.fragment.indicator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keludstats.R
import com.keludstats.databinding.DashboardIndikatorFragmentBinding
import com.keludstats.modul.editindicator.EditIndicatorDialog
import com.keludstats.modul.editsubindicator.EditSubindicatorContract
import com.keludstats.modul.editsubindicator.EditSubindicatorDialog
import com.keludstats.modul.newindicator.NewIndicatorDialog
import com.keludstats.modul.newsubindicator.NewSubindicatorDialog
import com.keludstats.modul.table.TableActivity
import com.keludstats.modul.years.YearsActivity
import com.keludstats.shared.model.Indikator
import com.keludstats.shared.model.Subindicator
import com.keludstats.shared.singletondata.IsLoggedIn.isLoggedIn
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast

class IndicatorFragment: Fragment(), IndicatorContract.View, NewIndicatorDialog.CreateIndicator
        , NewSubindicatorDialog.CreateSubindicatorContract, EditIndicatorDialog.EditIndicator
        , EditSubindicatorDialog.EditSubindicator
{
    private lateinit var binding: DashboardIndikatorFragmentBinding
    private val presenter: IndicatorContract.Presenter = IndicatorPresenter(this)
    private var createNewIndicatorDialog: NewIndicatorDialog? = null
    private var newSubindicatorDialog: NewSubindicatorDialog? = null
    private var editIndicatorDialog: EditIndicatorDialog? = null
    private var editSubindicatorDialog: EditSubindicatorDialog? = null

    companion object {
        private const val CREATE_INDICATOR_REQ_CODE = 200
        private const val EDIT_INDICATOR_REQ_CODE = 201
        private const val EDIT_SUBINDICATOR_REQ_CODE = 202
    }

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

        initializeOnClicks()
        return binding.root
    }

    private fun initializeOnClicks() {
        binding.createNewIndicatorBtn.setOnClickListener {
            if(createNewIndicatorDialog == null)
                createNewIndicatorDialog = NewIndicatorDialog().also {
                    it.setTargetFragment(this, CREATE_INDICATOR_REQ_CODE)
                }

            fragmentManager?.let {
                createNewIndicatorDialog?.show(it, "NewIndicatorDialog")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.showIndicators()
    }

    override fun onResume() {
        super.onResume()
        // check if user is logged in
        binding.createNewIndicatorBtn.visibility =
                if(isLoggedIn)
                    View.VISIBLE
                else
                    View.GONE
    }

    override fun showIndicators(indicators: ArrayList<Indikator>){
        binding.indicatorsRv.apply {
            adapter =
                IndicatorItemRecyclerAdapter(
                    indicators, this@IndicatorFragment
                )
        }
    }

    override fun showErrorMessage(message: String) {
        showToast(message)
    }

    override fun redirectToYearsPage(subIndicatorId: Int) {
        startActivity(
            Intent(view?.context, YearsActivity::class.java)
                .putExtra(TableActivity.TABLE_SUBINDICATOR_BUNDLE_KEY, subIndicatorId)
        )
    }

    override fun addIndicator(indicator: Indikator) {
        //refresh list
        presenter.showIndicators()
    }

    override fun addSubindicatorToList(newSubindicator: Subindicator) {
        //refresh list
        (binding.indicatorsRv.adapter as IndicatorItemRecyclerAdapter)
                .refreshSubindicators(newSubindicator.indicatorId)
    }

    override fun showNewSubIndicatorDialog(indikatorId: Int) {
        if(newSubindicatorDialog == null) {
            newSubindicatorDialog = NewSubindicatorDialog()
            newSubindicatorDialog?.setTargetFragment(this, CREATE_INDICATOR_REQ_CODE)
        }

        newSubindicatorDialog?.indicatorId = indikatorId

        fragmentManager?.let {
            newSubindicatorDialog?.show(it, TAG)
        }
    }

    override fun showEditIndicatorDialog(indicator: Indikator) {
        if(editIndicatorDialog == null){
            editIndicatorDialog = EditIndicatorDialog(indicator).also {
                it.setTargetFragment(this, EDIT_INDICATOR_REQ_CODE)
            }
        }

        editIndicatorDialog?.let {
            it.indikator = indicator

            fragmentManager?.let { it1 ->
                it.show(it1, TAG)
            }
        }
    }

    override fun showEditSubindicatorDialog(subindicator: Subindicator) {
        if(editSubindicatorDialog == null){
            editSubindicatorDialog = EditSubindicatorDialog(subindicator).also {
                it.setTargetFragment(this, EDIT_SUBINDICATOR_REQ_CODE)
            }
        }

        editSubindicatorDialog?.let {
            it.subindicator = subindicator

            fragmentManager?.let { it1 ->
                it.show(it1, TAG)
            }
        }
    }

    override fun updateIndicatorInList(indicator: Indikator) {
        binding.indicatorsRv.adapter?.let {
            (it as IndicatorItemRecyclerAdapter).refreshIndicator(indicator)
        }
    }

    override fun updateOldSubindicator(subindicator: Subindicator) {
        //refresh list
        (binding.indicatorsRv.adapter as IndicatorItemRecyclerAdapter)
                .refreshSubindicators(subindicator.indicatorId)
    }
}