package com.example.shoppinglist.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListsBinding
import com.example.shoppinglist.ui.main.MainFragmentDirections
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
        setupLists()
        setupBinding(inflater)

        setupRecycler()
        setupFab()
        setupObservers()

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
            })
    }

    private fun setupFab() {
        binding.addListFab.visibility = when (position) {
            0 -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun setupObservers() {
        viewModel.navigateToListEdit.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                if (navigate) {
                    navToListEdit(0, getString(R.string.add_list_title))
                    viewModel.navigateToListEditCompleted()
                }
            }
        }
    }

    private fun navToListEdit(listId: Long, destinationLabel: String) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToListEditFragment(
                listId, destinationLabel
            )
        )
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