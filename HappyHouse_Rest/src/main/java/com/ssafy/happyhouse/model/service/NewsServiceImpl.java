package com.ssafy.happyhouse.model.service;

//네이버 검색 API 예제 - blog 검색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NewsDto;

@Service
public class NewsServiceImpl implements NewsService{
		@Override
		public List<NewsDto> Newslist() {
			String clientId = "szxWiBK26BJPdfr_ztnI"; // 애플리케이션 클라이언트 아이디값"
			String clientSecret = "ckY7FdF3BQ"; // 애플리케이션 클라이언트 시크릿값"

			String text = null;
			try {
				text = URLEncoder.encode("부동산", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("검색어 인코딩 실패", e);
			}

			String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text; // json 결과
			// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
			// // xml 결과
			List<NewsDto> list = new LinkedList<NewsDto>();
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id", clientId);
			requestHeaders.put("X-Naver-Client-Secret", clientSecret);
			String responseBody = get(apiURL, requestHeaders);
			try {
				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(responseBody);
				JSONArray items = (JSONArray) obj.get("items");

				for (int i = 0; i < items.size(); i++) {
					JSONObject newsobj = (JSONObject) items.get(i);
					NewsDto news = new NewsDto();
					news.setTitle((String) newsobj.get("title"));
					news.setLink((String) newsobj.get("link"));
					news.setDescription((String) newsobj.get("description"));					
					news.setPubDate(date((String)newsobj.get("pubDate")));
					list.add(news);
				}
			} catch (Exception e) {
				System.out.println("진입2" + e);
				// TODO: handle exception
			}
			return list;
		}

		private String date(String str) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			String cd = str;
			LocalDateTime dateTime =LocalDateTime.parse(cd, formatter);
			formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm");
			cd = dateTime.format(formatter);
			return cd;
			
		}

		private static String get(String apiUrl, Map<String, String> requestHeaders) {
			HttpURLConnection con = connect(apiUrl);
			try {
				con.setRequestMethod("GET");
				for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}

				int responseCode = con.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
					return readBody(con.getInputStream());
				} else { // 에러 발생
					return readBody(con.getErrorStream());
				}
			} catch (IOException e) {
				throw new RuntimeException("API 요청과 응답 실패", e);
			} finally {
				con.disconnect();
			}
		}

		private static HttpURLConnection connect(String apiUrl) {
			try {
				URL url = new URL(apiUrl);
				return (HttpURLConnection) url.openConnection();
			} catch (MalformedURLException e) {
				throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
			} catch (IOException e) {
				throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
			}
		}

		private static String readBody(InputStream body) {
			InputStreamReader streamReader = new InputStreamReader(body);
			
			try (BufferedReader lineReader = new BufferedReader(streamReader)) {
				StringBuilder responseBody = new StringBuilder();

				String line;
				while ((line = lineReader.readLine()) != null) {
					responseBody.append(line);
				}

				return responseBody.toString();
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}
	}
