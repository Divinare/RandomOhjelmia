/*     */ package Tetris;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class Configuration
/*     */ {
/*  39 */   private static Hashtable config = new Hashtable();
/*     */ 
/*     */   public static String getValue(String key)
/*     */   {
/*  50 */     if (config.containsKey(key))
/*  51 */       return config.get(key).toString();
/*     */     try
/*     */     {
/*  54 */       return System.getProperty(key); } catch (SecurityException ignore) {
/*     */     }
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getValue(String key, String def)
/*     */   {
/*  72 */     String value = getValue(key);
/*     */ 
/*  74 */     return value == null ? def : value;
/*     */   }
/*     */ 
/*     */   public static void setValue(String key, String value)
/*     */   {
/*  84 */     config.put(key, value);
/*     */   }
/*     */ 
/*     */   public static Color getColor(String key, String def)
/*     */   {
/* 102 */     String value = getValue("tetris.color." + key, def);
/*     */ 
/* 105 */     Color color = parseColor(value);
/* 106 */     if (color != null) {
/* 107 */       return color;
/*     */     }
/* 109 */     color = parseColor(def);
/* 110 */     if (color != null) {
/* 111 */       return color;
/*     */     }
/* 113 */     return Color.white;
/*     */   }
/*     */ 
/*     */   private static Color parseColor(String value)
/*     */   {
/* 127 */     if (!value.startsWith("#"))
/* 128 */       return null;
/*     */     try
/*     */     {
/* 131 */       return new Color(Integer.parseInt(value.substring(1), 16)); } catch (NumberFormatException ignore) {
/*     */     }
/* 133 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\admin\Desktop\tetris-1.2-bin.jar
 * Qualified Name:     net.percederberg.tetris.Configuration
 * JD-Core Version:    0.6.0
 */