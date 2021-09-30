package com.example.shoppinglist.ui.listedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        setupBinding(inflater)

        setupObservers()

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentListEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupObservers() {
        viewModel.navigateToShoppingItems.observe(viewLifecycleOwner) { shoppingList ->
            shoppingList?.let {
                navToShoppingItems(it.id, it.name)
                viewModel.navigateToShoppingItemsCompleted()

            }
        }
        viewModel.showToast.observe(viewLifecycleOwner) {
            it?.content?.let { content ->
                val message = when (content) {
                    is String -> content
                    is Int -> getString(content)
                    else -> return@let
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                viewModel.showToastCompleted()
            }
        }
    }

    private fun navToShoppingItems(listId: Long, destinationLabel: String) {
        findNavController().navigate(
            ListEditFragmentDirections.actionListEditFragmentToShoppingItemsFragment(
                listId,
                destinationLabel
            )
        )
    }
}