package com.hanyeop.songforyou.view.main.other

import android.graphics.Color
import android.text.TextPaint
import android.view.View
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.CategoryValueDataEntry
import com.anychart.chart.common.dataentry.DataEntry
import com.magicgoop.tagpshere.example.util.LoremIpsum
import com.anychart.scales.OrdinalColor
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseFragment
import com.hanyeop.songforyou.databinding.FragmentShowOthersBinding
import com.magicgoop.tagsphere.OnTagTapListener
import com.magicgoop.tagsphere.item.TagItem
import com.magicgoop.tagsphere.item.TextTagItem
import kotlin.random.Random

class ShowOthersFragment : BaseFragment<FragmentShowOthersBinding>(R.layout.fragment_show_others) {
    override fun init() {

//        initChart()

        initTagView()
    }

    private fun initTagView() {
        binding.tagView.setTextPaint(
            TextPaint().apply {
                isAntiAlias = true
                textSize = resources.getDimension(R.dimen.tag_text_size)
                color = Color.DKGRAY
            }
        )
        val loremSize = LoremIpsum.list.size
        (0..40).map {
            TextTagItem(text = LoremIpsum.list[Random.nextInt(loremSize)])
        }.toList().let {
            binding.tagView.addTagList(it)
        }
        binding.tagView.setRadius(3f)
        binding.tagView.setOnTagTapListener(object : OnTagTapListener {
            override fun onTap(tagItem: TagItem) {
                Toast.makeText(
                    requireContext(),
                    "On tap: ${(tagItem as TextTagItem).text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initChart(){
        binding.apply {
//            anyChartView.setProgressBar(progressBar)
        }

        val tagCloud = AnyChart.tagCloud()

        tagCloud.title("World Population")

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

        val data: MutableList<DataEntry> = ArrayList()
        data.add(CategoryValueDataEntry("China", "asia", 1383220000))
        data.add(CategoryValueDataEntry("India", "asia", 1316000000))
        data.add(CategoryValueDataEntry("United States", "america", 324982000))
        data.add(CategoryValueDataEntry("Indonesia", "asia", 263510000))
        data.add(CategoryValueDataEntry("Brazil", "america", 207505000))
        data.add(CategoryValueDataEntry("Pakistan", "asia", 196459000))
        data.add(CategoryValueDataEntry("Nigeria", "africa", 191836000))
        data.add(CategoryValueDataEntry("Bangladesh", "asia", 162459000))
        data.add(CategoryValueDataEntry("Russia", "europe", 146804372))
        data.add(CategoryValueDataEntry("Japan", "asia", 126790000))
        data.add(CategoryValueDataEntry("Mexico", "america", 123518000))
        data.add(CategoryValueDataEntry("Ethiopia", "africa", 104345000))
        data.add(CategoryValueDataEntry("Philippines", "asia", 104037000))
        data.add(CategoryValueDataEntry("Egypt", "africa", 93013300))
        data.add(CategoryValueDataEntry("Vietnam", "asia", 92700000))
        data.add(CategoryValueDataEntry("Germany", "europe", 82800000))
        data.add(CategoryValueDataEntry("Democratic Republic of the Congo", "africa", 82243000))
        data.add(CategoryValueDataEntry("Iran", "asia", 80135400))
        data.add(CategoryValueDataEntry("Turkey", "asia", 79814871))
        data.add(CategoryValueDataEntry("Thailand", "asia", 68298000))
        data.add(CategoryValueDataEntry("France", "europe", 67013000))
        data.add(CategoryValueDataEntry("United Kingdom", "europe", 65110000))
        data.add(CategoryValueDataEntry("Italy", "europe", 60599936))
        data.add(CategoryValueDataEntry("Tanzania", "africa", 56878000))
        data.add(CategoryValueDataEntry("South Africa", "africa", 55908000))
        data.add(CategoryValueDataEntry("Myanmar", "asia", 54836000))
        data.add(CategoryValueDataEntry("South Korea", "asia", 51446201))
        data.add(CategoryValueDataEntry("Colombia", "america", 49224700))
        data.add(CategoryValueDataEntry("Kenya", "africa", 48467000))
        data.add(CategoryValueDataEntry("Spain", "europe", 46812000))
        data.add(CategoryValueDataEntry("Argentina", "america", 43850000))
        data.add(CategoryValueDataEntry("Ukraine", "europe", 42541633))
        data.add(CategoryValueDataEntry("Sudan", "africa", 42176000))
        data.add(CategoryValueDataEntry("Uganda", "africa", 41653000))
        data.add(CategoryValueDataEntry("Algeria", "africa", 41064000))
        data.add(CategoryValueDataEntry("Poland", "europe", 38424000))
        data.add(CategoryValueDataEntry("Iraq", "asia", 37883543))
        data.add(CategoryValueDataEntry("Canada", "america", 36541000))
        data.add(CategoryValueDataEntry("Morocco", "africa", 34317500))
        data.add(CategoryValueDataEntry("Saudi Arabia", "asia", 33710021))
        data.add(CategoryValueDataEntry("Uzbekistan", "asia", 32121000))
        data.add(CategoryValueDataEntry("Malaysia", "asia", 32063200))
        data.add(CategoryValueDataEntry("Peru", "america", 31826018))
        data.add(CategoryValueDataEntry("Venezuela", "america", 31431164))
        data.add(CategoryValueDataEntry("Nepal", "asia", 28825709))
        data.add(CategoryValueDataEntry("Angola", "africa", 28359634))
        data.add(CategoryValueDataEntry("Ghana", "africa", 28308301))
        data.add(CategoryValueDataEntry("Yemen", "asia", 28120000))
        data.add(CategoryValueDataEntry("Afghanistan", "asia", 27657145))
        data.add(CategoryValueDataEntry("Mozambique", "africa", 27128530))
        data.add(CategoryValueDataEntry("Australia", "australia", 24460900))
        data.add(CategoryValueDataEntry("North Korea", "asia", 24213510))
        data.add(CategoryValueDataEntry("Taiwan", "asia", 23545680))
        data.add(CategoryValueDataEntry("Cameroon", "africa", 23248044))
        data.add(CategoryValueDataEntry("Ivory Coast", "africa", 22671331))
        data.add(CategoryValueDataEntry("Madagascar", "africa", 22434363))
        data.add(CategoryValueDataEntry("Niger", "africa", 21564000))
        data.add(CategoryValueDataEntry("Sri Lanka", "asia", 21203000))
        data.add(CategoryValueDataEntry("Romania", "europe", 19760000))
        data.add(CategoryValueDataEntry("Burkina Faso", "africa", 19632147))
        data.add(CategoryValueDataEntry("Syria", "asia", 18907000))
        data.add(CategoryValueDataEntry("Mali", "africa", 18875000))
        data.add(CategoryValueDataEntry("Malawi", "africa", 18299000))
        data.add(CategoryValueDataEntry("Chile", "america", 18191900))
        data.add(CategoryValueDataEntry("Kazakhstan", "asia", 17975800))
        data.add(CategoryValueDataEntry("Netherlands", "europe", 17121900))
        data.add(CategoryValueDataEntry("Ecuador", "america", 16737700))
        data.add(CategoryValueDataEntry("Guatemala", "america", 16176133))
        data.add(CategoryValueDataEntry("Zambia", "africa", 15933883))
        data.add(CategoryValueDataEntry("Cambodia", "asia", 15626444))
        data.add(CategoryValueDataEntry("Senegal", "africa", 15256346))
        data.add(CategoryValueDataEntry("Chad", "africa", 14965000))
        data.add(CategoryValueDataEntry("Zimbabwe", "africa", 14542235))
        data.add(CategoryValueDataEntry("Guinea", "africa", 13291000))
        data.add(CategoryValueDataEntry("South Sudan", "africa", 12131000))
        data.add(CategoryValueDataEntry("Rwanda", "africa", 11553188))
        data.add(CategoryValueDataEntry("Belgium", "europe", 11356191))
        data.add(CategoryValueDataEntry("Tunisia", "africa", 11299400))
        data.add(CategoryValueDataEntry("Cuba", "america", 11239004))
        data.add(CategoryValueDataEntry("Bolivia", "america", 11145770))
        data.add(CategoryValueDataEntry("Somalia", "africa", 11079000))
        data.add(CategoryValueDataEntry("Haiti", "america", 11078033))
        data.add(CategoryValueDataEntry("Greece", "europe", 10783748))
        data.add(CategoryValueDataEntry("Benin", "africa", 10653654))
        data.add(CategoryValueDataEntry("Czech Republic", "europe", 10578820))
        data.add(CategoryValueDataEntry("Portugal", "europe", 10341330))
        data.add(CategoryValueDataEntry("Burundi", "africa", 10114505))
        data.add(CategoryValueDataEntry("Dominican Republic", "america", 10075045))
        data.add(CategoryValueDataEntry("Sweden", "europe", 10054100))
        data.add(CategoryValueDataEntry("United Arab Emirates", "asia", 10003223))
        data.add(CategoryValueDataEntry("Jordan", "asia", 9889270))
        data.add(CategoryValueDataEntry("Azerbaijan", "asia", 9823667))
        data.add(CategoryValueDataEntry("Hungary", "europe", 9799000))
        data.add(CategoryValueDataEntry("Belarus", "europe", 9498600))
        data.add(CategoryValueDataEntry("Honduras", "america", 8866351))
        data.add(CategoryValueDataEntry("Austria", "europe", 8773686))
        data.add(CategoryValueDataEntry("Tajikistan", "asia", 8742000))
        data.add(CategoryValueDataEntry("Israel", "asia", 8690220))
        data.add(CategoryValueDataEntry("Switzerland", "europe", 8417700))
        data.add(CategoryValueDataEntry("Papua New Guinea", "australia", 8151300))

        tagCloud.data(data)

//        binding.anyChartView.setChart(tagCloud)
    }
}