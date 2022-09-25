package com.ssafy.gumid207.songbox;

import org.springframework.stereotype.Service;

import com.ssafy.gumid207.dto.SongDto;
import com.ssafy.gumid207.entity.Song;
import com.ssafy.gumid207.song.SongRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SongBoxServiceImpl implements SongBoxService {
	private final SongRepository songBoxRepo;

	@Override
	public SongDto getUserSetting(Long songSeq) throws Exception {
		Song song = songBoxRepo.findBySongSeq(songSeq).orElse( //
				Song.builder() //
						.songSeq(-1l) //
						.build());
		return SongDto.of(song);
	}

//	@Transactional
//	@Override
//	public UserSettingDto setUserSetting(Long userSeq, UserSettingDto settingDto) throws Exception {
//		settingDto.setUserSettingSeq(null);
//		settingDto.setUserSeq(userSeq);
//		UserSettingEntity settingEntity = songBoxRepo.findByUserSeq(userSeq).orElse( //
//				UserSettingEntity.of(settingDto));
//		if (settingDto.getNicknameColor() != null) {
//			settingEntity.setNicknameColor(settingDto.getNicknameColor());
//		}
//		if (settingDto.getTracklineColor() != null) {
//			settingEntity.setTracklineColor(settingDto.getTracklineColor());
//		}
//		songBoxRepo.save(settingEntity);
//		return UserSettingDto.of(settingEntity);
//	}
}
