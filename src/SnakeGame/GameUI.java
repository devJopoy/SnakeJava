package SnakeGame;

import SnakeGame.GameSession.PixelBlock;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class GameUI {
    private GameSession gameSession;
    private Map map;
    private JLabel scoreCard;

    public GameUI(GameSession gameSession){
        this.gameSession = gameSession;
        initPanel();
    }

    public void initPanel(){
        JFrame frame = new JFrame();
        map = new Map();
        scoreCard = new JLabel("0");
        scoreCard.setBounds(900, 0, 50, 50);

        frame.getContentPane().setLayout(null);
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKE GAME");
        frame.add(map);  
        frame.add(scoreCard);
        frame.addKeyListener(map);
        frame.setVisible(true);
        
    }

    public void updateMap(){
        map.updateMap();
    }

    public void updateScore(String score){
        scoreCard.setText(score);
    }

    private class Map extends JPanel implements KeyListener{
        public Map(){
            setLayout(null);
            setBackground(Color.GRAY);
            setBounds(0, 0, GameSession.MAP_SIZE_X + 40, GameSession.MAP_SIZE_Y + 40);
            //setLocation(100, 100);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            //System.out.println(" I am typed");
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            int direction = 0;
            if(keyCode == 37){
                direction = 5;
            } else if(keyCode == 38){
                direction = 6;
            } else if(keyCode == 39){
                direction = 3;
            } else if(keyCode == 40){
                direction = 4;
            }
            gameSession.setDirection(direction);
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println(" I am released");        
        }
    
        public void updateMap(){
            repaint();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(20, 20, GameSession.MAP_SIZE_X, GameSession.MAP_SIZE_Y);
    
            Iterator<PixelBlock> it = gameSession.getPixelBlockIterator();
    
            while(it.hasNext()){
                System.out.println();
    
                PixelBlock b = it.next();
                int x = b.getX();
                int y = b.getY();
                boolean isFood = b.getIsFood();
                if(isFood){
                    g.setColor(Color.ORANGE);
                }else{
                    g.setColor(Color.WHITE);
                }

                g.fillOval(20 + x*20, 20 + y*20, 20, 20);
            
            }
        }
    }
}
