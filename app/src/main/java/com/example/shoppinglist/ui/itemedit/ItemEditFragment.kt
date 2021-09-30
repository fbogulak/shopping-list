package com.example.shoppinglist.ui.itemedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.databinding.FragmentItemEditBinding
import com.example.shoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemEditFragment : BaseFragment() {

    override val viewModel: ItemEditViewModel by viewModel()
    private lateinit var binding: FragmentItemEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentItemEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}