package zrw.zuul.controller;



import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller

public class FileUpLoadController {
	
  
  
  
  //@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
 // @Produces({MediaType.APPLICATION_JSON,"text/plain;charset=UTF-8"})
  
  
  
	@RequestMapping(value="/upload",
			  method=RequestMethod.POST
			  /*以下2行加上也可以*/
			  //consumes=MediaType.MULTIPART_FORM_DATA,
			 // produces=MediaType.APPLICATION_JSON
  )
	@ResponseBody
  public  String handleUpload(@RequestParam(value="file",required=true)
  MultipartFile file)throws Exception{
	  byte[] bytes = file.getBytes();
	  File fileToSave=new File(file.getOriginalFilename());
	  FileCopyUtils.copy(bytes, fileToSave);
	  String path = fileToSave.getAbsolutePath();
	  //path=F:\新建文件夹 (3)\springcloud-gateway-zuul8050-upload\zrw.txt
	  System.out.println("保存到了如下路径:"+path);
	  return path;
  }
}
