package novel.spider.junit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class teast {

	@Test
	public void teast1() {
		// TODO Auto-generated constructor stub
		PrintWriter out=null;
		try {
			File file = new File("E:\\3");
			List<File> files = new ArrayList<File>();
			File[] files2=file.listFiles();
			for(File file2:files2)
			{
				files.add(file2);
			}
			files.sort(new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					// TODO Auto-generated method stub
					System.out.println( o1.getName());
					if(o1.getName().length()==o2.getName().length())
					return o1.getName().compareTo(o2.getName());
					return Long.compare(o1.getName().length(), o2.getName().length());
				}
				
			});
			 out=new PrintWriter(new FileWriter("C:\\Users\\刘鹏\\Desktop\\新建文本文档.txt"));
			 System.out.println(files.size());
			for(int i=0;i<files.size();i++)
			{
				
				BufferedReader reader=new BufferedReader(new FileReader(files.get(i)));
				String text=null;
				while((text=reader.readLine())!=null)
				{
				out.println(text);		
				}
				reader.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			out.close();
		}
		
	}

}
