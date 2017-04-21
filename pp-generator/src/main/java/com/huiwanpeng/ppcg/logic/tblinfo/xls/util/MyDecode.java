package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;

public class MyDecode {
    private static byte[] decodeChars = new byte[]{
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
   
    private static byte[] decode(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {
           
            do {
                b1 = decodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1) break;
           
            do {
                b2 = decodeChars
                        [data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1) break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
           
            do {
                b3 = data[i++];
                if (b3 == 61) return sb.toString().getBytes("iso8859-1");
                b3 = decodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1) break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
           
            do {
                b4 = data[i++];
                if (b4 == 61) return sb.toString().getBytes("iso8859-1");
                b4 = decodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1) break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }
    
    public static ImageIcon getImageAli(){
        try{
            return new ImageIcon(decode(DataUtil.getDataAli()));
        }catch(Exception ex){
            return null;
        }
    }
    
    public static ImageIcon getImageWX(){
        try{
            return new ImageIcon(decode(DataUtil.getDataWX()));
        }catch(Exception ex){
            return null;
        }
    }
    
    public static ImageIcon getImageLogo(){
        try{
            return new ImageIcon(decode(DataUtil.getDataLogo()));
        }catch(Exception ex){
            return null;
        }
    }
    
}
