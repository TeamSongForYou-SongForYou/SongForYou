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
                    OthersDto("여름",1,"word")
                )
                findNavController().navigate(action)
            }
            layoutTwo.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("이별",2,"word")
                )
                findNavController().navigate(action)
            }
            layoutThree.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("고백",3,"word")
                )
                findNavController().navigate(action)
            }
            layoutFour.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("떼창",4,"none")
                )
                findNavController().navigate(action)
            }
            layoutFive.setOnClickListener {
                val action = ShowOthersFragmentDirections.actionShowOthersFragmentToOtherDetailFragment(
                    OthersDto("듀엣",5,"none")
                )
                findNavController().navigate(action)
            }
        }
    }

//    private fun initTagView() {
//        binding.tagView.setTextPaint(
//            TextPaint().apply {
//                isAntiAlias = true
//                textSize = resources.getDimension(R.dimen.tag_text_size)
//                color = Color.DKGRAY
//            }
//        )
//        val loremSize = LoremIpsum.list.size
//        (0..40).map {
//            TextTagItem(text = LoremIpsum.list[Random.nextInt(loremSize)])
//        }.toList().let {
//            binding.tagView.addTagList(it)
//        }
//        binding.tagView.setRadius(3f)
//        binding.tagView.setOnTagTapListener(object : OnTagTapListener {
//            override fun onTap(tagItem: TagItem) {
//                Toast.makeText(
//                    requireContext(),
//                    "On tap: ${(tagItem as TextTagItem).text}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })
//    }
}