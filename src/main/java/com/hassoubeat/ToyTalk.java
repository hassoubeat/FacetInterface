/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hassoubeat;

import java.io.IOException;

/**
 * Toytalkに喋らせるクラス
 * @author hassoubeat
 */
public class ToyTalk {

    static final private String TALKING_COMMAND = "jsay";
    
    /**
     * Toyに喋らせる。
     * @param talkString Toyに喋らせるクラス
     * @param isSerialize 読み上げ完了を待機するかを判定するフラグ(Trueで待機, falseで待たないで次の処理を実行)
     */
    static public void talking(String talkString, boolean isSerialize) {
        
        talkString = unTalkableStringReplace(talkString);
        
        Runtime runTime = Runtime.getRuntime();
        String command = TALKING_COMMAND + " " + talkString;
        try {
            Process process = runTime.exec(command);
            process.waitFor();
//            if (isSerialize) {
//                // プロセスの実行が完了するまで、待機する
//                process.waitFor();
//            }
        } catch (IOException | InterruptedException ex) {
            // 実行失敗時の挙動定義
            throw new ToyTalkException();
        }
    }
    
    /**
     * ToyTalkでしゃべれない文字を変換する
     * @param talkString 
     */
    static private String unTalkableStringReplace(String talkString) {
        // 読めない文字の変換と削除
        talkString = talkString.replace("\n", "");
        talkString = talkString.replace("\u0020", "");
        talkString = talkString.replace("#", "");
        talkString = talkString.replace("(", "");
        talkString = talkString.replace(")", "");
        return talkString;
    }
    
}
