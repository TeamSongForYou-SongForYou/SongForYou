package com.hanyeop.songforyou.view.main.karaoke

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityKaraokeBinding
import com.hanyeop.songforyou.model.response.Place
import com.hanyeop.songforyou.model.response.ResultSearchKeyword
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KaraokeActivity : BaseActivity<ActivityKaraokeBinding>(R.layout.activity_karaoke) {

    private val karaokeViewModel by viewModels<KaraokeViewModel>()

    override fun init() {
        startTracking()

        initViewModelCallback()
    }

    private fun startTracking(){
        binding.layoutMap.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        val lm: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val userNowLocation: Location? = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        // 경도 , 위도
        val uLongitude = userNowLocation?.longitude
        val uLatitude = userNowLocation?.latitude
        val uNowPosition = MapPoint.mapPointWithGeoCoord(uLatitude!!, uLongitude!!)

        karaokeViewModel.getSearchKeyword("노래방", uLongitude.toString(), uLatitude.toString())

        // 현 위치에 마커 찍기
        val marker = MapPOIItem()
        marker.itemName = "현 위치"
        marker.mapPoint = uNowPosition
        marker.markerType = MapPOIItem.MarkerType.BluePin
        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
        binding.layoutMap.addPOIItem(marker)
    }

    private fun initViewModelCallback(){
        lifecycleScope.launch {
            karaokeViewModel.karaokeList.collectLatest {
                addItemsAndMarkers(it)
            }
        }
    }

    // 검색 결과 처리 함수
    private fun addItemsAndMarkers(searchResult: List<Place>) {
        if (searchResult.isNotEmpty()) {
            binding.layoutMap.removeAllPOIItems() // 지도의 마커 모두 제거
            for (document in searchResult) {
            // 지도에 마커 추가
                val point = MapPOIItem()
                point.apply {
                    itemName = document.place_name
                    mapPoint = MapPoint.mapPointWithGeoCoord(document.y.toDouble(),
                        document.x.toDouble())
                    markerType = MapPOIItem.MarkerType.BluePin
                    selectedMarkerType = MapPOIItem.MarkerType.RedPin
                }
                binding.layoutMap.addPOIItem(point)
            }
        }
    }
}