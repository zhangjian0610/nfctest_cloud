/*
 * 文件名：VerificationCodeController.java 版权：Copyright by http://www.bjleisen.com/ 描述： 修改人：zhangziqi
 * 修改时间：2018年3月15日 跟踪单号： 修改单号： 修改内容：
 */

package com.bupt.cardtest.controller.verificationcode;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;


@Controller
@RequestMapping("/verificationCodeController")
public class VerificationCodeController
{

    @RequestMapping(value = "/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();

        // 设置验证码图片大小
        int width = 90, height = 35;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        SecureRandom random = new SecureRandom();

        // 设置图片背景颜色
        g.setColor(getRandColor(256, 256)); 
        g.fillRect(0, 0, width, height);// 填充矩形区域

        g.setFont(new Font("微软雅黑", Font.BOLD, 30));

        // 绘制干扰线
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 50; i++ )
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String rand = RandomStringUtils.randomNumeric(4);
        //String rand = RandomUtil.getStringRandom(4);
        char c;
        for (int i = 0; i < 4; i++ )
        {
            c = rand.charAt(i);
            // 设置每个字的颜色
            g.setColor(new Color(10 + random.nextInt(120), 10 + random.nextInt(120), 10 + random.nextInt(120))); // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(String.valueOf(c), 18 * i + 10, 30);
        }

        // 放入Session，解决Cookie出错的问题
        HttpSession seesion = request.getSession();
        seesion.setAttribute("authCode", rand);

        // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // encoder.encode(image);

        ImageIO.write(image, "JPEG", response.getOutputStream());
        out.close();

    }

    private Color getRandColor(int fc, int bc)
    { // 给定范围获得随机颜色
        SecureRandom random = new SecureRandom();
        if (fc > 255)
        {
            fc = 254;
        }
        if (bc > 255)
        {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public boolean validate(String authImg, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String authCode = (String)session.getAttribute("authCode");
        if (authCode.equals(authImg))
            return true;
        else
            return false;
    }

}
