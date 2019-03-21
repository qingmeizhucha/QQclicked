/*
 * ����Ϊ�ü�JLabel�ϵ�ͼƬ����
 */
package com.qq.clicked.tools;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SetJLabel{
	public JLabel GetJLabel(String path,final int x,final int y,final int w,final int h) throws IOException {
		final JLabel label;
		label = new JLabel();
		// TODO Auto-generated constructor stub
		final BufferedImage image = ImageIO.read(new File(path));
		ImageIcon icon = new ImageIcon(image.getSubimage(x, y, w, h));
		label.setIcon(icon);
		label.addMouseListener(new MouseListener() {
			
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//����뿪�ظ�ԭ״
				ImageIcon ic = new ImageIcon(image.getSubimage(x, y, w, h));
				label.setIcon(ic);
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				//��������ɫ
				ImageIcon ic = new ImageIcon(image.getSubimage(x+30, y, w, h));
				label.setIcon(ic);
			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		label.setOpaque(false);
		
		return label;
		
	}

}
