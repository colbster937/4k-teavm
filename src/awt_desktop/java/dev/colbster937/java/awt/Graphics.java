package dev.colbster937.java.awt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import dev.colbster937.eagler.Utils;
import dev.colbster937.java.awt.image.BufferedImage;

public class Graphics extends Graphics2D {
  private Graphics2D g;
  private Component c;

  public Graphics(Graphics2D graphics, Component canvas) {
    this.g = graphics;
    this.c = canvas;
  }

  public void drawImage(BufferedImage img, int x, int y, int w, int h, Object obs) {
    if (w == Utils.RENDER_SIZE[0] && h == Utils.RENDER_SIZE[1]) {
      int cw = c.getWidth();
      int ch = c.getHeight();
      double sx = cw / (double) Utils.GAME_SIZE[0];
      double sy = ch / (double) Utils.GAME_SIZE[1];
      double s = Utils.FULLSCREEN_ALT ? Math.min(sx, sy) : Math.max(sx, sy);
      w = (int) Math.round(Utils.GAME_SIZE[0] * s);
      h = (int) Math.round(Utils.GAME_SIZE[1] * s);
      x = (cw - w) / 2;
      y = (ch - h) / 2;
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, cw, ch);
    }
    img.flushToCanvas();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    g.drawImage(img.backingImage(), x, y, w, h, c);
  }

  public void draw(Shape s) { g.draw(s); }
  public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) { return g.drawImage(img, xform, obs); }
  public void drawImage(java.awt.image.BufferedImage img, BufferedImageOp op, int x, int y) { g.drawImage(img, op, x, y); }
  public void drawRenderedImage(RenderedImage img, AffineTransform xform) { g.drawRenderedImage(img, xform); }
  public void drawRenderableImage(RenderableImage img, AffineTransform xform) { g.drawRenderableImage(img, xform); }
  public void drawString(String str, int x, int y) { g.drawString(str, x, y); }
  public void drawString(String str, float x, float y) { g.drawString(str, x, y); }
  public void drawString(AttributedCharacterIterator iterator, int x, int y) { g.drawString(iterator, x, y); }
  public void drawString(AttributedCharacterIterator iterator, float x, float y) { g.drawString(iterator, x, y); }
  public void drawGlyphVector(GlyphVector gv, float x, float y) { g.drawGlyphVector(gv, x, y); }
  public void fill(Shape s) { g.fill(s); }
  public boolean hit(Rectangle rect, Shape s, boolean onStroke) { return g.hit(rect, s, onStroke); }
  public GraphicsConfiguration getDeviceConfiguration() { return g.getDeviceConfiguration(); }
  public void setComposite(Composite comp) { g.setComposite(comp); }
  public void setPaint(Paint paint) { g.setPaint(paint); }
  public void setStroke(Stroke s) { g.setStroke(s); }
  public void setRenderingHint(Key hintKey, Object hintValue) { g.setRenderingHint(hintKey, hintValue); }
  public Object getRenderingHint(Key hintKey) { return g.getRenderingHint(hintKey); }
  public void setRenderingHints(Map<?, ?> hints) { g.setRenderingHints(hints); }
  public void addRenderingHints(Map<?, ?> hints) { g.addRenderingHints(hints); }
  public RenderingHints getRenderingHints() { return g.getRenderingHints(); }
  public void translate(int x, int y) { g.translate(x, y); }
  public void translate(double tx, double ty) { g.translate(tx, ty); }
  public void rotate(double theta) { g.rotate(theta); }
  public void rotate(double theta, double x, double y) { g.rotate(theta, x, y); }
  public void scale(double sx, double sy) { g.scale(sx, sy); }
  public void shear(double shx, double shy) { g.shear(shx, shy); }
  public void transform(AffineTransform Tx) { g.transform(Tx); }
  public void setTransform(AffineTransform Tx) { g.setTransform(Tx); }
  public AffineTransform getTransform() { return g.getTransform(); }
  public Paint getPaint() { return g.getPaint(); }
  public Composite getComposite() { return g.getComposite(); }
  public void setBackground(Color color) { g.setBackground(color); }
  public Color getBackground() { return g.getBackground(); }
  public Stroke getStroke() { return g.getStroke(); }
  public void clip(Shape s) { g.clip(s); }
  public FontRenderContext getFontRenderContext() { return g.getFontRenderContext(); }
  public java.awt.Graphics create() { return new Graphics((Graphics2D) g.create(), c); }
  public Color getColor() { return g.getColor(); }
  public void setColor(Color c0) { g.setColor(c0); }
  public void setPaintMode() { g.setPaintMode(); }
  public void setXORMode(Color c1) { g.setXORMode(c1); }
  public Font getFont() { return g.getFont(); }
  public void setFont(Font font) { g.setFont(font); }
  public FontMetrics getFontMetrics(Font f) { return g.getFontMetrics(f); }
  public Rectangle getClipBounds() { return g.getClipBounds(); }
  public void clipRect(int x, int y, int width, int height) { g.clipRect(x, y, width, height); }
  public void setClip(int x, int y, int width, int height) { g.setClip(x, y, width, height); }
  public Shape getClip() { return g.getClip(); }
  public void setClip(Shape clip) { g.setClip(clip); }
  public void copyArea(int x, int y, int width, int height, int dx, int dy) { g.copyArea(x, y, width, height, dx, dy); }
  public void drawLine(int x1, int y1, int x2, int y2) { g.drawLine(x1, y1, x2, y2); }
  public void fillRect(int x, int y, int width, int height) { g.fillRect(x, y, width, height); }
  public void clearRect(int x, int y, int width, int height) { g.clearRect(x, y, width, height); }
  public void drawRoundRect(int x, int y, int width, int height, int aw, int ah) { g.drawRoundRect(x, y, width, height, aw, ah); }
  public void fillRoundRect(int x, int y, int width, int height, int aw, int ah) { g.fillRoundRect(x, y, width, height, aw, ah); }
  public void drawOval(int x, int y, int width, int height) { g.drawOval(x, y, width, height); }
  public void fillOval(int x, int y, int width, int height) { g.fillOval(x, y, width, height); }
  public void drawArc(int x, int y, int width, int height, int start, int extent) { g.drawArc(x, y, width, height, start, extent); }
  public void fillArc(int x, int y, int width, int height, int start, int extent) { g.fillArc(x, y, width, height, start, extent); }
  public void drawPolyline(int[] xp, int[] yp, int n) { g.drawPolyline(xp, yp, n); }
  public void drawPolygon(int[] xp, int[] yp, int n) { g.drawPolygon(xp, yp, n); }
  public void fillPolygon(int[] xp, int[] yp, int n) { g.fillPolygon(xp, yp, n); }
  public boolean drawImage(Image img, int x, int y, ImageObserver obs) { return g.drawImage(img, x, y, obs); }
  public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver obs) { return g.drawImage(img, x, y, width, height, obs); }
  public boolean drawImage(Image img, int x, int y, Color bg, ImageObserver obs) { return g.drawImage(img, x, y, bg, obs); }
  public boolean drawImage(Image img, int x, int y, int width, int height, Color bg, ImageObserver obs) { return g.drawImage(img, x, y, width, height, bg, obs); }
  public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver obs) { return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs); }
  public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bg, ImageObserver obs) { return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bg, obs); }
  public void dispose() { g.dispose(); }
}
