package com.example.shoppinglist.ui.shoppingitems

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingItemsBinding
import com.example.shoppinglist.ui.base.BaseFragment
import com.example.shoppinglist.ui.shoppingitems.adapters.ShoppingItemsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingItemsFragment : BaseFragment() {

    override val viewModel: ShoppingItemsViewModel by viewModel()
    private lateinit var binding: FragmentShoppingItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupBinding(inflater)

        setupList()
        setupRecycler()
        setupListeners()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentShoppingItemsBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupList() {
        viewModel.listId.value = ShoppingItemsFragmentArgs.fromBundle(requireArguments()).argListId
        viewModel.listIsArchived =
            ShoppingItemsFragmentArgs.fromBundle(requireArguments()).argListIsArchived
    }

    private fun setupRecycler() {
        binding.shoppingItemsRecycler.adapter =
            ShoppingItemsListAdapter(ShoppingItemsListAdapter.ShoppingItemListener { shoppingItem ->
                viewModel.navToItemEdit(shoppingItem.id, getString(R.string.edit_list_item))
            })
    }

    private fun setupListeners() {
        binding.addItemFab.setOnClickListener {
            viewModel.navToItemEdit(0, getString(R.string.add_item_title))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shopping_items_overflow_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.archive_list).isVisible = !viewModel.listIsArchived
        menu.findItem(R.id.unarchive_list).isVisible = viewModel.listIsArchived
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_list -> {
                showDeleteConfirmationDialog()
                return true
            }
            R.id.rename_list -> {
                viewModel.navToListEdit(getString(R.string.rename_list_title))
                return true
            }
            R.id.archive_list -> {
                viewModel.setListArchived(true)
                return true
            }
            R.id.unarchive_list -> {
                viewModel.setListArchived(false)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setMessage(getString(R.string.delete_list_dialog_msg))
            setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.deleteShoppingList()
            }
            setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog?.dismiss()
            }
            show()
        }
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