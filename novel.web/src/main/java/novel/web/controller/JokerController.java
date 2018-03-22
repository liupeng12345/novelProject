package novel.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import novel.web.entitys.JSONResponse;

@Controller
public class JokerController extends BaseController {
	@RequestMapping(value = "/jokers.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getJoker(int i) {
		Process process = null;
		List<String> list = new ArrayList<String>();
		try {
			process = Runtime.getRuntime().exec("python G:\\conf\\joker.py " + i);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String a = null;
		try {
			while ((a = reader.readLine()) != null) {
				if (!a.trim().equals("")) {
					list.add(a.trim());// 将python处理出的标记数组保存在list中
					System.out.println(a);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer jokes=new StringBuffer();
		for (String string : list) {
			jokes.append(string);
		}
		return JSONResponse.succcess(jokes);
	}
}
