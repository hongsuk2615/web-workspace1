package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{
	
	/*
	 *반드시 미완성된 rename메서드를 오버라이딩 해서 구현해야함
	 *기존의 파일을 전달받아서 파일명 수정작업후에 수정된 파일을 반환해주는 메서드 
	 **/
	@Override
	public File rename(File originFile) {
		
		// 원본파일명("aaa.jpg")
		String originName = originFile.getName();
		
		// 수정파일명 : 파일업로드 시간(년/월/일/시/분/초) + 5자리 랜덥값(10000~99999) => 최대한 이름이 겹치지 않게
		// 확장자 : 원본파일명에서 그대로 얻어올 예정
		// 원본명                    ->               수정명
		// aaa.jpg                  -> 2023022011265012345.jpg
		// 1. 파일업로드 된 시간 (년월일시분초) => String currentTime;
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) ;
		
		// 2. 5자리 랜덤값 추출 int ranNum;
		int ranNum = (int)(90000*Math.random() + 10000); 
		
		// 3. 원본파일 확장자 => String ext
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ranNum + ext; 
		
		return new File(originFile.getParent(), changeName); // 원본파일을 수정된 파일명으로 적용시켜서 객체 반환
		
	}
}
