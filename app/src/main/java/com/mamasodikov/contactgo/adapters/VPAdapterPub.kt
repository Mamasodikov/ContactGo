package com.mamasodikov.contactgo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mamasodikov.contactgo.nested_fragments.Goverment
import com.mamasodikov.contactgo.nested_fragments.Social

class VPAdapterPub (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if(position==0)
        {
            return Social()
        }
        else return Goverment()
    }
}