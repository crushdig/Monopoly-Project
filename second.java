import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class second extends JPanel implements ActionListener, KeyListener {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Timer t = new Timer(5, this);
 double x = 0, y = 0, velX = 0, velY = 0;
 
 public second() {
  t.start();
  addKeyListener(this); // to JPanel
  setFocusable(true);
  setFocusTraversalKeysEnabled(false);
  
 }
 
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  Graphics2D g2 = (Graphics2D) g;
  g2.fill(new Ellipse2D.Double(x,y,40,40));
  
 }
 
 public void actionPerformed(ActionEvent e) {
  repaint();
  x += velX;
  y += velY;
 }
 
 public void up() {
  velY = -1.5;
  velX = 0;
 }
 public void down() {
  velY = 1.5;
  velX = 0;
 }
 public void left() {
  velX = -1.5;
  velY = 0;
 }
 public void right() {
  velX = 1.5;
  velY = 0;
 }
 
 public void keyPressed(KeyEvent e){
  int code = e.getKeyCode();
  if (code == KeyEvent.VK_UP) {
   up();
  }
  if (code == KeyEvent.VK_DOWN) {
   down();
  }
  if (code == KeyEvent.VK_LEFT) {
   left();
  }
  if (code == KeyEvent.VK_RIGHT) {
   right();
  }
 }

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
 
// public void keyTyped(KeyEvent e) {}
// public void keyReleased(KeyEvent e){﻿
}