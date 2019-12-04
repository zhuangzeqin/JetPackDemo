package com.eeepay.zzq.jetpackdemo.utils.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * Created by Administrator on 2016/7/19.
 */
public final class CommonTools {
	private static final String TAG = "CommonTools";

	/**
	 * 正则表达式的匹配
	 *
	 * @param reg
	 * @param string
	 * @return
	 */
	public static boolean startCheck(String reg, String string) {
		boolean tem = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		tem = matcher.matches();
		return tem;
	}

	/**
	 * 删除某目录下所有文件
	 */
	public static void deleteFile2(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return;
			}
			for (File f : childFile) {
				deleteFile2(f);
			}
			file.delete();
		}
	}


	/**
	 * 获取webview js css 图片资源存放路径
	 *
	 * @return
	 */
	@SuppressLint("NewApi")
	public static String getCacheDir_Path(Context context) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			/**获取缓存路径:/sdcard/Android/data/<application package>/cache  */
			cachePath = context.getExternalCacheDir().getPath() + File.separator + "szxys#WebviewCacheDir";
		} else {
			/**获取缓存路径:/data/data/<application package>/cache   */
			cachePath = context.getCacheDir().getPath() + File.separator + "szxys#WebviewCacheDir";
		}
		return cachePath;
	}

	/**
	 * 递归删除 文件/文件夹
	 *
	 * @param file
	 */
	public static void deleteFile(File file) {

		Log.i(TAG, "delete file path=" + file.getAbsolutePath());

		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		} else {
			Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
		}
	}


//	/**
//	 * 获取网络状态
//	 *
//	 * @param context
//	 * @return
//	 */
//	public static int getNetworkState(Context context) {
//
//		ConnectivityManager connManager = (ConnectivityManager) context
//				.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//		// Wifi
//		NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//		if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
//			return NetWorkStateEnum.NETWORN_WIFI.getNetStateType();
//		}
//
//		//3G
//		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//		if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
//			return NetWorkStateEnum.NETWORN_MOBILE.getNetStateType();
//		}
//		return NetWorkStateEnum.NETWORN_NONE.getNetStateType();
//	}

	/**
	 * 创建文件
	 *
	 * @param path     文件路径
	 * @param FileName 文件名称
	 * @return
	 */
	public static File createFile(String path, String FileName) {

		File dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();//创建目录
		}
		File file = new File(path, FileName);

		if (!file.exists()) {

			try {
				// 创建文件
				file.createNewFile();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 将文件转换为输入流
	 *
	 * @param file
	 * @return
	 */
	public static InputStream getStreamFromFile(File file) {
		try {
			InputStream inputStream = new FileInputStream(file);
			return inputStream;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串进行MD5编码
	 *
	 * @param key 需要md5 编码的key
	 * @return
	 */
	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 获取到当前应用程序的版本号
	 *
	 * @param context
	 * @return
	 */
	public static int getAppVersion(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/*
		 * Bitmap → byte[]
		 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		if (bm == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/*
	 * byte[] → Bitmap
	 */
	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length == 0) {
			return null;
		}
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	}

	/*
	 * Drawable → Bitmap
	 */
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		if (drawable == null) {
			return null;
		}
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/*
	 * Bitmap → Drawable
	 */
	@SuppressWarnings("deprecation")
	public static Drawable bitmap2Drawable(Bitmap bm) {
		if (bm == null) {
			return null;
		}
		return new BitmapDrawable(bm);
	}

	/**
	 * @param inStream
	 * @return 字节数组
	 * @throws Exception
	 * @功能 读取流
	 */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.flush();
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

}
