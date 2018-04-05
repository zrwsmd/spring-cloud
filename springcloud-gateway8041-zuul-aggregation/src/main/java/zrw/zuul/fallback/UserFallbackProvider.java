package zrw.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
@Component
public class UserFallbackProvider  implements ZuulFallbackProvider {

	@Override
	public ClientHttpResponse fallbackResponse() {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers=new HttpHeaders();
				MediaType mt=new MediaType("application","json",
						Charset.forName("utf-8"));
				headers.setContentType(mt);
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				// TODO Auto-generated method stub
				return new ByteArrayInputStream("用户微服务不可以,请稍后重试".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				// 状态文本，本实例返回的是OK
				return this.getStatusCode().getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// 数字类型的状态码，本实例返回的是200
				return this.getStatusCode().value();
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		} ;
	}

	@Override
	public String getRoute() {
		// 表示为哪个微服务提供回退
		return "providerunsafe";
	}
  
}
