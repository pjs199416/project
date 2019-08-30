package com.project.web.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.util.UploadUtils;


@WebServlet("/upload.action")
public class UploadAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.上传获得访问路径
		String path = UploadUtils.uploadFile(request, "upload/temp", "upload/admin");
		//2.用utf-8编码响应访问路径
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(path);
		
	}
}
