package com.olosom.health.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 拷贝 assets 里的配置文件到 SD 卡缓存目录
 */
public class AssetsCopyUtil {

	private static final String PARAMS_FILE_NAME = "params.dat";

	private static final String SPSEX_FILE_NAME = "spsex.dat";

	public static void copy(Context context) {
		if (!isExist(context)) {
			write(context, PARAMS_FILE_NAME);
			write(context, SPSEX_FILE_NAME);
		}
	}

	private static void write(Context context, String fileName) {
		InputStream inputStream;
		try {
			inputStream = context.getResources().getAssets().open(fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(
					CommonUtils.getDiskCacheDir(context).getAbsolutePath()
							+ "/" + fileName);
			byte[] buffer = new byte[512];
			int count = 0;
			while ((count = inputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, count);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isExist(Context context) {
		File file = new File(CommonUtils.getDiskCacheDir(context) + "/"
				+ PARAMS_FILE_NAME);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

}
