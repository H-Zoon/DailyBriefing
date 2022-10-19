package com.example.dailybriefing.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dailybriefing.R

class BriefingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_briefing, container, false)

        val frag1= BusFragment()
        val frag2= WeatherFragment()

        val pageAdapter = ViewPagerAdapter(requireActivity())
        pageAdapter.addFragment(frag1)
        pageAdapter.addFragment(frag2)

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = pageAdapter

        return view
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

        var fragments : ArrayList<Fragment> = ArrayList()

        override fun getItemCount(): Int {
            Log.d("size", "$fragments.size")
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            Log.d("call", "call")
            notifyItemInserted(fragments.size-1)
        }

        fun removeFragment() {
            fragments.removeLast()
            notifyItemRemoved(fragments.size)
        }
    }
}