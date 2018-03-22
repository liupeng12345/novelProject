package novel.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import novel.web.entitys.JSONResponse;
import novel.web.entitys.User;
import novel.web.mapper.UserMapper;
import novel.web.servers.Userserversmpl;
import novel.web.util.Conver2MD5;

@Controller

public class UserController extends BaseController {
	@Resource
	private Userserversmpl userserversmpl;
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public void login(@RequestParam String username,@RequestParam String password)
	
	{  PrintWriter out=null;
     	try {
			out=response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
		User user = userserversmpl.getUser(username);
	     if(user!=null)
	     {
	    	 if(user.getPassword().equals(password))
	    	 {
	    		 out.print("<script>alert(\"登陆成功\")</script>");
	    	 }else {
	    		 out.print("<script>alert(\"密码错误\")</script>");
	    	 }
	     }
	     else {
	    	 out.print("<script>alert(\"用户名错误\")</script>");
		}
	    
	}
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	@ResponseBody
	public void register(@RequestParam String username,@RequestParam String password)
	{
		    User user = new User(username, password);
		  System.out.println(username);
		  System.out.println(password);
			user.setPassword(password);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (userserversmpl.insert(user)==1) {
				out.print("<script>alert('注册成功!');</script>");
			
			}
			else {
				out.print("<script>alert('注册失败!');</script>");
				
				
			}
			
		} 
		

	@RequestMapping(value="/testCode.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse testCode(String code) {
		String truecode = request.getSession().getAttribute("truecode").toString().toLowerCase();
		
		String info=null;
     	if(truecode.equals(code.toLowerCase()))
     	{
     		info="验证码正确";
     		return JSONResponse.succcess(info);
     	}
		return null;
	}
	
}
