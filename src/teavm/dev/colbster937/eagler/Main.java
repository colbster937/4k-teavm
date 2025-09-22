package dev.colbster937.eagler;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.MouseEvent;

import dev.colbster937.render.Canvas;
import dev.colbster937.render.RenderContext;

import m4k.M;

public class Main {
  private static M game;
  private static Window window;
  private static HTMLDocument doc;
  private static Canvas canvas;
  private static RenderContext ctx;
  private static int[] d;
  
  private static String id = "game_frame";

  public static void main(String[] args) {
    window = Window.current();
    doc = window.getDocument();
    HTMLElement node = doc.getElementById(id);
    d = new int[3];

    d[1] = window.getInnerWidth();
    d[2] = window.getInnerHeight();

    if (node == null) {
      createCanvas(doc.getBody());
    } else if (!(node instanceof HTMLCanvasElement)) {
      node.removeAttribute("id");
      createCanvas(node);
    } else {
      canvas = (Canvas) node;
      if (canvas.getWidth() == 0) canvas.setWidth(d[1]); else d[1] = canvas.getWidth(); d[0] = 1;
      if (canvas.getHeight() == 0) canvas.setHeight(d[2]); else d[2] = canvas.getHeight(); d[0] = 1;
    }

    ctx = (RenderContext) canvas.getContext("2d");

    game = new M(canvas, ctx);
    game.start();

    Util.setSmoothing(ctx, false);
    canvas.setTabIndex(0);
    canvas.focus();

    canvas.addEventListener("mousedown", (MouseEvent e) -> {
      int[] p = Util.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 501;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mouseup", (MouseEvent e) -> {
      int[] p = Util.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 502;
      ev.x = p[0];
      ev.y = p[1];
      ev.modifiers = (e.getButton() == 2 ? 4 : 0);
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("mousemove", (MouseEvent e) -> {
      int[] p = Util.scaleMouse(canvas, e);
      AwtEvent ev = new AwtEvent();
      ev.id = 503;
      ev.x = p[0];
      ev.y = p[1];
      game.handleEvent(ev);
    });

    canvas.addEventListener("mouseleave", (MouseEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 505;
      game.handleEvent(ev);
    });

    canvas.addEventListener("contextmenu", (Event e) -> {
      e.preventDefault();
    });

    canvas.addEventListener("keydown", (KeyboardEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 401;
      int code = e.getKeyCode();
      if (code >= 65 && code <= 90) code += 32;
      ev.key = code;
      game.handleEvent(ev);
      e.preventDefault();
    });

    canvas.addEventListener("keyup", (KeyboardEvent e) -> {
      AwtEvent ev = new AwtEvent();
      ev.id = 402;
      int code = e.getKeyCode();
      if (code >= 65 && code <= 90) code += 32;
      ev.key = code;
      game.handleEvent(ev);
      e.preventDefault();
    });

    window.addEventListener("resize", (Event e) -> {
      if (d[0] > 0) return;
      int nw = window.getInnerWidth();
      int nh = window.getInnerHeight();
      canvas.setWidth(nw);
      canvas.setHeight(nh);
      d[1] = nw;
      d[2] = nh;
    });

    doc.addEventListener("click", (Event e) -> {
      canvas.focus();
    });
  }

  private static void createCanvas(HTMLElement parent) {
    canvas = (Canvas) doc.createElement("canvas");
    canvas.setId(id);
    canvas.setWidth(d[1]);
    canvas.setHeight(d[2]);
    parent.appendChild(canvas);
  }

  public static int getWidth() {
    return d[1];
  }

  public static int getHeight() {
    return d[2];
  }
}
