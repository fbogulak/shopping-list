package com.example.shoppinglist.ui.itemedit;

import static org.koin.java.KoinJavaComponent.get;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shoppinglist.databinding.FragmentItemEditBinding;
import com.example.shoppinglist.ui.base.BaseFragment;
import com.example.shoppinglist.ui.base.BaseViewModel;

public class ItemEditFragment extends BaseFragment {

    ItemEditViewModel viewModel = get(ItemEditViewModel.class);
    private FragmentItemEditBinding binding;

    @NonNull
    @Override
    public BaseViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setupBinding(inflater);
        setupShoppingItem();

        return binding.getRoot();
    }

    private void setupBinding(LayoutInflater inflater) {
        binding = FragmentItemEditBinding.inflate(inflater);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }

    private void setupShoppingItem() {
        long itemId = ItemEditFragmentArgs.fromBundle(requireArguments()).getArgItemId();
        long listId = ItemEditFragmentArgs.fromBundle(requireArguments()).getArgListId();
        if (itemId > 0) {
            viewModel.getShoppingItem().setId(itemId);
            viewModel.getItemFromDb();
        }
        viewModel.getShoppingItem().setListId(listId);
    }
}
