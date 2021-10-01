package com.example.shoppinglist.ui.listedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
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

        setupShoppingList()
        setupListeners()

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentListEditBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupShoppingList() {
        val listId = ListEditFragmentArgs.fromBundle(requireArguments()).argListId
        if (listId > 0) {
            viewModel.shoppingList.id = listId
            viewModel.getListNameFromDb()
        }
    }

    private fun setupListeners() {
        binding.saveListFab.setOnClickListener {
            if (binding.nameEdit.text.toString().isEmpty()) {
                binding.nameTextField.error = getString(R.string.list_must_have_name)
            } else {
                viewModel.saveShoppingList()
            }
        }
        binding.nameEdit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.nameTextField.error = null
        }
        binding.nameEdit.setOnClickListener {
            binding.nameTextField.error = null
        }
    }
}