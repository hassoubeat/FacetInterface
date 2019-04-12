/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hassoubeat;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *
 * @author hassoubeat
 */
public class PropertyUtil {
    private static final PropertyUtil instance = new PropertyUtil();
    private static final String PROPERTY_FILE_PATH = "/usr/bin/toytalk/facet/properties/";
    
    /**
     * 指定したプロパティファイルから指定したkeyのパラメーターを取得する
     * @param filePath
     * @param key
     * @return 
     */
    public String load(String filePath, String key) {
        String fullFilePath = PROPERTY_FILE_PATH + filePath;
        String param = "";
        try{
            Properties prop = new Properties();
            prop.load(new InputStreamReader(new FileInputStream(fullFilePath), "UTF-8"));
            param = prop.getProperty(key);
        }
        catch(IOException ex){
            throw new ToyTalkException("プロパティの取得に失敗しました。", ex);
        } 
        return param;
    }
    
    /**
     * 指定したプロパティファイルに指定したkeyでパラメーターを保存する
     * @param filePath
     * @param key
     * @param param
     */
    public void save(String filePath, String key, String param) {
        String fullFilePath = PROPERTY_FILE_PATH + filePath;
        try {
            Properties prop = new Properties();
            prop.load(new InputStreamReader(new FileInputStream(fullFilePath), "UTF-8"));
            prop.setProperty(key, param);
            prop.store(new FileOutputStream(fullFilePath), "任意のコメント");
        } catch (UnsupportedEncodingException ex) {
            throw new ToyTalkException("プロパティの上書きに失敗しました。", ex);
        } catch (IOException ex) {
            throw new ToyTalkException("プロパティの上書きに失敗しました。", ex);
        }
    }
    
    /**
     * PropertyUtilのシングルトンインスタンスを取得する
     * @return 
     */
    public static PropertyUtil getInstace() {
        return instance;
    }
}
