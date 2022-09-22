package com.hanyeop.songforyou.view.main.karaoke

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityKaraokeBinding
import com.hanyeop.songforyou.model.response.Place
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KaraokeActivity : BaseActivity<ActivityKaraokeBinding>(R.layout.activity_karaoke) {

    private val karaokeViewModel by viewModels<KaraokeViewModel>()

    private val eventListener = MarkerEventListener()   // 마커 클릭 이벤트 리스너

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

        karaokeViewModel.getSearchKeyword("노래방", uLongitude.toString(), uLatitude.toString())

        binding.layoutMap.setPOIItemEventListener(eventListener)
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
                    userObject = document
                    mapPoint = MapPoint.mapPointWithGeoCoord(document.y.toDouble(),
                        document.x.toDouble())
                    markerType = MapPOIItem.MarkerType.BluePin
                    selectedMarkerType = MapPOIItem.MarkerType.RedPin
                }
                binding.layoutMap.addPOIItem(point)
            }
        }
    }

    // 마커 클릭 이벤트 리스너
    inner class MarkerEventListener: MapView.POIItemEventListener {
        override fun onPOIItemSelected(mapView: MapView?, poiItem: MapPOIItem?) {
            // 마커 클릭 시
            val curItem = poiItem!!.userObject as Place
            binding.apply {
                tvKaraokeName.text = curItem.place_name
                tvAddress.text = curItem.road_address_name
                tvNumber.text = curItem.address_name
                tvDistance.text = "${curItem.distance}m"
            }
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?) {
            // 말풍선 클릭 시 (Deprecated)
        }

        override fun onCalloutBalloonOfPOIItemTouched(mapView: MapView?, poiItem: MapPOIItem?, buttonType: MapPOIItem.CalloutBalloonButtonType?) {
            // 말풍선 클릭 시
        }

        override fun onDraggablePOIItemMoved(mapView: MapView?, poiItem: MapPOIItem?, mapPoint: MapPoint?) {
            // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
        }
    }
}