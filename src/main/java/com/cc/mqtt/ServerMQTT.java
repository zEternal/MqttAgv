/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author eternal
 */
public class ServerMQTT {

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }

    private String URL = "tcp://shiyan.mqtt.iot.bj.baidubce.com:1883";
    private String ID = "wesrfq23wfrsaf";
    private String userName = "shiyan/esp8266";
    private String password = "5FyadUaBpe0RKi7rWBiJQLj+YMFGbsW0/pHZ55KlqAM=";
    private int qos = 0;
    
    private MqttClient client;
    private MqttConnectOptions options;
    
   
    
    public MqttClient mqttClient(String url, String id) throws MqttException{
        return new MqttClient(url, id);
    }
    
    public void connect() throws MqttSecurityException,MqttException{
        client = mqttClient(URL, ID);
        options = getOptions();
        client.setCallback(new MyMqttCallback());
        client.connect(options);
    }
    
    public void subscribe(String topic, int qos) throws MqttException{
        int[] qoss = {qos};
        String[] topics = {topic};
        client.subscribe(topics,qoss);
        System.out.println("订阅topic : :" + topic);
    }
    
    public void publish(String topic, String payload) throws MqttException,MqttPersistenceException{
        client.publish(topic, getMessage(payload));
        
    }
    
    public MqttMessage getMessage(String paylosd){
        MqttMessage message = new MqttMessage(paylosd.getBytes());
        message.setQos(qos);
        message.setRetained(true);
        return  message;
    }
    
    public MqttConnectOptions getOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        
        return  options;
    }
}
