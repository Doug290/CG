/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import static java.awt.Color.getHSBColor;

public class Bola {
    public List<Bola> bolas;
    private Color cor;
    private int pos_x, pos_y, direcao_x, direcao_y;
    private int form_largura, form_altura;
   
    public Bola(int form_largura, int form_altura, Color cor) {
        pos_x = new Random().nextInt(350);
        pos_y = new Random().nextInt(350);
        this.cor = cor;
        int num_random = new Random().nextInt(40);
        
        if (num_random < 10) {
            direcao_x = 1;
            direcao_y = 1;
        } else  if (num_random < 20) {
            direcao_x = -1;
            direcao_y = 1;
        } else  if (num_random < 30) {
            direcao_x = 1;
            direcao_y = -1;
        } else  if (num_random < 40) {
            direcao_x = -1;
            direcao_y = -1;
        } 
        
        this.form_largura = form_largura;        
        this.form_altura = form_altura;
    }

    public void draw(Graphics g) {
        g.setColor(cor);
        g.fillOval(pos_x, pos_y, 10, 10); 
    }
    
    public void limits() {
        if (pos_x < 0 || pos_x > form_largura - 30) direcao_x *= -1;
        if (pos_y < 0 || pos_y > form_altura - 30) direcao_y *= -1;
    }

    public void move() {
        pos_x += direcao_x;
        pos_y += direcao_y;
    }
}
