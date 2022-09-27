package com.ssafy.gumid207.review;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.dto.ReviewDto;
import com.ssafy.gumid207.dto.ReviewUploadDto;
import com.ssafy.gumid207.res.MyRecordResDto;

@Service
public interface ReviewService {

	ReviewDto uploadReview(Long userSeq, ReviewUploadDto reviewUploadDto, MultipartFile imgFile) throws Exception;

}