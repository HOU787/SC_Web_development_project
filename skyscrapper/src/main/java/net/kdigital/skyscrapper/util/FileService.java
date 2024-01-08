package net.kdigital.skyscrapper.util;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

public class FileService {


	
	public static String saveFile(MultipartFile upload, String uploadPath) {
		
		// 파일이 첨부되었을 경우 경로가 있는지 확인하고
		// 없으면 폴더 생성해야함
		if(!upload.isEmpty()) {
			File path = new File(uploadPath); // 폴더관리, 파일관리
			if(!path.isDirectory()) 
				path.mkdirs();
		}
		
		// 원본의 파일명 // 그림1.jpg ====> c:\\upload\\그림1_dkaosdfmasodi8akw34-7ojf.jpg
		// 그림.1.jpg
		String originalFileName = upload.getOriginalFilename(); 
		
		// 랜덤값 발생
		String uuid = UUID.randomUUID().toString();
		
		// 원본파일명에서 확장자와 파일명을 분리 
		String filename;
		String ext;
		String savedFileName;   // 서버에 저장될 이름, 오라클에 저장
		 
		int position = originalFileName.lastIndexOf(".");
		
		// 확장자가 없는 경우
		if(position == -1) ext = "";
		// 확장자가 있는 경우
		else
			ext = "." + originalFileName.substring(position + 1);
		
		filename = originalFileName.substring(0, position);
		
		savedFileName = filename + "_" + uuid + ext; 
		
		// 파일을 하드에 저장하기
		File serverFile = null;
		serverFile = new File(uploadPath + "/" + savedFileName);
		
		try {
			upload.transferTo(serverFile);
		} catch(Exception e) {
			savedFileName = null;
			e.printStackTrace();
		}
		
		// ------- 이미지 불러오기 ---------
		
		
		
		return savedFileName;
	}
	
	// 삭제할 경로 포함 파일명이 조합된 문자열을 파라미터로 받는다.
	public static boolean deleteFile(String fullPath) { 
		boolean result = false;	// 삭제여부를 반환할 변수
		
		File delFile = new File(fullPath);
		
		if(delFile.isFile()) {
			delFile.delete();
			result = true;
		}

		return result;
	}
}











