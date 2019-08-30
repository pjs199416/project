package com.project.web.upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dsna.util.images.ValidateCode;
 
@WebServlet("/vcode.action")
public class VcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1.向客户端输出一张图片,生成验证码对象
		ValidateCode validateCode = new ValidateCode(120, 50, 4, 20);
		//2.获取验证码字符串
		String code = validateCode.getCode();
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		//3.调用方法,向客户端输出一张图片
		ServletOutputStream outputStream = response.getOutputStream();
		validateCode.write(outputStream);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
