package com.example.shoppinglist.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppinglist.ui.shoppinglists.ShoppingListsFragment

class ViewPagerFragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return ShoppingListsFragment.newInstance(position)
    }


}