package net.kdigital.skyscrapper.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageNavigator {
	// 멤버
	private int pagePerGroup;		// 그룹당 페이지 수
	private int countPerPage;		// 한 페이지당  글의 개수
	private int currentPage;		// 사용자가 현재 요청한 페이지
	private int totalRecordCount;	// 총 글의 개수
	private int totalPageCount;		// 총 페이지 수
	private int totalGroupCount;	// 총 그룹의 수
	private int currentGroup;		// 요청한 페이지가 속한 그룹
	private int startPageGroup;		// 현재 그룹의 첫 페이지
	private int endPageGroup;		// 현재 그룹의 마지막 페이지
	private int startRecord;		// 현재 요청한 페이지의 첫 글
	private int endRecord;			// 현재 요청한 페이지의 마지막 글
	
	public PageNavigator(int countPerPage, int pagePerGroup, int currentPage, int totalRecordCount) {
		// 초기화
		this.countPerPage = countPerPage;
		this.pagePerGroup = pagePerGroup;
		this.currentPage  = currentPage;
		this.totalRecordCount = totalRecordCount;
		
		// 전체 페이지 수 계산
		totalPageCount = totalRecordCount / countPerPage;
		totalPageCount += (totalRecordCount % countPerPage == 0) ? 0 : 1;
		
		// 사용자가 요청한 페이지의 첫번째 글번호와 마지막 글번호 계산
		startRecord = countPerPage * (currentPage - 1) + 1; 
		endRecord   = countPerPage * (currentPage - 1) + countPerPage; 		

		// 전체 그룹수
		totalGroupCount = totalPageCount / pagePerGroup; 
		totalGroupCount += (totalPageCount % pagePerGroup == 0) ? 0 : 1;
		
		// 요청한 페이지가 속한 그룹 계산
		currentGroup = (currentPage - 1) / pagePerGroup; // ?
		
		// 현재 그룹의 첫 페이지
		startPageGroup = currentGroup * pagePerGroup + 1;
		startPageGroup = (startPageGroup < 0) ? 0 : startPageGroup;

		// 현재 그룹의 마지막 페이지
		endPageGroup = (currentGroup + 1) * pagePerGroup ;
		endPageGroup = (endPageGroup > totalPageCount) ?  totalPageCount : endPageGroup;		
	}
}
