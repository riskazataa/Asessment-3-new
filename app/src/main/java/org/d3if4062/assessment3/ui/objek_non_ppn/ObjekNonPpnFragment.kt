package org.d3if4062.assessment3.ui.objek_non_ppn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4062.assessment3.databinding.FragmentObjekNonPpnBinding
import org.d3if4062.assessment3.network.ApiStatus

class ObjekNonPpnFragment: Fragment() {

    private val viewModel: ObjekNonPpnViewModel by lazy {
        ViewModelProvider(this).get(ObjekNonPpnViewModel::class.java)
    }
    private lateinit var binding: FragmentObjekNonPpnBinding
    private lateinit var myAdapter: ObjekNonPpnAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentObjekNonPpnBinding.inflate(layoutInflater, container, false)
        myAdapter = ObjekNonPpnAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }ApiStatus.SUCCESS -> {
            binding.progressBar.visibility = View.GONE
        }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}