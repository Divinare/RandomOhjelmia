/*     */ package Tetris;
/*     */ 
/*     */ import java.awt.Color;
/*     */ 
/*     */ public class Figure
/*     */ {
/*     */   public static final int SQUARE_FIGURE = 1;
/*     */   public static final int LINE_FIGURE = 2;
/*     */   public static final int S_FIGURE = 3;
/*     */   public static final int Z_FIGURE = 4;
/*     */   public static final int RIGHT_ANGLE_FIGURE = 5;
/*     */   public static final int LEFT_ANGLE_FIGURE = 6;
/*     */   public static final int TRIANGLE_FIGURE = 7;
/*  77 */   private SquareBoard board = null;
/*     */ 
/*  83 */   private int xPos = 0;
/*     */ 
/*  89 */   private int yPos = 0;
/*     */ 
/*  98 */   private int orientation = 0;
/*     */ 
/* 109 */   private int maxOrientation = 4;
/*     */ 
/* 115 */   private int[] shapeX = new int[4];
/*     */ 
/* 121 */   private int[] shapeY = new int[4];
/*     */ 
/* 126 */   private Color color = Color.white;
/*     */ 
/*     */   public Figure(int type)
/*     */     throws IllegalArgumentException
/*     */   {
/* 147 */     initialize(type);
/*     */   }
/*     */ 
/*     */   private void initialize(int type)
/*     */     throws IllegalArgumentException
/*     */   {
/* 169 */     this.board = null;
/* 170 */     this.xPos = 0;
/* 171 */     this.yPos = 0;
/* 172 */     this.orientation = 0;
/*     */ 
/* 175 */     switch (type) {
/*     */     case 1:
/* 177 */       this.maxOrientation = 1;
/* 178 */       this.color = Configuration.getColor("figure.square", "#ffd8b1");
/* 179 */       this.shapeX[0] = -1;
/* 180 */       this.shapeY[0] = 0;
/* 181 */       this.shapeX[1] = 0;
/* 182 */       this.shapeY[1] = 0;
/* 183 */       this.shapeX[2] = -1;
/* 184 */       this.shapeY[2] = 1;
/* 185 */       this.shapeX[3] = 0;
/* 186 */       this.shapeY[3] = 1;
/* 187 */       break;
/*     */     case 2:
/* 189 */       this.maxOrientation = 2;
/* 190 */       this.color = Configuration.getColor("figure.line", "#ffb4b4");
/* 191 */       this.shapeX[0] = -2;
/* 192 */       this.shapeY[0] = 0;
/* 193 */       this.shapeX[1] = -1;
/* 194 */       this.shapeY[1] = 0;
/* 195 */       this.shapeX[2] = 0;
/* 196 */       this.shapeY[2] = 0;
/* 197 */       this.shapeX[3] = 1;
/* 198 */       this.shapeY[3] = 0;
/* 199 */       break;
/*     */     case 3:
/* 201 */       this.maxOrientation = 2;
/* 202 */       this.color = Configuration.getColor("figure.s", "#a3d5ee");
/* 203 */       this.shapeX[0] = 0;
/* 204 */       this.shapeY[0] = 0;
/* 205 */       this.shapeX[1] = 1;
/* 206 */       this.shapeY[1] = 0;
/* 207 */       this.shapeX[2] = -1;
/* 208 */       this.shapeY[2] = 1;
/* 209 */       this.shapeX[3] = 0;
/* 210 */       this.shapeY[3] = 1;
/* 211 */       break;
/*     */     case 4:
/* 213 */       this.maxOrientation = 2;
/* 214 */       this.color = Configuration.getColor("figure.z", "#f4adff");
/* 215 */       this.shapeX[0] = -1;
/* 216 */       this.shapeY[0] = 0;
/* 217 */       this.shapeX[1] = 0;
/* 218 */       this.shapeY[1] = 0;
/* 219 */       this.shapeX[2] = 0;
/* 220 */       this.shapeY[2] = 1;
/* 221 */       this.shapeX[3] = 1;
/* 222 */       this.shapeY[3] = 1;
/* 223 */       break;
/*     */     case 5:
/* 225 */       this.maxOrientation = 4;
/* 226 */       this.color = Configuration.getColor("figure.right", "#c0b6fa");
/* 227 */       this.shapeX[0] = -1;
/* 228 */       this.shapeY[0] = 0;
/* 229 */       this.shapeX[1] = 0;
/* 230 */       this.shapeY[1] = 0;
/* 231 */       this.shapeX[2] = 1;
/* 232 */       this.shapeY[2] = 0;
/* 233 */       this.shapeX[3] = 1;
/* 234 */       this.shapeY[3] = 1;
/* 235 */       break;
/*     */     case 6:
/* 237 */       this.maxOrientation = 4;
/* 238 */       this.color = Configuration.getColor("figure.left", "#f5f4a7");
/* 239 */       this.shapeX[0] = -1;
/* 240 */       this.shapeY[0] = 0;
/* 241 */       this.shapeX[1] = 0;
/* 242 */       this.shapeY[1] = 0;
/* 243 */       this.shapeX[2] = 1;
/* 244 */       this.shapeY[2] = 0;
/* 245 */       this.shapeX[3] = -1;
/* 246 */       this.shapeY[3] = 1;
/* 247 */       break;
/*     */     case 7:
/* 249 */       this.maxOrientation = 4;
/* 250 */       this.color = Configuration.getColor("figure.triangle", "#a4d9b6");
/* 251 */       this.shapeX[0] = -1;
/* 252 */       this.shapeY[0] = 0;
/* 253 */       this.shapeX[1] = 0;
/* 254 */       this.shapeY[1] = 0;
/* 255 */       this.shapeX[2] = 1;
/* 256 */       this.shapeY[2] = 0;
/* 257 */       this.shapeX[3] = 0;
/* 258 */       this.shapeY[3] = 1;
/* 259 */       break;
/*     */     default:
/* 261 */       throw new IllegalArgumentException("No figure constant: " + type);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isAttached()
/*     */   {
/* 273 */     return this.board != null;
/*     */   }
/*     */ 
/*     */   public boolean attach(SquareBoard board, boolean center)
/*     */   {
/* 302 */     if (isAttached()) {
/* 303 */       detach();
/*     */     }
/*     */ 
/* 307 */     this.xPos = 0;
/* 308 */     this.yPos = 0;
/*     */ 
/* 311 */     int newX = board.getBoardWidth() / 2;
/*     */     int newY;
/* 312 */     if (center) {
/* 313 */       newY = board.getBoardHeight() / 2;
/*     */     } else {
/* 315 */       newY = 0;
/* 316 */       for (int i = 0; i < this.shapeX.length; i++) {
/* 317 */         if (getRelativeY(i, this.orientation) - newY > 0) {
/* 318 */           newY = -getRelativeY(i, this.orientation);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 324 */     this.board = board;
/* 325 */     if (!canMoveTo(newX, newY, this.orientation)) {
/* 326 */       this.board = null;
/* 327 */       return false;
/*     */     }
/*     */ 
/* 331 */     this.xPos = newX;
/* 332 */     this.yPos = newY;
/* 333 */     paint(this.color);
/* 334 */     board.update();
/*     */ 
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   public void detach()
/*     */   {
/* 345 */     this.board = null;
/*     */   }
/*     */ 
/*     */   public boolean isAllVisible()
/*     */   {
/* 356 */     if (!isAttached()) {
/* 357 */       return false;
/*     */     }
/* 359 */     for (int i = 0; i < this.shapeX.length; i++) {
/* 360 */       if (this.yPos + getRelativeY(i, this.orientation) < 0) {
/* 361 */         return false;
/*     */       }
/*     */     }
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean hasLanded()
/*     */   {
/* 376 */     return (!isAttached()) || (!canMoveTo(this.xPos, this.yPos + 1, this.orientation));
/*     */   }
/*     */ 
/*     */   public void moveLeft()
/*     */   {
/* 387 */     if ((isAttached()) && (canMoveTo(this.xPos - 1, this.yPos, this.orientation))) {
/* 388 */       paint(null);
/* 389 */       this.xPos -= 1;
/* 390 */       paint(this.color);
/* 391 */       this.board.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveRight()
/*     */   {
/* 403 */     if ((isAttached()) && (canMoveTo(this.xPos + 1, this.yPos, this.orientation))) {
/* 404 */       paint(null);
/* 405 */       this.xPos += 1;
/* 406 */       paint(this.color);
/* 407 */       this.board.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveDown()
/*     */   {
/* 419 */     if ((isAttached()) && (canMoveTo(this.xPos, this.yPos + 1, this.orientation))) {
/* 420 */       paint(null);
/* 421 */       this.yPos += 1;
/* 422 */       paint(this.color);
/* 423 */       this.board.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveAllWayDown()
/*     */   {
/* 436 */     int y = this.yPos;
/*     */ 
/* 439 */     if (!isAttached()) {
/* 440 */       return;
/*     */     }
/*     */ 
/*     */     do
/*     */     {
/* 445 */       y++;
/*     */     }
/* 444 */     while (canMoveTo(this.xPos, y + 1, this.orientation));
/*     */ 
/* 449 */     if (y != this.yPos) {
/* 450 */       paint(null);
/* 451 */       this.yPos = y;
/* 452 */       paint(this.color);
/* 453 */       this.board.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getRotation()
/*     */   {
/* 463 */     return this.orientation;
/*     */   }
/*     */ 
/*     */   public void setRotation(int rotation)
/*     */   {
/* 479 */     int newOrientation = rotation % this.maxOrientation;
/*     */ 
/* 482 */     if (!isAttached()) {
/* 483 */       this.orientation = newOrientation;
/* 484 */     } else if (canMoveTo(this.xPos, this.yPos, newOrientation)) {
/* 485 */       paint(null);
/* 486 */       this.orientation = newOrientation;
/* 487 */       paint(this.color);
/* 488 */       this.board.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void rotateRandom()
/*     */   {
/* 500 */     setRotation((int)(Math.random() * 4.0D) % this.maxOrientation);
/*     */   }
/*     */ 
/*     */   public void rotateClockwise()
/*     */   {
/* 511 */     if (this.maxOrientation == 1) {
/* 512 */       return;
/*     */     }
/* 514 */     setRotation((this.orientation + 1) % this.maxOrientation);
/*     */   }
/*     */ 
/*     */   public void rotateCounterClockwise()
/*     */   {
/* 526 */     if (this.maxOrientation == 1) {
/* 527 */       return;
/*     */     }
/* 529 */     setRotation((this.orientation + 3) % 4);
/*     */   }
/*     */ 
/*     */   private boolean isInside(int x, int y)
/*     */   {
/* 544 */     for (int i = 0; i < this.shapeX.length; i++) {
/* 545 */       if ((x == this.xPos + getRelativeX(i, this.orientation)) && (y == this.yPos + getRelativeY(i, this.orientation)))
/*     */       {
/* 548 */         return true;
/*     */       }
/*     */     }
/* 551 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean canMoveTo(int newX, int newY, int newOrientation)
/*     */   {
/* 571 */     for (int i = 0; i < 4; i++) {
/* 572 */       int x = newX + getRelativeX(i, newOrientation);
/* 573 */       int y = newY + getRelativeY(i, newOrientation);
/* 574 */       if ((!isInside(x, y)) && (!this.board.isSquareEmpty(x, y))) {
/* 575 */         return false;
/*     */       }
/*     */     }
/* 578 */     return true;
/*     */   }
/*     */ 
/*     */   private int getRelativeX(int square, int orientation)
/*     */   {
/* 592 */     switch (orientation % 4) {
/*     */     case 0:
/* 594 */       return this.shapeX[square];
/*     */     case 1:
/* 596 */       return -this.shapeY[square];
/*     */     case 2:
/* 598 */       return -this.shapeX[square];
/*     */     case 3:
/* 600 */       return this.shapeY[square];
/*     */     }
/* 602 */     return 0;
/*     */   }
/*     */ 
/*     */   private int getRelativeY(int square, int orientation)
/*     */   {
/* 617 */     switch (orientation % 4) {
/*     */     case 0:
/* 619 */       return this.shapeY[square];
/*     */     case 1:
/* 621 */       return this.shapeX[square];
/*     */     case 2:
/* 623 */       return -this.shapeY[square];
/*     */     case 3:
/* 625 */       return -this.shapeX[square];
/*     */     }
/* 627 */     return 0;
/*     */   }
/*     */ 
/*     */   private void paint(Color color)
/*     */   {
/* 639 */     for (int i = 0; i < this.shapeX.length; i++) {
/* 640 */       int x = this.xPos + getRelativeX(i, this.orientation);
/* 641 */       int y = this.yPos + getRelativeY(i, this.orientation);
/* 642 */       this.board.setSquareColor(x, y, color);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\admin\Desktop\tetris-1.2-bin.jar
 * Qualified Name:     net.percederberg.tetris.Figure
 * JD-Core Version:    0.6.0
 */