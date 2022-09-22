package com.hanyeop.songforyou.view.main.karaoke

import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.activity.viewModels
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityKaraokeBinding
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class KaraokeActivity : BaseActivity<ActivityKaraokeBinding>(R.layout.activity_karaoke) {

    private val karaokeViewModel by viewModels<KaraokeViewModel>()

    override fun init() {
        startTracking()


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
}