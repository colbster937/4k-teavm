package dev.colbster937.eagler;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import mc4k.M;

public class Main {
  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
      if (!(e instanceof NullPointerException &&
          e.getStackTrace()[0].getClassName().startsWith("dev.colbster937.java.awt.Graphics"))) {
        e.printStackTrace();
      }
    });

    M game = new M(null, null);
    game.start();
    game.setArgs(args);

    Canvas canvas = game.canvas;

    canvas.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        AwtEvent ev = new AwtEvent();
        ev.id = 401;
        ev.key = Utils.keyUtoL(e.getKeyCode());
        game.handleEvent(ev);
      }

      public void keyReleased(KeyEvent e) {
        AwtEvent ev = new AwtEvent();
        ev.id = 402;
        ev.key = Utils.keyUtoL(e.getKeyCode());
        game.handleEvent(ev);
      }
    });

    canvas.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        int[] p = CanvasUtils.scaleMouse(canvas, e);
        AwtEvent ev = new AwtEvent();
        ev.id = 501;
        ev.x = p[0];
        ev.y = p[1];
        ev.modifiers = (e.getButton() == MouseEvent.BUTTON3 ? 4 : 0);
        game.handleEvent(ev);
      }

      public void mouseReleased(MouseEvent e) {
        int[] p = CanvasUtils.scaleMouse(canvas, e);
        AwtEvent ev = new AwtEvent();
        ev.id = 502;
        ev.x = p[0];
        ev.y = p[1];
        ev.modifiers = (e.getButton() == MouseEvent.BUTTON3 ? 4 : 0);
        game.handleEvent(ev);
      }
    });

    canvas.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        int[] p = CanvasUtils.scaleMouse(canvas, e);
        AwtEvent ev = new AwtEvent();
        ev.id = 503;
        ev.x = p[0];
        ev.y = p[1];
        game.handleEvent(ev);
      }

      public void mouseDragged(MouseEvent e) {
        int[] p = CanvasUtils.scaleMouse(canvas, e);
        AwtEvent ev = new AwtEvent();
        ev.id = 506;
        ev.x = p[0];
        ev.y = p[1];
        game.handleEvent(ev);
      }
    });
  }
}