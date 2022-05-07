package com.example.cityselector_hw15.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cityselector_hw15.R
import com.example.cityselector_hw15.databinding.SecondFragmentBinding

class SecondFragment:Fragment(R.layout.second_fragment) {

    private var _binding: SecondFragmentBinding? = null
    private val binding: SecondFragmentBinding get() = _binding!!

//    private val viewModel: ViewModelHome by viewModels()
    private val viewModel: ViewModelHome by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SecondFragmentBinding.bind(view)

        viewModel.favoriteListLiveData.value?.also {
            binding.rvSecond.adapter = SecondAdapter(it)
        }
//        viewModel.cityListLiveData.observe(viewLifecycleOwner, Observer {
//            binding.rvSecond.adapter = SecondAdapter(it)
//        })

        val swipeGesture = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    viewModel.removeCityItem(viewHolder.bindingAdapterPosition)
                }
                binding.rvSecond.adapter?.notifyItemRemoved(viewHolder.bindingAdapterPosition)
            }
        }
        ItemTouchHelper(swipeGesture).attachToRecyclerView(binding.rvSecond)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvSecond.adapter = null
        _binding = null
    }

}