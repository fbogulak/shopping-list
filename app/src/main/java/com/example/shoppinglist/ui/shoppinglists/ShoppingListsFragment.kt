package com.example.shoppinglist.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shoppinglist.databinding.FragmentShoppingListsBinding
import com.example.shoppinglist.ui.shoppinglists.adapters.ShoppingListsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_POSITION = "position"

class ShoppingListsFragment : Fragment() {
    private var position: Int? = null

    private val viewModel: ShoppingListsViewModel by viewModel()
    private lateinit var binding: FragmentShoppingListsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        when (position) {
            0 -> viewModel.showCurrentLists()
            1 -> viewModel.showArchivedLists()
        }

        binding = FragmentShoppingListsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.shoppingListsRecycler.adapter =
            ShoppingListsListAdapter(ShoppingListsListAdapter.ShoppingListListener { shoppingList ->
                Toast.makeText(
                    requireContext(),
                    "${shoppingList.name} clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            ShoppingListsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}