package com.hanyeop.songforyou.view.main.other

import androidx.navigation.fragment.findNavController
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentShowOthersBinding
import com.hanyeop.songforyou.model.dto.OthersDto
import com.hanyeop.songforyou.view.main.other.detail.OtherDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowOthersFragment : BaseFragment<FragmentShowOthersBinding>(R.layout.fragment_show_others) {

    override fun init() {
        initClickListener()
    }

    private fun initClickListener(){
        binding.apply {
            layoutOne.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("여름",1,"word"), "시원함이 느껴지는 여름 노래"
                )
                findNavController().navigate(action)
            }
            layoutTwo.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("이별",2,"word"), "이별 후 불뤄줘야 하는 노래"
                )
                findNavController().navigate(action)
            }
            layoutThree.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("고백",3,"word"), "설레는 마음을 표현할 노래"
                )
                findNavController().navigate(action)
            }
            layoutFour.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("떼창",4,"none"), "다같이 신나게 부르는 노래"
                )
                findNavController().navigate(action)
            }
            layoutFive.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("듀엣",5,"none"), "연인과 부르기 좋은 노래"
                )
                findNavController().navigate(action)
            }
        }
    }
}