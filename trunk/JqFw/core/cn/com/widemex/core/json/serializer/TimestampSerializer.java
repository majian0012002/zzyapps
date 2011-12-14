package cn.com.widemex.core.json.serializer;

import java.io.IOException;
import java.sql.Timestamp;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import cn.com.widemex.core.utils.data.DateHelper;

public class TimestampSerializer  extends JsonSerializer<Timestamp>{
	/**格式化*/
//	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
    public void serialize(Timestamp value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
			String formattedDate = DateHelper.TIMESTAMP_FORMAT.format(value);
            jgen.writeString(formattedDate);
    }
}
