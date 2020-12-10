package com.liruicong.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 跨服务器文件上传
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传");

        //定义上传文件服务器路径
        String path = "http://localhost:9090/SpringMVC_04_fileuploadserver_war/uploads/";

        //说明上传文件项
        //获取上传文件的名称
        String fileName = upload.getOriginalFilename();
        //把文件的名称设置唯一值：uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;
        //完成文件上传，跨服务器上传
        //创建客户端的对象
        Client client = Client.create();
        //和图片的服务器进行连接
        WebResource webResource = client.resource(path + fileName);
        //上传文件
        webResource.put(upload.getBytes());
        return "success";
    }

    /**
     * SpringMVC方式文件上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("SpringMVC文件上传");
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }

        //说明上传文件项
        //获取上传文件的名称
        String fileName = upload.getOriginalFilename();
        //把文件的名称设置唯一值：uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;
        //完成文件上传
        upload.transferTo(new File(path, fileName));

        return "success";
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }

        //解析request对象，获取上传的文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析request
        List<FileItem> items = upload.parseRequest(request);

        //遍历
        for (FileItem item : items){
            //进行判断，当前item对象是否是上传文件项
            if(item.isFormField()){
                //说明普通表单项
            }else{
                //说明上传文件项
                //获取上传文件的名称
                String fileName = item.getName();
                //把文件的名称设置唯一值：uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "_" + fileName;
                //完成文件上传
                item.write(new File(path, fileName));
                //删除临时文件
                item.delete();
            }
        }

        return "success";
    }
}
