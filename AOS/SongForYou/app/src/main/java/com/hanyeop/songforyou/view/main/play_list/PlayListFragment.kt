package com.hanyeop.songforyou.view.main.play_list

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentPlayListBinding
import com.hanyeop.songforyou.view.main.play_list.tab.record.PlayListRecordTabFragment
import com.hanyeop.songforyou.view.main.play_list.tab.saved.PlayListSavedTabFragment

class PlayListFragment : BaseFragment<FragmentPlayListBinding>(R.layout.fragment_play_list) {

    private lateinit var playListSavedTabFragment: PlayListSavedTabFragment
    private lateinit var playListRecordTabFragment: PlayListRecordTabFragment

    override fun init() {
        initTabLayout()
    }

    // 각 탭에 들어갈 프레그먼트 객체화
    private fun initTabLayout(){
        playListSavedTabFragment = PlayListSavedTabFragment()
        playListRecordTabFragment = PlayListRecordTabFragment()

        childFragmentManager.beginTransaction().replace(R.id.frame_layout, playListSavedTabFragment).commit()

        binding.tabLayoutHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position) {
                    0 -> replaceView(playListSavedTabFragment)
                    1 -> replaceView(playListRecordTabFragment)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // 화면 변경
    private fun replaceView(tab : Fragment){
        var selectedFragment = tab
        selectedFragment.let {
            childFragmentManager.beginTransaction().replace(R.id.frame_layout, it).commit()
        }
    }
}