package com.example.shoppinglist.ui.listedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppinglist.databinding.FragmentListEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEditFragment : Fragment() {

    private val viewModel: ListEditViewModel by viewModel()
    private lateinit var binding: FragmentListEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}