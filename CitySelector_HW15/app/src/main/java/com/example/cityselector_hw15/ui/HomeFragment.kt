package com.example.cityselector_hw15.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cityselector_hw15.R
import com.example.cityselector_hw15.databinding.HomeFragmentBinding

class HomeFragment: Fragment(R.layout.home_fragment),ClickHandler {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    private val navController by lazy { findNavController() }
//    private val viewModel: ViewModelHome by viewModels()
    private val viewModel: ViewModelHome by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeFragmentBinding.bind(view)


        binding.btnNext.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_secondFragment)
//            parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_container, SecondFragment())
//                .addToBackStack("main").setReorderingAllowed(true).commit()
        }
        viewModel.cityListLiveData.value?.also {
            binding.rvHome.adapter = HomeAdapter(it, this)
        }
//        viewModel.cityListLiveData.observe(viewLifecycleOwner, Observer {
//            binding.rvHome.adapter = HomeAdapter(it, this)
//        })

    }

    override fun click(position: Int, selected: Boolean) {
        viewModel.selectCity(position, selected)
        binding.rvHome.adapter?.notifyItemChanged(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvHome.adapter = null
        _binding = null
    }

}