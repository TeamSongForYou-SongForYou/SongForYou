package com.hanyeop.songforyou.view.audio

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hanyeop.songforyou.R
import kotlin.random.Random

class SoundVisualizerView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    // MainActivity 에서 콜백함수 지정
    var onRequestCurrentAmplitude: (() -> Int)? = null

    // 계단화 방지 플레그 적용
    private val amplitudePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.CustomColor1)
        strokeWidth = LINE_WIDTH
        strokeCap = Paint.Cap.ROUND // 라인의 양 끄투머리 동그랗게
    }

    private var drawingWidth: Int = 0
    private var drawingHeight: Int = 0
    private var drawingAmplitudes: List<Int> = emptyList()
    private var isReplaying: Boolean = false
    private var replayingPosition: Int = 0

    // 반복적인 드로우 처리를 위한 Runnable 객체
    private val visualizeRepeatAction: Runnable = object : Runnable {
        override fun run() {
            if (!isReplaying) {
                // Amplitude를 가져오고, Draw를 요청

                // Amplitude 값 가져오기
                val currentAmplitude = onRequestCurrentAmplitude?.invoke() ?: 0
                // 오른 쪽 부터 순차적으로 그리기
                drawingAmplitudes = listOf(currentAmplitude) + drawingAmplitudes
            } else {
                replayingPosition++
            }
            invalidate() // 드로잉 처리

            handler?.postDelayed(this, ACTION_INTERVAL)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawingWidth = w
        drawingHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // canvas 가 null 인 경우 반환환
        canvas ?: return

        val centerY = drawingHeight / 2f // 뷰의 중앙 높이
        var offsetX = drawingWidth.toFloat() // 오른 쪽 부터

        // 어떤 걸 그릴지
        // 진폭값을 배열로 넣어두고 오른쪽 부터 왼쪽으로 그려지게
        drawingAmplitudes
            .let { amplitudes ->
                if (isReplaying) {
                    amplitudes.takeLast(replayingPosition) // 가장 뒤 부터 리플레이 포지션 까지
                } else {
                    amplitudes
                }
            }
            .forEach { amplitude ->
                // 그릴려는 높이 대비 몇퍼로 그릴지 (80%)
                val lineLength = amplitude / MAX_AMPLITUDE * drawingHeight * 0.8F

                // offset 계산 다시
                // 뷰의 우측 부터 그릴 것임.
                offsetX -= LINE_SPACE

                // 진폭 배열이 많이 쌓여 뷰를 초과하는 경우
                // 보이지 않는 경우 그리지 않아야함
                if (offsetX < 0) return@forEach

                // amplitude 그리기 (좌상단이 0,0 우하단이 w,h)
                canvas.drawLine(
                    offsetX, // 시작
                    centerY - lineLength / 2F,
                    offsetX,
                    centerY + lineLength / 2F,
                    amplitudePaint
                )
            }
    }


    fun startVisualizing(isReplaying: Boolean) {
        this.isReplaying = isReplaying
        // 반복 호출 하기 위해 post
        handler?.post(visualizeRepeatAction)
    }

    fun stopVisualizing() {
        // 반복 호출 제거
        replayingPosition = 0 // 구간 초기화
        handler?.removeCallbacks(visualizeRepeatAction)
    }

    fun clearVisualization() {
        drawingAmplitudes = emptyList()
        invalidate()
    }

    // companion object(동반자 객체) = static 비슷 한 개념
    companion object {
        private const val LINE_WIDTH = 10F
        private const val LINE_SPACE = 15F

        // 오디오 레코더의 get max amplitude(진폭, 볼륨) 음성의 최대값의 short 타입 최대값임.
        private const val MAX_AMPLITUDE = Short.MAX_VALUE.toFloat() // Float로 미리 타입 변환

        private const val ACTION_INTERVAL = 20L // 20밀리초
    }

}