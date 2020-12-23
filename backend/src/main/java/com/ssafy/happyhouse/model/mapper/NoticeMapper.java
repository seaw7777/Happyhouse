package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {

	List<NoticeDto> getNoticeList(Map<String, String> map) throws Exception;
	void writeNotice(NoticeDto notice) throws Exception;
	NoticeDto getNotice(int no) throws Exception;
	void updateViews(int no) throws Exception;
	void modifyNotice(NoticeDto notice) throws Exception;
	void deleteNotice(int no) throws Exception;
	List<NoticeDto> searchByTitle(String word) throws Exception;

}
