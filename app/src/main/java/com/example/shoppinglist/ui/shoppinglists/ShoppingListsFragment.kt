package com.example.shoppinglist.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListsBinding
import com.example.shoppinglist.ui.base.BaseFragment
import com.example.shoppinglist.ui.shoppinglists.adapters.ShoppingListsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_POSITION = "position"

class ShoppingListsFragment : BaseFragment() {
    private var position: Int? = null

    override val viewModel: ShoppingListsViewModel by viewModel()
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
        setupLists()
        setupBinding(inflater)

        setupRecycler()
        setupFab()
        setupListeners()

        return binding.root
    }

    private fun setupLists() {
        when (position) {
            0 -> viewModel.showCurrentLists()
            1 -> viewModel.showArchivedLists()
        }
    }

    private fun setupBinding(inflater: LayoutInflater) {
        binding = FragmentShoppingListsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupRecycler() {
        binding.shoppingListsRecycler.adapter =
            ShoppingListsListAdapter(ShoppingListsListAdapter.ShoppingListListener { shoppingList ->
                viewModel.navToShoppingItems(
                    shoppingList.id,
                    shoppingList.name
                )
            })
    }

    private fun setupFab() {
        binding.addListFab.visibility = when (position) {
            0 -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun setupListeners(){
        binding.addListFab.setOnClickListener {
            viewModel.navToListEdit(0, getString(R.string.add_list_title))
        }
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