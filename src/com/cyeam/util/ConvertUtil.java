package com.cyeam.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ConvertUtil {
	// JGP
	public static final String JPG = "jpg";
	public static final String JPEG = "jpeg";
	// GIF
	public static final String GIF = "gif";
	// PNG
	public static final String PNG = "png";
	// BMP
	public static final String BMP = "bmp";

	public static String inputStream2String(InputStream in) {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		try {
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out.toString();
	}

	public static Date str2Date(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return d;
	}

	public static void converter(File imgFile, String format, File formatFile)
			throws IOException {
		BufferedImage bIMG = ImageIO.read(imgFile);
		ImageIO.write(bIMG, format, formatFile);
	}
	
	public final static void scale(File f, File result, String format, int width, int height, boolean bb) {
		try {
            double ratio = 0.0; // 缩放比例
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);

            System.out.println(width / (double)bi.getWidth()+ "|"+ height / (double)bi.getHeight());
            AffineTransformOp op = new AffineTransformOp(AffineTransform
                    .getScaleInstance(width / (double)bi.getWidth(), height / (double)bi.getHeight()), null);
            itemp = op.filter(bi, null);
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage)itemp, format, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
