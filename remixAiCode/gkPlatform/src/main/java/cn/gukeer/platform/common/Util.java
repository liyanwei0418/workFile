package cn.gukeer.platform.common;

import io.netty.buffer.ByteBuf;

import java.io.*;


public class Util {
    public static byte[] readBuffer(ByteBuf buffer){
        byte[] bs = new byte[buffer.readableBytes()];
        buffer.readBytes(bs);
        return bs;
    }

    public static byte[] object2Bytes(Object obj){
        byte[] bs = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            bs = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bs;
    }

    public static Object bytes2Object(byte[] bs){
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bs);
        ObjectInputStream ois = null;
        try {  
            ois = new ObjectInputStream(bis);  
            obj = ois.readObject();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } finally{  
            try {  
                ois.close();  
                bis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return obj;  
    }  
}  