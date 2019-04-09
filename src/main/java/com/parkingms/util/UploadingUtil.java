package com.parkingms.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传
 * @author 张怡
 *
 */
public class UploadingUtil {
	public static Map<String, Object> upload(HttpServletRequest req,MultipartFile imageFile) {
		// 获取到文件的原始名字
		String oldName = imageFile.getOriginalFilename();
		// 设置文件新名字
		String newName = UUID.randomUUID() + "_" + oldName;
		// 获取到保存的文件路径
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/upload");
		// 创建一个文件,upload文件夹下面创建
		File file = new File(path, newName);
		// 判断路径是否存在，没有则创建
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Map<String, Object> res = new HashMap<>();
		// 将上传过来的文件数据写入到file对象
		try {
			imageFile.transferTo(file);
			res.put("url", newName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
