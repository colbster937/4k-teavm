package mc4k;

import java.util.Random;

import dev.colbster937.java.applet.Applet;
import dev.colbster937.java.awt.Event;
import dev.colbster937.java.awt.image.BufferedImage;
import dev.colbster937.java.awt.image.DataBufferInt;

public class M extends Applet implements Runnable {
   public M(dev.colbster937.eagler.render.Canvas canvas, dev.colbster937.eagler.render.RenderContext ctx) {
      super(canvas, ctx);
   }

   private boolean[] k = new boolean[32767];
   private float xRot;
   private float yRot;
   private int xo;
   private int yo;

   @Override
   public void start() {
      new Thread(this).start();
   }

   @Override
   public void run() {
      try {
         Random var1 = new Random();
         BufferedImage var2 = new BufferedImage(160, 120, 1);
         int[] var3 = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();
         int[] var4 = new int[262144];
         var1.setSeed(18295169L);

         for (int var5 = 0; var5 < 262144; var5++) {
            int var6 = var5 / 64 % 64;
            var4[var5] = var1.nextInt(64) < var6 ? 1 : 0;
            if (var1.nextInt(100) == 0) {
               var4[var5] = 255;
            }
         }

         float var59 = 32.5F;
         float var60 = -2.5F;
         float var7 = 32.5F;
         float var8 = 0.0F;
         float var9 = 0.0F;
         float var10 = 0.0F;
         long var11 = System.currentTimeMillis();

         while (true) {
            float var13 = (float)Math.sin(this.xRot);
            float var14 = (float)Math.cos(this.xRot);
            float var15 = (float)Math.sin(this.yRot);
            float var16 = (float)Math.cos(this.yRot);

            while (System.currentTimeMillis() - var11 > 10L) {
               var11 += 10L;
               float var17 = 0.0F;
               float var18 = 0.0F;
               if (this.k[119]) {
                  var18 += 0.02F;
               }

               if (this.k[115]) {
                  var18 -= 0.02F;
               }

               if (this.k[97]) {
                  var17 -= 0.02F;
               }

               if (this.k[100]) {
                  var17 += 0.02F;
               }

               var8 *= 0.5F;
               var9 *= 0.99F;
               var10 *= 0.5F;
               var8 += var13 * var18;
               var10 += var14 * var18;
               var8 += var14 * var17;
               var10 -= var13 * var17;
               var9 += 0.003F;

               label145:
               for (int var19 = 0; var19 < 3; var19++) {
                  float var20 = var59 + var8 * ((var19 + 0) % 3 / 2);
                  float var21 = var60 + var9 * ((var19 + 1) % 3 / 2);
                  float var22 = var7 + var10 * ((var19 + 2) % 3 / 2);

                  for (int var23 = 0; var23 < 12; var23++) {
                     int var24 = (int)(var20 + (var23 >> 0 & 1) * 0.6F - 0.3F);
                     int var25 = (int)(var21 + ((var23 >> 2) - 1) * 0.8F + 0.65F);
                     int var26 = (int)(var22 + (var23 >> 1 & 1) * 0.6F - 0.3F);
                     if (var24 >= 0 && var25 >= 0 && var26 >= 0 && var24 < 64 && var25 < 64 && var26 < 64 && var4[var24 + var25 * 64 + var26 * 4096] > 0) {
                        if (var19 != 1) {
                           continue label145;
                        }

                        if (this.k[32] && var9 > 0.0F) {
                           var9 = -0.1F;
                           continue label145;
                        }

                        var9 = 0.0F;
                        continue label145;
                     }
                  }

                  var59 = var20;
                  var60 = var21;
                  var7 = var22;
               }
            }

            int var66 = 0;
            int var68 = 0;

            for (int var70 = 0; var70 < 160; var70++) {
               float var71 = (var70 - 80) / 60.0F;

               for (int var72 = 0; var72 < 120; var72++) {
                  float var73 = (var72 - 60) / 60.0F;
                  float var74 = 1.0F;
                  float var75 = var74 * var16 + var73 * var15;
                  float var76 = var73 * var16 - var74 * var15;
                  float var27 = var71 * var14 + var75 * var13;
                  float var28 = var76;
                  float var29 = var75 * var14 - var71 * var13;
                  float var30 = var59;
                  float var31 = var60;
                  float var32 = var7;
                  float var33 = var59 + var27 * 20.0F;
                  float var34 = var60 + var76 * 20.0F;
                  float var35 = var7 + var29 * 20.0F;
                  int var36 = (int)var33;
                  int var37 = (int)var34;
                  int var38 = (int)var35;
                  int var39 = (int)var59;
                  int var40 = (int)var60;
                  int var41 = (int)var7;
                  int var42 = 20;
                  int var43 = 0;
                  float var44 = 1.0F;
                  float var45 = 999.0F;
                  float var46 = 999.0F;
                  float var47 = 999.0F;

                  while (var42-- >= 0) {
                     float var48 = 999.0F;
                     float var49 = 999.0F;
                     float var50 = 999.0F;
                     float var51 = var33 - var30;
                     float var52 = var34 - var31;
                     float var53 = var35 - var32;
                     if (var36 > var39) {
                        var45 = var39 + 1;
                        var48 = (var45 - var30) / var51;
                     } else if (var36 < var39) {
                        var45 = var39;
                        var48 = (var45 - var30) / var51;
                     }

                     if (var37 > var40) {
                        var46 = var40 + 1;
                        var49 = (var46 - var31) / var52;
                     } else if (var37 < var40) {
                        var46 = var40;
                        var49 = (var46 - var31) / var52;
                     }

                     if (var38 > var41) {
                        var47 = var41 + 1;
                        var50 = (var47 - var32) / var53;
                     } else if (var38 < var41) {
                        var47 = var41;
                        var50 = (var47 - var32) / var53;
                     }

                     byte var54 = 0;
                     if (var48 < var49 && var48 < var50) {
                        var30 = var45 + var27 * 1.0E-4F;
                        var31 += var52 * var48;
                        var32 += var53 * var48;
                        var54 = 2;
                     } else if (var49 < var50) {
                        var30 += var51 * var49;
                        var31 = var46 + var28 * 1.0E-4F;
                        var32 += var53 * var49;
                        var54 = 0;
                     } else {
                        var30 += var51 * var50;
                        var31 += var52 * var50;
                        var32 = var47 + var29 * 1.0E-4F;
                        var54 = 1;
                     }

                     var39 = (int)var30;
                     var40 = (int)var31;
                     var41 = (int)var32;
                     if (var39 >= 0 && var40 >= 0 && var41 >= 0 && var39 < 64 && var40 < 64 && var41 < 64 && var4[var39 + var40 * 64 + var41 * 4096] > 0) {
                        var66 = (int)((var30 + var32) * 16.0F) & 15;
                        var68 = (int)(var31 * 16.0F) & 15;
                        if (var54 == 0) {
                           var66 = (int)(var30 * 16.0F) & 15;
                           var68 = (int)(var32 * 16.0F) & 15;
                        }

                        float var55 = (var30 - var59) / 10.0F;
                        float var56 = (var31 - var60) / 10.0F;
                        float var57 = (var32 - var7) / 10.0F;
                        var44 *= 1.0F - (float)Math.sqrt(var55 * var55 + var56 * var56 + var57 * var57);
                        var44 *= 1.0F - var54 * 0.2F;
                        var43 = (var66 ^ var68) * 8 + 128;
                        var43 |= var43 << 16 | var43 << 8;
                        break;
                     }
                  }

                  int var79 = (int)(var44 * 255.0F);
                  if (var79 < 0) {
                     var79 = 0;
                  }

                  if (var79 > 255) {
                     var79 = 255;
                  }

                  int var80 = (var43 >> 16 & 0xFF) * var79 / 255;
                  int var81 = (var43 >> 8 & 0xFF) * var79 / 255;
                  int var82 = (var43 & 0xFF) * var79 / 255;
                  var3[var70 + var72 * 160] = var80 << 16 | var81 << 8 | var82;
               }
            }

            Thread.sleep(4L);
            if (!this.isActive()) {
               return;
            }

            this.getGraphics().drawImage(var2, 0, 0, 640, 480, null);
         }
      } catch (Exception var58) {
      }
   }

   @Override
   public boolean handleEvent(Event var1) {
      boolean var2 = false;
      switch (var1.id) {
         case 401:
            var2 = true;
         case 402:
            this.k[var1.key] = var2;
            break;
         case 501:
            var2 = true;
            this.xo = var1.x;
            this.yo = var1.y;
         case 502:
            this.k[1] = var2;
            break;
         case 506:
            this.xRot = this.xRot + (var1.x - this.xo) / 160.0F;
            this.yRot = this.yRot - (var1.y - this.yo) / 160.0F;
            this.xo = var1.x;
            this.yo = var1.y;
      }

      return true;
   }
}
