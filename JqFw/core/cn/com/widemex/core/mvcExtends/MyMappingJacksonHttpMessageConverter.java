package cn.com.widemex.core.mvcExtends;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.joda.time.DateTime;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;


import cn.com.widemex.core.json.hibernate.HibernateModule;
import cn.com.widemex.core.utils.data.DateHelper;

/**
 * 自定义json格式的http消息转换
 * @author 张中原
 *
 */
@SuppressWarnings("deprecation")
public class MyMappingJacksonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Module module = new HibernateModule();
	
	
	// 代码块：解决Hibernate延时加载、日期格式转换，JSON转换的问题
	{
		objectMapper.registerModule(module);
		objectMapper.getSerializationConfig().setDateFormat(DateHelper.TIMESTAMP_FORMAT);
		objectMapper.getDeserializationConfig().setDateFormat(DateHelper.TIMESTAMP_FORMAT);
	}

	private boolean prefixJson = false;


	/**
	 * Construct a new {@code BindingJacksonHttpMessageConverter}.
	 */
	public MyMappingJacksonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	/**
	 * Sets the {@code ObjectMapper} for this view. If not set, a default
	 * {@link ObjectMapper#ObjectMapper() ObjectMapper} is used.
	 * <p>Setting a custom-configured {@code ObjectMapper} is one way to take further control of the JSON serialization
	 * process. For example, an extended {@link org.codehaus.jackson.map.SerializerFactory} can be configured that provides
	 * custom serializers for specific types. The other option for refining the serialization process is to use Jackson's
	 * provided annotations on the types to be serialized, in which case a custom-configured ObjectMapper is unnecessary.
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null----objectMapper对象不能为空");
		this.objectMapper = objectMapper;
	}

	/**
	 * Indicates whether the JSON output by this view should be prefixed with "{} &&". Default is false.
	 * <p> Prefixing the JSON string in this manner is used to help prevent JSON Hijacking. The prefix renders the string
	 * syntactically invalid as a script so that it cannot be hijacked. This prefix does not affect the evaluation of JSON,
	 * but if JSON validation is performed on the string, the prefix would need to be ignored.
	 */
	public void setPrefixJson(boolean prefixJson) {
		this.prefixJson = prefixJson;
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		JavaType javaType = getJavaType(clazz);
		return this.objectMapper.canDeserialize(javaType) && canRead(mediaType);
	}

	/**
	 * Returns the Jackson {@link JavaType} for the specific class.
	 *
	 * <p>Default implementation returns {@link TypeFactory#type(java.lang.reflect.Type)}, but this can be overridden
	 * in subclasses, to allow for custom generic collection handling. For instance:
	 * <pre class="code">
	 * protected JavaType getJavaType(Class&lt;?&gt; clazz) {
	 *   if (List.class.isAssignableFrom(clazz)) {
	 *     return TypeFactory.collectionType(ArrayList.class, MyBean.class);
	 *   } else {
	 *     return super.getJavaType(clazz);
	 *   }
	 * }
	 * </pre>
	 *
	 * @param clazz the class to return the java type for
	 * @return the java type
	 */
	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.type(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return this.objectMapper.canSerialize(clazz) && canWrite(mediaType);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		// should not be called, since we override canRead/Write instead
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(clazz);
		try {
			return this.objectMapper.readValue(inputMessage.getBody(), javaType);
		}
		catch (JsonParseException ex) {
			throw new HttpMessageNotReadableException("不能读JSON：Could not read JSON: " + ex.getMessage(), ex);
		}
		catch (EOFException ex) {
			throw new HttpMessageNotReadableException("不能读JSON：Could not read JSON: " + ex.getMessage(), ex);
		}
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		JsonEncoding encoding = getEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator =
				this.objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
		try {
			if (this.prefixJson) {
				jsonGenerator.writeRaw("{} && ");
			}
			this.objectMapper.writeValue(jsonGenerator, o);
		}
		catch (JsonGenerationException ex) {
			throw new HttpMessageNotWritableException("不能写JSON： Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	private JsonEncoding getEncoding(MediaType contentType) {
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			for (JsonEncoding encoding : JsonEncoding.values()) {
				if (charset.name().equals(encoding.getJavaName())) {
					return encoding;
				}
			}
		}
		return JsonEncoding.UTF8;
	}

}
