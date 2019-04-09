package com.parkingms.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUploadUtil {

	/**
	 * 保存多个文件
	 * @param req
	 * @return 文件名的Set集合
	 */
	public static List<String> saveFiles(HttpServletRequest req) {
		
		List<String> fileNameSet = new ArrayList<String>();

		CommonsMultipartResolver multipartReslver = new CommonsMultipartResolver(req.getSession().getServletContext());

		// 检查 form 是否有 enctype="multipart/form-data" 属性值
		if (multipartReslver.isMultipart(req)) {
			
			// request 转换为 多部分 request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
			
			// 获取 multiRequest 中所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			
			// 遍历所有文件
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				
				if (file != null) {
					// 保存文件
					String filename = saveFile(req, file);
					fileNameSet.add(filename);
				}
			}
		}
		
		return fileNameSet;
	}

	/**
	 * 保存单个文件，保存成功返回文件名，否则返回null
	 * 
	 * @param req
	 * @param file
	 * @return
	 */
	public static String saveFile(HttpServletRequest req, MultipartFile file) {

		String result = null;

		// 获取原始文件名
		String fileName = file.getOriginalFilename();

		// 给文件设置新名字
		String newFileName = UUID.randomUUID() + "_" + fileName;

		// 需要保存的文件路径
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/upload");

		// 检查目录是否存在
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 在目录下创建文件
		File newFile = new File(path, newFileName);

		// 将上传的文件数据，写入到file对象
		try {
			file.transferTo(newFile);
			result = newFileName;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
