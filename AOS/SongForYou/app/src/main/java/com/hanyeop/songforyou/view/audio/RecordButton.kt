package com.hanyeop.songforyou.view.audio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.utils.State

/*
Android 스튜디오가 뷰와 상호작용할 수 있도록 하려면 최소한 Context 및 AttributeSet 객체를
매개변수로 취하는 생성자를 제공해야 합니다.
이 생성자를 사용하면 Layout Editor에서 뷰의 인스턴스를 만들고 수정할 수 있습니다.

attrs : default 파라미터를 나타낼 때 보통 사용

AppCompat : 매년 새로운 버전의 안드로이드가 출시되는 편인데, 이때 필연적으로 이전 버전에 대한
    호환성 지원이 필요한것은 불가피. AppCompat으로 기존 클래스를 랩핑해서 이전 버전에서도
    새로 출시한 것들을 정상적으로 동작하도록 해주는 라이브러리로 이해하면된다.

    그러면 activity.xml 에서는 TextView 등 을 쓰는 이유?
        xml 내부에 정의된 TextView 등을 자동으로 앱컴펫에 매핑할 수 있는 클래스가 있는 경우 매핑
        해주는 기능이 프로젝트에 포함이 되기 때문. (하지만. 코드는 지원 안하기 때문에.)

 */


class RecordButton(
    context: Context,
    attrs: AttributeSet
) : AppCompatImageButton(context, attrs) {

    init {
        setBackgroundResource(R.drawable.shape_oval_button)
    }

    fun updateIconWithState(state: State) {
        when (state) {
            State.BEFORE_RECORDING -> {
                setImageResource(R.drawable.ic_recorde)
            }
            State.ON_RECORDING -> {
                setImageResource(R.drawable.ic_stop)
            }
            State.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play)
            }
            State.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop)
            }
        }
    }
}
