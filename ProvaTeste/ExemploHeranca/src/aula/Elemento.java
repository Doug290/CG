package aula;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author glauco
 */
public abstract class Elemento {
    int x;
    int y;
    int incX = 1;
    int incY = 1;
    int width;
    int height;
    int widthAux;
    Color color;
    Rectangle rect;
    
    public Elemento(int x, int y, int width, int height, Color color, int widthAux) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.widthAux = widthAux;
        
        rect = new Rectangle(x, y, width, height);
    }
     
    public void mover() { 
        this.x += incX * 2;
        this.y += incY * 2.5;
        rect.x = x;
        rect.y = y;
    }
    
    public boolean colisao(Elemento b) {
        if(this.getClass().equals(b.getClass())) 
            return false;
        else
            return this.rect.intersects(b.rect);
    }
    
    public abstract void desenhar(Graphics g);
}
