package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeDto> getNoticeList(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getNoticeList(map);
	}

	@Override
	public void writeNotice(NoticeDto notice) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).writeNotice(notice);
	}

	@Override
	public NoticeDto getNotice(int no) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).updateViews(no);
		NoticeDto notice = sqlSession.getMapper(NoticeMapper.class).getNotice(no);
		notice.setContent(notice.getContent().replace("<br>", "\r\n"));
		return notice;
	}

	@Override
	public void modifyNotice(NoticeDto notice) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).modifyNotice(notice);
	}

	@Override
	public void deleteNotice(int no) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).deleteNotice(no);
	}

	@Override
	public List<NoticeDto> searchByTitle(String word) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).searchByTitle(word);
	}

}
