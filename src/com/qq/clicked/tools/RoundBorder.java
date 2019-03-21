/*
 * 本类为登录界面的登录按钮画外围的边框
 */
package com.qq.clicked.tools;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundBorder implements Border {

	public Insets getBorderInsets(Component c) {
		return new Insets(0, 0, 0, 0);
	}

	public boolean isBorderOpaque() {
		return false;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// 使用黑色在组件的外边缘绘制一个圆角矩形
		g.setColor(new Color(5, 186, 251));
		g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 5, 5);
	}

}