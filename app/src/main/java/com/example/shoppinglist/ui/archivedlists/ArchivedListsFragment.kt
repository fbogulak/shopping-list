package com.example.shoppinglist.ui.archivedlists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentArchivedListsBinding

class ArchivedListsFragment : Fragment() {

    private lateinit var binding: FragmentArchivedListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArchivedListsBinding.inflate(inflater)

        return binding.root
    }
}