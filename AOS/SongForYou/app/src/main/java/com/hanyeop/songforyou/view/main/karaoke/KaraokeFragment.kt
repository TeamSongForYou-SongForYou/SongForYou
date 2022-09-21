package com.hanyeop.songforyou.view.main.karaoke

import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentKaraokeBinding
import net.daum.mf.map.api.MapView

class KaraokeFragment : BaseFragment<FragmentKaraokeBinding>(R.layout.fragment_karaoke) {

    override fun init() {
        initMap()
    }

    private fun initMap(){
        val mapView = MapView(requireContext())
        binding.layoutMap.addView(mapView)
    }
}