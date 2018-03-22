package novel.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import novel.web.util.ImageUtils;




public class CodeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void code(){
    	HttpServletResponse response = ServletActionContext.getResponse();
    	HttpServletRequest request = ServletActionContext.getRequest();
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("Cache-control", "no-cache");
	    response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		BufferedImage image = ImageUtils.createMulImage(80, 40, 4);
		String truecode = ImageUtils.getCode();
		request.getSession(true).setAttribute("truecode", truecode);
	    try {
			ImageIO.write(image, "jpeg", response.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    }

}
