package com.example.weatherapp.presentation.weatherScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentViewPagerBinding
import com.example.weatherapp.presentation.addCityScreen.CityListViewModel
import com.example.weatherapp.utils.LiveDataUtils.liveDataOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {


    private val viewModel by viewModel<CityListViewModel>()
    private val binding by viewBinding(FragmentViewPagerBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager,viewLifecycleOwner.lifecycle)
        binding.pager.adapter = adapter

        viewModel.favoriteList.observe(liveDataOwner){
            val currentPositon = binding.pager.currentItem
            adapter.update(it)
            binding.pager.adapter = null
            binding.pager.adapter = adapter
            binding.pager.setOffscreenPageLimit(adapter.itemCount)
            binding.pager.setCurrentItem(currentPositon - COUNT_STEPS_TO_THE_PREVIOUS_FRAGMENT,false )
            binding.indicator.setViewPager(binding.pager)
        }
    }

    companion object{
        private const val COUNT_STEPS_TO_THE_PREVIOUS_FRAGMENT = 1
    }
}