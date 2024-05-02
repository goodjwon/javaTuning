package com.ezezbiz.demo.files.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageWatermark {
    public static void main(String[] args) {
        String sourceImagePath = "path/to/input.jpg";
        String destinationImagePath = "path/to/output.jpg";
        String watermarkText = "Watermark";

        try {
            Thumbnails.of(sourceImagePath)
                    .size(800, 600)
                    .watermark(Positions.BOTTOM_RIGHT, makeWatermarkText(watermarkText, 50), 0.5f)
                    .outputQuality(0.8)
                    .toFile(destinationImagePath);

            System.out.println("Watermark added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to add watermark.");
        }
    }

    private static Image makeWatermarkText(String text, int fontSize) {
        BufferedImage img = new BufferedImage(400, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        g2d.drawString(text, 10, 50);
        g2d.dispose();

        return img;
    }
}