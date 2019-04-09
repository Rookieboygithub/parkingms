package com.parkingms.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpLoad {

	/**
	 * 
	 * 此方法用于将文件全路径上传至数据库，将文件对象存入服务器目录硬盘中
	 */
	public static String fileUpLoad(HttpServletRequest req, MultipartFile imageFile) {
		// 获取到文件的原始名字
		String oldName = imageFile.getOriginalFilename();
		// 更改文件的名字，防止上传的文件名重复
		String newName = UUID.randomUUID() + oldName;
		// 获取保存的文件路径
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/upload");
		// 创建一个文件于upload目录下
		File file = new File(path, newName);
		if (!file.exists()) {
			file.mkdir();
		} else {
			// 将文件对象写入file对象中
			try {
				imageFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file.getPath();

	}
}
