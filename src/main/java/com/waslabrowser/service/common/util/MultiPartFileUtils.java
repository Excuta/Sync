package com.waslabrowser.service.common.util;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class MultiPartFileUtils {
	@Nullable
	public static InputStream toStream(MultipartFile multipartFile) {
		try {
			return multipartFile.getInputStream();
		} catch (IOException e) {
			return null;
		}
	}
}
