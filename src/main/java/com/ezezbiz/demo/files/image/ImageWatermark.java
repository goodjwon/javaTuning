package com.ezezbiz.demo.files.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 *         String sourceImagePath = "C:\\Users\\user\\Pictures\\jellyfish-4925772__340.jpg";
 *         String destinationImagePath = "C:\\Users\\user\\Pictures\\jellyfish-4925772__340_out.jpg";
 */

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ImageWatermark {
    public static void main(String[] args) {
        String sourceImagePath = "C:\\Users\\user\\Pictures\\jellyfish-4925772__340.jpg";
        String destinationImagePath = "C:\\Users\\user\\Pictures\\jellyfish-4925772__340_out.jpg";
        String watermarkText = "테스트공급업체 1004";

        // Record the start CPU time for the current thread
        long startCpuTime = getThreadCpuTime();

        try {
            Thumbnails.of(sourceImagePath)
                    .size(800, 600)
                    .watermark(Positions.CENTER, makeWatermarkText(watermarkText, 50), 0.5f)
                    .outputQuality(0.8)
                    .toFile(destinationImagePath);

            System.out.println("Watermark added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to add watermark.");
        }

        // Record the end CPU time for the current thread
        long endCpuTime = getThreadCpuTime();
        long elapsedCpuTime = endCpuTime - startCpuTime;

        // Convert CPU time from nanoseconds to seconds
        double elapsedCpuTimeSeconds = elapsedCpuTime / 1_000_000_000.0;
        System.out.printf("CPU time elapsed: %.4f seconds\n", elapsedCpuTimeSeconds);
    }

    private static long getThreadCpuTime() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean.isCurrentThreadCpuTimeSupported()) {
            return threadMXBean.getCurrentThreadCpuTime();
        }
        return -1;
    }

    private static BufferedImage makeWatermarkText(String text, int fontSize) {
        int canvasWidth = 800;
        int canvasHeight = 600;

        // Create an off-screen image to measure the text width
        BufferedImage measureImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dMeasure = measureImage.createGraphics();
        g2dMeasure.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        FontMetrics fm = g2dMeasure.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2dMeasure.dispose();

        // Adjust spacing based on text size
        int horizontalSpacing = textWidth + 50;
        int verticalSpacing = textHeight + 50;

        BufferedImage img = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        // Set rendering hints for quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Set the font and color for the watermark text
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        g2d.setColor(new Color(211, 211, 211, (int) (255 * 0.4))); // Light gray color with 40% transparency

        // Rotate the canvas to draw diagonal text
        g2d.rotate(Math.toRadians(-45), canvasWidth / 2, canvasHeight / 2);

        // Draw text repeatedly across the entire image
        for (int x = -canvasWidth; x < canvasWidth * 2; x += horizontalSpacing) {
            for (int y = -canvasHeight; y < canvasHeight * 2; y += verticalSpacing) {
                g2d.drawString(text, x, y);
            }
        }

        g2d.dispose();
        return img;
    }
}
