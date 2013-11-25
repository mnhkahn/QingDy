package com.cyeam.util;

import java.io.IOException;

import net.sf.json.JSONObject;

public class JSONUtil {

	public static void marshal() throws IOException {
		JSONObject jsonObject = JSONObject.fromObject(FileUtil.File2Str("slide.json"));
		jsonObject.size();
		System.out.println(jsonObject.size());
	}

}
