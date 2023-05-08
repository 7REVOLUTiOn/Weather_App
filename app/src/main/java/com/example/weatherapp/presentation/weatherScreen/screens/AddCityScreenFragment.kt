package com.example.weatherapp.presentation.weatherScreen.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentAddCityScreenBinding

class AddCityScreenFragment : Fragment(R.layout.fragment_add_city_screen) {

    private val binding by viewBinding(FragmentAddCityScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_cityListFragment)
        }

    }
}