package net.meloli.demo.sys.gson.extend;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * long及Long类型为""或"null"时设置默认值0
 * @author shuchao
 */
public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
	@Override
	public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			  throws JsonParseException {
		try {
			if( json.getAsString().equals("") || json.getAsString().equals("null")) {//定义为long类型,如果后台返回""或者null,则返回0
			     return 0l;
			}
		} catch (Exception ignore) {
		}
		try {
			return json.getAsLong();
		} catch (NumberFormatException e) {
			throw new JsonSyntaxException(e);
		}
	}
	@Override
	public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src);
	}
}
