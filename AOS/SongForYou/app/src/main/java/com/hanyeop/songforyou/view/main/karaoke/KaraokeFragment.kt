package com.hanyeop.songforyou.view.main.karaoke

import androidx.fragment.app.viewModels
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentKaraokeBinding
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KaraokeFragment : BaseFragment<FragmentKaraokeBinding>(R.layout.fragment_karaoke) {

    private val karaokeViewModel by viewModels<KaraokeViewModel>()

    override fun init() {
        initMap()

        karaokeViewModel.getSearchKeyword("노래방")
    }

    private fun initMap(){
        val mapView = MapView(requireActivity())
        binding.layoutMap.addView(mapView)
    }
}