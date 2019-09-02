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
    private int altura, largura, pos_x, pos_y, direcao_x, direcao_y;
    private int form_largura, form_altura, tamanho;
    public boolean removido;
   
    public Bola(int form_largura, int form_altura, Color cor) {
        pos_x = new Random().nextInt(350);
        pos_y = new Random().nextInt(350);
        tamanho = 30;
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

    public void colisao(List<Bola> bolas) {
        this.bolas = bolas;
        colide_bola();
    }

    public void colide_bola() {
        for(Bola bola : bolas) {
            if (this.pos_x != bola.pos_x && this.pos_y != bola.pos_y &&  !bola.removido && !this.removido) {
                if ((this.pos_x >= (bola.pos_x - (this.tamanho - (this.tamanho / 5)))
                    && this.pos_x <= (bola.pos_x + (this.tamanho - (this.tamanho / 5))))
                    && (this.pos_y >= (bola.pos_y - (this.tamanho - (this.tamanho / 5)))
                    && this.pos_y <= (bola.pos_y + (this.tamanho - (this.tamanho / 5))))) {
                    if (this.cor == Color.BLUE && bola.cor == Color.RED) {
                        remover_aumentar(bola);
                    } else if (this.cor == Color.RED && bola.cor == Color.GREEN) {
                        remover_aumentar(bola);
                    } else if (this.cor == Color.GREEN && bola.cor == Color.BLUE) {
                        remover_aumentar(bola);
                    }
                }
            }
        }
    }

    public void remover_aumentar(Bola bola) {
        bola.altura = bola.largura = 0;
        bola.removido = true;
        int aux = altura;
        altura = largura = tamanho = (int) (aux + (aux * 0.3));
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

    public int qual_cor(Color cor) {
        if (cor == Color.RED) return 1;
        if (cor == Color.GREEN) return 1;
        if (cor == Color.BLUE) return 1;
        return 0;
    }
}
