/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    
    private int height;
    private int width;
    private int score;
    private Player player;
    
    private ArrayList<Elemento> objects = new ArrayList<Elemento>();
    private ArrayList<Elemento> garbage = new ArrayList<Elemento>();
    
    private Long timeLastCreated = 0L;
    
    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        player = new Player(width / 2 - 40, height - 30, 10, 10, Color.GREEN);
        player.incY = 0;
        
        addObject(player);
    }

    public void addObject(Elemento b) {
        objects.add(b);
    }
    
    public void update(Graphics g) {
        cleanScreen(g);
        clear();
        
        moveAll();
        drawAll(g);
        
        if (System.currentTimeMillis() - timeLastCreated > 350) {
            timeLastCreated = System.currentTimeMillis();
            
            int widthAux = new Random().nextInt(300);
            
            addObject(new Bloco(0, 0, widthAux, 40, Color.BLACK, widthAux));
            addObject(new Bloco(50 + widthAux * 2 + 200, 0, widthAux * 1000, 40, Color.BLACK, widthAux));
        }
       
        objects.remove(player);
        objects.add(player);
        
        clearRects();
        verifyScore();
    }
    
    public void clear() {
        for(Elemento b: garbage) {
            if(objects.contains(b))
                objects.remove(b);            
        }
        
        garbage.clear();
    }
    
    public void cleanScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
    }
    
    public void moveAll() {
        for(Elemento b : objects) {
            if(!garbage.contains(b)) {
                if(b instanceof Bloco) {
                       Bloco a = (Bloco)b;
                       a.moveAlien(width);
                }
                else 
                    b.mover();
            }
        }         
    }
    
    public void drawAll(Graphics g) {
        for(Elemento b: objects)
            if(!garbage.contains(b) && b.y > 0 && b.y < height) 
                b.desenhar(g);
    } 
    
    public void setPlayerActions(boolean right, boolean left) {
        if(right && player.x < width - 2 * player.width) {
            player.incX = 1;
        } else if(left && player.x > 0 + player.width) {
            player.incX = -1;
        } else {
            player.incX = 0;
        }
    }

    private void clearRects() {
       ArrayList<Elemento> aux = (ArrayList<Elemento>) objects.clone(); 
        
       for (Elemento b: aux) {
           if (b instanceof Bloco) {
               if (b.y > height) {
                   objects.remove(b);
               }
           }
       }
    }
    
    public void updateScore(Graphics g) {
        String msg = "Score = " + score;
        g.drawString(msg, 20, 100);
    }

    private void verifyScore() {
        for (Elemento b: objects) {
            if (b instanceof Bloco) {
                if (player.y == ((Bloco) b).y && (player.x >= ((Bloco) b).x && player.x <= ((Bloco) b).x + ((Bloco) b).width)) {
                    score--;
                }
            }
        }
    }
}
