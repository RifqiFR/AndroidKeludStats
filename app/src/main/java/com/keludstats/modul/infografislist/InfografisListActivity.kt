package com.keludstats.modul.infografislist

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.keludstats.R
import com.keludstats.shared.model.Infografi
import com.keludstats.shared.singletondata.IsLoggedIn

class InfografisListActivity
    : AppCompatActivity(R.layout.infografis_activity), InfografisListContract.View {
    private val presenter : InfografisListContract.Presenter = InfografisListPresenter(this)
    private lateinit var addInfografisButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addInfografisButton = findViewById(R.id.tambahInfografisButton)
        initializeOnClicks()
        presenter.showInfografis()
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
    }

    override fun showInfografis(infografis: ArrayList<Infografi>) {
        findViewById<RecyclerView>(R.id.infografisListRv).apply {
            adapter = InfografisListAdapter(infografis)
        }
    }
}