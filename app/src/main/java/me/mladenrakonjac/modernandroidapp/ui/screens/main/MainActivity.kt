package me.mladenrakonjac.modernandroidapp.ui.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import me.mladenrakonjac.modernandroidapp.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import android.support.v7.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import me.mladenrakonjac.modernandroidapp.R
import me.mladenrakonjac.modernandroidapp.databinding.ActivityMainBinding
import me.mladenrakonjac.modernandroidapp.ui.rvadapters.RepositoryRecyclerViewAdapter
import me.mladenrakonjac.modernandroidapp.ui.uimodels.Repository
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this,
                Observer<ArrayList<Repository>> { it?.let { repositoryRecyclerViewAdapter.replaceData(it) } })

        viewModel.isLoading.observe(this) { isLoading ->
            binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.repositoryRv.visibility = if (isLoading) View.GONE else View.VISIBLE
            binding.refreshButton.isClickable = !isLoading
        }

        binding.refreshButton.setOnClickListener {
            viewModel.loadRepositories()
        }
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
