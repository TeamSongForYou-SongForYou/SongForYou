package com.hanyeop.songforyou.view.main.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.*
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentHomeBinding
import com.hanyeop.songforyou.utils.Common
import com.hanyeop.songforyou.utils.ResultType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "test5"
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var recommendMyListAdapter: RecommendMyListAdapter
    private lateinit var recommendMyRecordAdapter: RecommendMyRecordAdapter

    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    private lateinit var mLocationRequest: LocationRequest// 위치 정보 요청의 매개변수를 저장하는

    override fun init() {
        recommendMyListAdapter = RecommendMyListAdapter()
        recommendMyRecordAdapter = RecommendMyRecordAdapter()

        binding.apply {
            recyclerMyList.adapter = recommendMyListAdapter
            recyclerMyRecord.adapter = recommendMyRecordAdapter
        }

        startLocationUpdates()

        initViewModelCallBack()

        homeViewModel.getIbRecommendMyList()

        homeViewModel.getIbRecommendMyRecord()
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            homeViewModel.weatherResponse.collectLatest {
                Log.d(TAG, "initViewModelCallBack: $it")
                if(it is ResultType.Success){
                    if(it.data.response.body != null) {
                        for (i in it.data.response.body.items.item) {
                            if (i.category == "T1H") {
                                // 온도가 이상하게 받아질 때 종료시킴.
                                if(i.obsrValue > 40 || i.obsrValue < 0){
                                    Log.d(TAG, "initViewModelCallBack: $i")
                                    return@collectLatest
                                }
                            }
                            if (i.category == "PTY") {
                                /** 날씨 정보
                                 * 날씨 : 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
                                 */
                                Log.d(TAG, "initViewModelCallBack: ${i.obsrValue.toInt()}")
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            homeViewModel.recommendMyList.collectLatest {
                recommendMyListAdapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            homeViewModel.recommendMyRecord.collectLatest {
                recommendMyRecordAdapter.submitList(it)
            }
        }
    }

    private fun initWeather(lat: Double, long: Double){
        val cal = Calendar.getInstance()
        var baseDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        val baseTime = Common().getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && baseTime == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            baseDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }

        val curPoint = Common().dfs_xy_conv(lat, long)

        homeViewModel.getWeather("JSON",14,1,
            baseDate,baseTime,curPoint.x,curPoint.y)

        Log.d(TAG, "onLocationResult: $baseTime $baseDate")
        Log.d(TAG, "onLocationResult: $curPoint")
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        mLocationRequest =  LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        //FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper())
    }

    // 시스템으로 부터 위치 정보를 콜백으로 받음
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        mLastLocation = location
        Log.d(TAG, "위도 : " + mLastLocation.latitude) // 갱신 된 위도")
        Log.d(TAG, "경도 : " + mLastLocation.longitude)  // 갱신 된 위도")

        initWeather(mLastLocation.latitude, mLastLocation.longitude)
    }
}