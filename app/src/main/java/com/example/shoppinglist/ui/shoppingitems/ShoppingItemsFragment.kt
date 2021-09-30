package com.example.shoppinglist.ui.shoppingitems

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingItemsBinding
import com.example.shoppinglist.ui.shoppingitems.adapters.ShoppingItemsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingItemsFragment : Fragment() {

    private val viewModel: ShoppingItemsViewModel by viewModel()
    private lateinit var binding: FragmentShoppingItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)

        setupListId()
        setupRecycler()
        setupObservers()

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentShoppingItemsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupListId() {
        viewModel.listId.value = ShoppingItemsFragmentArgs.fromBundle(requireArguments()).argListId
    }

    private fun setupRecycler() {
        binding.shoppingItemsRecycler.adapter =
            ShoppingItemsListAdapter(ShoppingItemsListAdapter.ShoppingItemListener { shoppingItem ->
                navToItemEdit(shoppingItem.id, getString(R.string.edit_list_item))
            })
    }

    private fun setupObservers() {
        viewModel.navigateToItemEdit.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                if (navigate) {
                    navToItemEdit(0, getString(R.string.add_item_title))
                    viewModel.navigateToItemEditCompleted()
                }
            }
        }
    }

    private fun navToItemEdit(listId: Long, destinationLabel: String) {
        findNavController().navigate(
            ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToItemEditFragment(
                listId, destinationLabel
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToMainFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}