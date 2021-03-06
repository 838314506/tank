package com.myself.tank;

import com.myself.tank.net.Client;
import com.myself.tank.net.Msg;
import com.myself.tank.net.TankMoveMsg;
import com.myself.tank.net.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * 处理键盘事件
 */
public class TankFrame extends Frame {

    public static TankFrame INSTANCE = new TankFrame();

    Tank myTrank = new Tank(new Random().nextInt(GAME_WIDTH), new Random().nextInt(GAME_HEIGHT), Dir.DOWN, this,Group.GOOD);

    List<Tank> foeTranks = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    List<Explored> explored = new ArrayList<>();

    Map<UUID,Tank> tanks = new HashMap<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public Tank getMyTrank() {
        return myTrank;
    }

    public void setMyTrank(Tank myTrank) {
        this.myTrank = myTrank;
    }

    public void addExplored(Explored e){
        explored.add(e);
    }

    @Override
    public void paint(Graphics a) {
        myTrank.paint(a);
//        for (int i = 0;i < foeTranks.size();i ++){
//            foeTranks.get(i).paint(a);
//        }
        tanks.values().stream().forEach((x) -> x.paint(a));

        a.drawString("子弹的数量："+ bullets.size(),10,60);
        for (int i = 0;i < bullets.size();i ++) {
            bullets.get(i).paint(a);
        }

        for (int i = 0;i < bullets.size();i ++){
//            for (int j = 0;j < foeTranks.size();j ++){
//                bullets.get(i).collide(foeTranks.get(j));
//            }
            for (Tank t : tanks.values()){
                bullets.get(i).collide(t);
            }
        }


        for (int i = 0;i < explored.size();i ++){
            explored.get(i).paint(a);
        }
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public Tank findByUUID(UUID id) {
        return tanks.get(id);
    }

    public Bullet findBulletByUUID(UUID bulletId){
        for(int i = 0;i < bullets.size();i ++){
            if (bullets.get(i).getId().equals(bulletId)){
                return bullets.get(i);
            }
        }
        return null;
    }

    public void put(Tank t){
        tanks.put(t.getId(),t);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTrank.fire();
                    break;
                default:
                    break;
            }
            setMainTrank();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTrank();
        }

        private void setMainTrank() {
            if (!bL && !bU && !bR && !bD) {
                myTrank.setMoving(false);
                Client.INSTANCE.send(new TankStopMsg(getMyTrank()));
            } else {
                if (bL) myTrank.setDir(Dir.LEFT);
                if (bU) myTrank.setDir(Dir.UP);
                if (bR) myTrank.setDir(Dir.RIGHT);
                if (bD) myTrank.setDir(Dir.DOWN);
                if (!myTrank.isMoving()){
                    Client.INSTANCE.send(new TankMoveMsg(getMyTrank()));
                }
                myTrank.setMoving(true);
            }

        }
    }
}
