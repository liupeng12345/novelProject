package novel.web.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;




public class ImageUtils {
	private static String code;
	

	public static String getCode() {
		return code;
	}


	public static BufferedImage createImage(int width, int height, int n) {
		// ָ����֤��ĸ߶�
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ��ø�ͼƬ�Ļ���
		Graphics g = image.getGraphics();
		// �������ɫ�������
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// ���û�����ɫ��׼������
		g.setColor(Color.BLACK);
		// �Ӹ������ַ����������4���ַ�
		char[] element = "123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
		String checkcode = "";
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int randomIndex = Math.abs(random.nextInt()) % element.length;
			checkcode = checkcode + element[randomIndex];
		}
		g.drawString(checkcode, 10, 16);
		code=checkcode;
		return image;
	}
	public static BufferedImage createMulImage(int width,int height,int n){
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();    					//��ȡGraphics��Ķ���
		Graphics2D g2d = (Graphics2D) g;
		Random random = new Random();						//ʵ����һ��Random����
		Font mFont = new Font("����", Font.BOLD, 17);			//ͨ��Font��������
		g.setColor(getRandColor(200, 250)); 					//������ɫ
		g.fillRect(0, 0, width, height);    						//������֤�뱳��
		g.setFont(mFont);						//��������
		g.setColor(getRandColor(180, 200));		//������ɫ
		// �����������
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);	//������ʼ��x�������
			int y = random.nextInt(height - 1);	//������ʼ��y�������
			int x1 = random.nextInt(6) + 1;	//���ɽ�����x�������
			int y1 = random.nextInt(12) + 1;	//���ɽ�����y�������
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line);     //����ֱ��
		}
		String sRand = "";
		// ����������֤����
		String ctmp = "";
		int itmp = 0;
		for (int i = 0; i < n; i++) {
			if((random.nextInt(2)+1)==1){
				itmp = random.nextInt(10) + 48; // ����0~9������
				ctmp = String.valueOf((char) itmp);
			}else{
				itmp = random.nextInt(26) + 65; // ����A~Z����ĸ
				ctmp = String.valueOf((char) itmp);
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110));
			g.setColor(color);		//����������ɫ
			/** **����������ֲ���������תָ���Ƕ�* */
			// ��������תָ���Ƕ�
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 8, 7);
			// ��������
			float scaleSize = random.nextFloat() +0.8f;
			if (scaleSize > 1f)	scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			/** ********************* */
			g.drawString(ctmp, 15 * i + 18, 14);

		}
		
		code=sRand;
		return image;
	}
	// ��ȡ�����ɫ
		public static Color getRandColor(int s, int e) {
			Random random = new Random();
			if (s > 255) s = 255;
			if (e > 255) e = 255;
			int r = s + random.nextInt(e - s);		//�������RGB��ɫ�е�rֵ
			int g = s + random.nextInt(e - s);		//�������RGB��ɫ�е�gֵ
			int b = s + random.nextInt(e - s);		//�������RGB��ɫ�е�bֵ
			return new Color(r, g, b);
		}
}
