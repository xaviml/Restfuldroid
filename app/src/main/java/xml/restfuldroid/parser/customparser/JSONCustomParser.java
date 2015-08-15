package xml.restfuldroid.parser.customparser;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import xml.restfuldroid.core.WebService;
import xml.restfuldroid.parser.CustomRequestParser;
import xml.restfuldroid.parser.CustomResponseParser;
import xml.restfuldroid.parser.WebServiceParser;
import xml.restfuldroid.parser.customparser.deserializer.BitmapDeserializer;

/**
 * Created by zenbook on 30/03/15.
 */
public class JSONCustomParser implements CustomRequestParser, CustomResponseParser, WebServiceParser {

    private Gson mGson;

    @Override
    public void initWebService(WebService service) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Bitmap.class, new BitmapDeserializer(service));
        mGson = gsonBuilder.create();
    }

    @Override
    public <T> byte[] serializer(T obj) {
        return mGson.toJson(obj).getBytes();
    }

    @Override
    public <T> T deserializer(byte[] data, Class<T> c) {
        return mGson.fromJson(new String(data), c);
    }

    @Override
    public String getContentType() {
        return "application/json";
    }
}
