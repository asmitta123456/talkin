package com.asmitta.talkin.view.searchUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.asmitta.talkin.R
import com.asmitta.talkin.databinding.FragmentSearchBinding
import com.asmitta.talkin.utils.states.FragmentState
import com.asmitta.talkin.utils.states.NetworkState
import com.asmitta.talkin.view.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SearchFragment : Fragment() {

    private val firebaseVm: FirebaseViewModel by viewModels({ requireActivity() })
    private lateinit var searchView: SearchView
    private lateinit var searchItem: MenuItem
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(context))
        binding.lifecycleOwner = this
        binding.firebaseVm = firebaseVm

        binding.recyclerView.itemAnimator = null
        binding.recyclerView.adapter = SearchAdapter(SearchAdapter.OnClickListener { user ->
            firebaseVm.setReceiver(user)
            firebaseVm.setFragmentState(FragmentState.CHAT)
            onSearchViewClose()
        })

        firebaseVm.userSearchStatus.observe(viewLifecycleOwner, Observer {
            binding.searchView.visibility = View.VISIBLE
            when (it) {
                NetworkState.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.info.visibility = View.GONE
                }
                NetworkState.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.info.visibility = View.GONE
                }
                NetworkState.FAILED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.info.visibility = View.VISIBLE
                }
                else -> {
                }
            }
        })

        binding.root.setOnClickListener {
            onSearchViewClose()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun onSearchViewClose() {
        binding.root.visibility = View.GONE
        if (this::searchItem.isInitialized) {
            searchItem.collapseActionView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        searchItem = menu.findItem(R.id.search) ?: return
        searchView = searchItem.actionView as SearchView
        setOnQueryTextFocusChangeListener()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setOnQueryTextFocusChangeListener() {
        searchView.setOnQueryTextFocusChangeListener { view, b ->
            if (b.not()) onSearchViewClose()
            else {
                binding.root.visibility = View.VISIBLE
                binding.searchView.visibility = View.GONE
            }
        }
    }
}