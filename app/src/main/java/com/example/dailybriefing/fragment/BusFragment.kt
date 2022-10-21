package com.example.dailybriefing.fragment

import android.os.Bundle
import android.view.View
import com.example.dailybriefing.databinding.FragmentBusBinding
import com.example.bushelp.HttpSocket

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BusFragment : BaseFragment<FragmentBusBinding>(FragmentBusBinding::inflate) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HttpSocket().getBusInfo("전통시장"){
            binding.textView.text = it
        }
    }

}