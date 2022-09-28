package com.hanyeop.songforyou.view.karaoke.review

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.Gson
import com.hanyeop.songforyou.R
import com.hanyeop.songforyou.base.BaseActivity
import com.hanyeop.songforyou.databinding.ActivityCreateReviewBinding
import com.hanyeop.songforyou.model.dto.ReviewDto
import com.hanyeop.songforyou.model.response.ReviewResponse
import com.hanyeop.songforyou.utils.resizeBitmapFormUri
import com.hanyeop.songforyou.view.karaoke.KaraokeViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class CreateReviewActivity : BaseActivity<ActivityCreateReviewBinding>(R.layout.activity_create_review) {

    private val karaokeViewModel by viewModels<KaraokeViewModel>()

    private var imgFile : MultipartBody.Part? = null

    override fun init() {
        initClickListener()

        initViewModelCallBack()
    }

    private fun initViewModelCallBack(){
        karaokeViewModel.successMsgEvent.observe(this){
            showToast(it)
            finish()
        }

        karaokeViewModel.errorMsgEvent.observe(this){
            showToast(it)
        }
    }

    private fun initClickListener(){
        binding.apply {
            imageButton.setOnClickListener {
                pickPhotoGallery()
            }

            tvCreate.setOnClickListener{
                // TODO
                val review = ReviewDto(
                    intent.getStringExtra("name")!!,
                    intent.getStringExtra("address")!!,
                    "1000, 3곡",
                    "",
                    "없음",
                    "화장실",
                    ratingCleanness.rating.toInt(),
                    ratingSoundQuality.rating.toInt(),
                    etContent.text.toString())
                val json = Gson().toJson(review)
                val reviewDto = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)

                karaokeViewModel.uploadReview(reviewDto, imgFile!!)
            }
        }
    }

    private fun pickPhotoGallery() {
        val photoIntent = Intent(Intent.ACTION_PICK)
        photoIntent.type = "image/*"
        photoIntent.putExtra("crop","true")
        pickPhotoResult.launch(photoIntent)
    }

    private val pickPhotoResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            binding.imageButton.setImageURI(it.data?.data)

            var bitmap : Bitmap?
            val uri = it.data?.data

            try {
                if(uri != null){
                    bitmap = resizeBitmapFormUri(uri,this)
                    createMultiPart(bitmap!!)
                }
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
//        else if(it.resultCode == Activity.RESULT_CANCELED){ // 아무 사진도 선택하지 않은 경우
//            binding.imageRecommendPhoto.visibility = View.VISIBLE
//            binding.tvImageText.visibility = View.VISIBLE
//        }
    }

    private fun createMultiPart(bitmap: Bitmap) {
        var imageFile: File? = null
        try {
            imageFile = createFileFromBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile!!)
        imgFile = MultipartBody.Part.createFormData("imgFile", imageFile!!.name, requestFile)
    }

    @Throws(IOException::class)
    private fun createFileFromBitmap(bitmap: Bitmap): File? {
        val newFile = File(this.filesDir, "profile_${System.currentTimeMillis()}")
        val fileOutputStream = FileOutputStream(newFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, fileOutputStream)
        fileOutputStream.close()
        return newFile
    }
}