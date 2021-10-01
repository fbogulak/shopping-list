package com.example.shoppinglist.ui.itemedit;

import static org.koin.java.KoinJavaComponent.get;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.shoppinglist.R;
import com.example.shoppinglist.databinding.FragmentItemEditBinding;
import com.example.shoppinglist.ui.base.BaseFragment;
import com.example.shoppinglist.ui.base.BaseViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

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
        setupListeners();

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

    private void setupListeners() {
        binding.saveItemFab.setOnClickListener(v -> {
            if (Objects.requireNonNull(binding.nameEdit.getText()).toString().isEmpty()) {
                binding.nameTextField.setError(getString(R.string.item_must_have_name));
            } else if (Objects.requireNonNull(binding.quantityEdit.getText()).toString().isEmpty()) {
                binding.quantityTextField.setError(getString(R.string.item_must_have_quantity));
            } else {
                viewModel.saveShoppingItem();
            }
        });
        binding.nameEdit.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) binding.nameTextField.setError(null);
        });
        binding.nameEdit.setOnClickListener(v -> binding.nameTextField.setError(null));
        binding.quantityEdit.setOnFocusChangeListener((View.OnFocusChangeListener) (v, hasFocus) -> {
            if (hasFocus) binding.quantityTextField.setError(null);
        });
        binding.quantityEdit.setOnClickListener(v -> binding.quantityTextField.setError(null));
    }
}
