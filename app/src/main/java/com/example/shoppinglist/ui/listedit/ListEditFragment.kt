package com.example.shoppinglist.ui.listedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.databinding.FragmentListEditBinding
import com.example.shoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEditFragment : BaseFragment() {

    override val viewModel: ListEditViewModel by viewModel()
    private lateinit var binding: FragmentListEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentListEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}