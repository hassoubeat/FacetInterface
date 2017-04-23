/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hassoubeat;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

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
        Configurations configs = new Configurations();
        Configuration config;
        try {
            config = configs.properties(fullFilePath);
        } catch (ConfigurationException ex) {
            throw new ToyTalkException("プロパティの取得に失敗しました。", ex);
        }
        String param = config.getString(key);
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
        Configurations configs = new Configurations();
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configs.propertiesBuilder(fullFilePath);
        PropertiesConfiguration config;
        try {
            config = builder.getConfiguration();
            config.setProperty(key, param);
        } catch (ConfigurationException ex) {
            throw new ToyTalkException("プロパティの上書きに失敗しました。", ex);
        }
        try {
            builder.save();
        } catch (ConfigurationException ex) {
            throw new ToyTalkException("プロパティファイルの保存に失敗しました。", ex);
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
