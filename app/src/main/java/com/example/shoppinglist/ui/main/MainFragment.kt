package com.example.shoppinglist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentMainBinding
import com.example.shoppinglist.ui.main.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        binding.viewPager.adapter = ViewPagerFragmentAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.shopping_lists)
                    tab.icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_list, context?.theme)
                }
                1 -> {
                    tab.text = getString(R.string.archived_lists)
                    tab.icon =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_archive,
                            context?.theme
                        )
                }
            }
        }.attach()

        return binding.root
    }
}