package com.mamasodikov.contactgo.main_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mamasodikov.contactgo.R
import com.mamasodikov.contactgo.adapters.VPAdapterPub
import com.mamasodikov.contactgo.databinding.FragmentPublicCardsBinding

class PublicCards:Fragment(R.layout.fragment_public_cards) {


    lateinit var binding:FragmentPublicCardsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPublicCardsBinding.inflate(inflater, container, false)
        val view = binding.root

        val manager: FragmentManager = childFragmentManager
        val adapter: VPAdapterPub = VPAdapterPub(manager, lifecycle)

        binding.viewPagerPub.adapter = adapter

        binding.tabLayoutPub.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPagerPub.setCurrentItem(tab.position)
            }


            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.viewPagerPub.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayoutPub.selectTab(binding.tabLayoutPub.getTabAt(position))
                super.onPageSelected(position)
            }
        })

        binding.viewPagerPub.setSaveEnabled(false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //binding.RecViewFirst.adapter = MenuAdapter(menus)

    }
}