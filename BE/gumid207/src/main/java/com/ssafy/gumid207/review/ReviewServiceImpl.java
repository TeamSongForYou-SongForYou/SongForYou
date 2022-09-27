package com.ssafy.gumid207.review;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.gumid207.customexception.UserNotFoundException;
import com.ssafy.gumid207.dto.ReviewDto;
import com.ssafy.gumid207.dto.ReviewUploadDto;
import com.ssafy.gumid207.entity.File;
import com.ssafy.gumid207.entity.Karaoke;
import com.ssafy.gumid207.entity.Review;
import com.ssafy.gumid207.entity.User;
import com.ssafy.gumid207.res.MyRecordResDto;
import com.ssafy.gumid207.s3.FileRepository;
import com.ssafy.gumid207.s3.S3FileService;
import com.ssafy.gumid207.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final UserRepository userRepo;
	private final FileRepository fileRepo;
	private final S3FileService s3serv;
	private final KaraokeRepository karaokeRepo;
	private final ReviewRepository reviewRepo;

	@Override
	public ReviewDto uploadReview(Long userSeq, ReviewUploadDto reviewUploadDto, MultipartFile imgFile)
			throws Exception {
		User user = userRepo.findByUserSeq(userSeq).orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));
		Karaoke karaoke = karaokeRepo
				.findByKaraokeNameAndKaraokeAddress(reviewUploadDto.getKaraokeName(),
						reviewUploadDto.getKaraokeAddress()) //
				.orElse(Karaoke.builder() //
						.karaokeName(reviewUploadDto.getKaraokeName()) //
						.karaokeAddress(reviewUploadDto.getKaraokeAddress()) //
						.build());
		karaokeRepo.save(karaoke);
		
		File file = File.of(s3serv.upload(imgFile, imgFile.getOriginalFilename(), "reviewImg", "png"));
		fileRepo.save(file);
		Review review = Review.builder() //
				.reviewPrice(reviewUploadDto.getReviewPrice()) //
				.reviewPayType(reviewUploadDto.getReviewPayType()) //
				.reviewEmployee(reviewUploadDto.getReviewEmployee()) //
				.reviewToilet(reviewUploadDto.getReviewToilet()) //
				.reviewCleanness(reviewUploadDto.getReviewCleanness()) //
				.reviewSoundQuality(reviewUploadDto.getReviewSoundQuality()) //
				.reviewContent(reviewUploadDto.getReviewContent()) //
				.fileSeq(file) //
				.karaokeSeq(karaoke) //
				.userSeq(user) //
				.build();
		reviewRepo.save(review);
		
		
		return ReviewDto.of(review);
	}
}
