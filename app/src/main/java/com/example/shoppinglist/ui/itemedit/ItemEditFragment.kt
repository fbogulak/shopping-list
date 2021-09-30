package com.example.shoppinglist.ui.itemedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.databinding.FragmentItemEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemEditFragment : Fragment() {

    private val viewModel: ItemEditViewModel by viewModel()
    private lateinit var binding: FragmentItemEditBinding

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
        binding = FragmentItemEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupObservers() {
        viewModel.navigateToShoppingItems.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                if (navigate) {
                    navToShoppingItems()
                    viewModel.navigateToShoppingItemsCompleted()
                }
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

    private fun navToShoppingItems() {
        findNavController().popBackStack()
    }
}