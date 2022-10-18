package com.example.dailybriefing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailybriefing.databinding.FragmentInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BriefingFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {
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
        binding.recyclerview.layoutManager = LinearLayoutManager(
            this.context, RecyclerView.VERTICAL,
            false
        )
        val topItemListArray = ArrayList<AdapterItem>()

        val adapterItem = AdapterItem()
        adapterItem.tag = "bus"
        adapterItem.title = "7100"
        adapterItem.contents = "8분뒤 도착"

        topItemListArray.add(adapterItem)

        val adapterItem2 = AdapterItem()
        adapterItem2.tag = "weather"
        adapterItem2.title = "김포시"
        adapterItem2.contents = "맑음"
        topItemListArray.add(adapterItem2)

        val adapter = InfoRecyclerView(topItemListArray)
        binding.recyclerview.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BriefingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}