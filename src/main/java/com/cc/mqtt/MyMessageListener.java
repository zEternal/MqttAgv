/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 *
 * @author eternal
 */
public class MyMessageListener implements IMqttMessageListener{

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        //接口实现接收数据
        System.out.println("Interface implementation to receive data:::" + mm.toString());
    }
    
}
