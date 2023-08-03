package com.midas.api.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ImageUtil {
	// REDUCAO DE IMAGENS PARA DB
	public static byte[] resizeImage(byte[] imageByte, Integer sizeImg) throws Exception {
		try (InputStream inputImage = new ByteArrayInputStream(imageByte);) {
			BufferedImage image = ImageIO.read(inputImage);
			BufferedImage imgResized = simpleResizeImage(image, sizeImg);
			return imageToByte(imgResized);
		}
	}

	private static byte[] imageToByte(BufferedImage img) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			return baos.toByteArray();
		}
	}

	private static BufferedImage simpleResizeImage(BufferedImage originalImage, int targetWidth) throws Exception {
		return Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.AUTOMATIC, targetWidth, Scalr.OP_ANTIALIAS);
	}
}
