/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Elemento {
    
    public Player(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color, 0);
    }

    @Override
    public void desenhar(Graphics g) {
       g.setColor(color);
       g.fillRect(x, y, width, height);

    }
}
