package org.ccf.ccfpedia.cms.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtils {

    private static Gson gson = new Gson();


    public static <T> T fromJson(String json, Class<T> cls) {
		try {
			return gson.fromJson(json, cls);
		} catch (Exception e) {
			throw e;
		}
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		try {
			return gson.fromJson(json, typeOfT);
		} catch (Exception e) {
			throw e;
		}
	}

	public static String toJson(Object obj) {
		try {
			return gson.toJson(obj);
		} catch (Exception e) {
			throw e;
		}
	}

}