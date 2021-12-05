package com.mamasodikov.contactgo.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mamasodikov.contactgo.R
import com.mamasodikov.contactgo.adapters.VPAdapter
import com.mamasodikov.contactgo.databinding.FragmentPrivateCardsBinding

class PrivateCards:Fragment(R.layout.fragment_private_cards) {

    lateinit var binding: FragmentPrivateCardsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPrivateCardsBinding.inflate(inflater, container, false)
        val view = binding.root

        val manager:FragmentManager = childFragmentManager
        val adapter: VPAdapter = VPAdapter(manager, lifecycle)

        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
               binding.viewPager.setCurrentItem(tab.position)
            }


            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                super.onPageSelected(position)
            }
        })

/*** Muhim ***/

        binding.viewPager.setSaveEnabled(false)
        return view
    }
}