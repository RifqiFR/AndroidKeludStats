package com.keludstats.modul.infografislist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.keludstats.R
import com.keludstats.modul.createinfografi.CreateInfografiActivity
import com.keludstats.modul.detailinfografi.DetailInfografiActivity
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.singletondata.IsLoggedIn

class InfografisListActivity
    : AppCompatActivity(R.layout.infografis_activity), InfografisListContract.View {
    private val presenter : InfografisListContract.Presenter = InfografisListPresenter(this)
    private lateinit var addInfografisButton: ImageButton

    companion object {
        private const val DETAIL_INFOGRAFI_REQ_CODE = 300
        private const val CREATE_INFOGRAFI_REQ_CODE = 201
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addInfografisButton = findViewById(R.id.tambahInfografisButton)
        initializeOnClicks()
        presenter.showInfografis()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            DETAIL_INFOGRAFI_REQ_CODE, CREATE_INFOGRAFI_REQ_CODE -> {
                // if an infografi deleted, updated, or created refresh list
                if(resultCode == Activity.RESULT_OK)
                    presenter.showInfografis()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        addInfografisButton.visibility =
            if(IsLoggedIn.isLoggedIn)
                 View.VISIBLE
            else
                View.GONE
    }

    private fun initializeOnClicks() {
        addInfografisButton.setOnClickListener {
            redirectToNewInfografis()
        }
    }

    override fun redirectToNewInfografis() {
        startActivityForResult(
                Intent(this, CreateInfografiActivity::class.java)
                , CREATE_INFOGRAFI_REQ_CODE
        )
    }

    override fun redirectToDetailInfografis(infografi: Infografi) {
        Intent(this, DetailInfografiActivity::class.java).also {
            it.putExtra(DetailInfografiActivity.DETAIL_INFOGRAFI_BUNDLE_KEY, infografi)
            startActivityForResult(it, DETAIL_INFOGRAFI_REQ_CODE)
        }
    }

    override fun showInfografis(infografis: ArrayList<Infografi>) {
        findViewById<RecyclerView>(R.id.infografisListRv).let {
            it.adapter = InfografisListAdapter(infografis, this)
        }
    }
}