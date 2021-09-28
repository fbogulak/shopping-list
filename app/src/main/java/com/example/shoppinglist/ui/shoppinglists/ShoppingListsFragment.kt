package com.example.shoppinglist.ui.shoppinglists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListsBinding

class ShoppingListsFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingListsBinding.inflate(inflater)

        return binding.root
    }
}