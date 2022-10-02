package com.hanyeop.songforyou.view.main.other.detail

import android.content.Intent
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.charts.TagCloud
import com.anychart.scales.OrdinalColor
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentOtherDetailBinding
import com.hanyeop.songforyou.model.response.SongResponse
import com.hanyeop.songforyou.model.response.WordResponse
import com.hanyeop.songforyou.utils.SONG
import com.hanyeop.songforyou.view.audio.AudioRecordActivity
import com.hanyeop.songforyou.view.detail.SongDetailActivity
import com.hanyeop.songforyou.view.main.home.SongDetailListener
import com.hanyeop.songforyou.view.main.other.OtherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class OtherDetailFragment : BaseFragment<FragmentOtherDetailBinding>(R.layout.fragment_other_detail) {

    private val otherViewModel by viewModels<OtherViewModel>()
    private val args by navArgs<OtherDetailFragmentArgs>()
    private lateinit var adapter: OtherDetailAdapter

    private lateinit var tagCloud: TagCloud

    override fun init() {
        adapter = OtherDetailAdapter(songDetailListener)

        binding.apply {
            tvHeader.text = args.item.name
            recyclerRecommend.adapter = adapter
        }

        initChart()

        initViewModelCallBack()

        otherViewModel.getRecommendWithWord(args.item.num)
    }

    private fun initViewModelCallBack(){
        lifecycleScope.launch {
            otherViewModel.songList.collectLatest {
                Log.d("test5", "initViewModelCallBack: $it")
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            otherViewModel.wordList.collectLatest {
                Log.d("test5", "initViewModelCallBack: $it")
                initData(it)
            }
        }
    }

    private fun initChart(){
        binding.apply {
            anyChartView.setProgressBar(progressBar)
        }

        tagCloud = AnyChart.tagCloud()

        tagCloud.title("가사에서 많이 나오는 단어")

        val ordinalColor = OrdinalColor.instantiate()
        ordinalColor.colors(
            arrayOf(
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26"
            )
        )
        tagCloud.colorScale(ordinalColor)
        tagCloud.angles(arrayOf(-90.0, 0.0, 90.0))

        tagCloud.colorRange().enabled(true)
        tagCloud.colorRange().colorLineSize(15.0)
    }

    private fun initData(list: List<WordResponse>){
        val data: MutableList<DataEntry> = ArrayList()
        for(i in list){
            val random = Random()
            val num = random.nextInt(10)

            data.add(CategoryValueDataEntry(i.word,"music $num",i.count * 2000000000))
        }
        data.add(CategoryValueDataEntry("", "music", 1383220000))
        tagCloud.data(data)

        binding.anyChartView.setChart(tagCloud)
    }

    private val songDetailListener = object: SongDetailListener {
        override fun onItemClick(song: SongResponse) {
            val intent = Intent(context, SongDetailActivity::class.java)
            intent.putExtra(SONG,song)
            startActivity(intent)
        }

        override fun onRecordClick(song: SongResponse) {
            val intent = Intent(requireContext(), AudioRecordActivity::class.java)
            intent.putExtra(SONG,song)
            startActivity(intent)
        }
    }
}