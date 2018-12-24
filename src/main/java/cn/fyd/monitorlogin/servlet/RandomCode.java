package cn.fyd.monitorlogin.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static cn.fyd.common.Constant.*;

/**
 * 获取验证码Servlet
 * @author fanyidong
 * @date 2018-12-12
 */
@WebServlet("/RandomCode")
public class RandomCode extends HttpServlet {

	private static Logger logger = LogManager.getLogger(RandomCode.class);
	private static final long serialVersionUID = 1L;

	/**
	 * 设置图片的宽度
	 */
	private static int WIDTH = 60;
	/**
	 * 设置图片的高度
	 */
	private static int HEIGHT = 22;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		char[] rands = generateCheckCode();
		drawBackground(g);
		drawRands(g, rands);
		g.dispose();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		byte[] buf = bos.toByteArray();
		response.setContentLength(buf.length);
		sos.write(buf);
		bos.close();
		sos.close();
		String imgCode = new String(rands);
		session.setAttribute("ImgCode", imgCode);
		logger.info("日志信息 => session_id = " + session.getId() + " ***** 验证码：" + imgCode);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

	private void drawBackground(Graphics g) {
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < CAPTCHA_BACKGROUND; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			g.setColor(new Color(red, green, blue));
			g.drawOval(x, y, 1, 0);
		}
	}

	private void drawRands(Graphics g, char[] rands) {
		Random random = new Random();
		int red = random.nextInt(110);
		int green = random.nextInt(50);
		int blue = random.nextInt(50);
		g.setColor(new Color(red, green, blue));
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
		g.drawString("" + rands[0], 1, 17);
		g.drawString("" + rands[1], 16, 15);
		g.drawString("" + rands[2], 31, 18);
		g.drawString("" + rands[3], 46, 16);
	}

	private char[] generateCheckCode() {
		String chars = "3456789ABCDEFGHJKLMNPQRSTUVWXY";
		char[] rands = new char[4];
		for (int i = 0; i < CAPTCHA_LENGTH; i++) {
			int rand = (int) (Math.random() * 30);
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}
}