/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 *  类名：ServerMQTT
 *  功能：MQTT服务类
 *  创建人：stars
 *  创建时间：2019/11/18
 *  修改时间：2019/11/19
 *  修改内容：在原版本上继续抽象，分离
 *  
 *  成员：  URL：服务器地址
 *         ID：客户端ID
 *         userName：用户名
 *         password：密码
 *         qos：消息机制
 */
public class ServerMQTT {

    private String url = "tcp://shiyan.mqtt.iot.bj.baidubce.com:1883";
    private String id = "wesrfq23wfrsaf";
    private String userName = "shiyan/esp8266";
    private String password = "5FyadUaBpe0RKi7rWBiJQLj+YMFGbsW0/pHZ55KlqAM=";
    private int qos = 0;
    
    private MqttClient client;          //客户端对象
    private MqttConnectOptions options; //
    
   
    /************************测试方法************************/
    public void tests(){
        try {
            ServerMQTT sqMQTT = new ServerMQTT();
            sqMQTT.connect();
            sqMQTT.subscribe("kaiguan", new MyMessageListener());
        } catch (MqttException ex) {
            System.out.println("com.cc.mqtt.ServerMQTT.main()");
            ex.printStackTrace();
        }
    }
    /************************************************/
    
    /**
     * 方法名：mqttClient
     * 功能：创建一个可用于与MQTT服务器通信的MqttClient。
     * @param url：服务器地址 
     * @param id：客户端标识符，唯一
     * @return：MqttClient对象
     * @throws MqttException 
     */
    public MqttClient mqttClient(String url, String id) throws MqttException{
        return new MqttClient(url, id);
    }
    
    /**
     * 功能：创建MQTT连接
     * @throws MqttSecurityException
     * @throws MqttException 
     */
    public void connect() throws MqttSecurityException,MqttException{
        client = mqttClient(url, id);
        options = getOptions();
        client.setCallback(new MyMqttCallback());
        client.connect(options);
    }
    
    /**
     * 功能：创建MQTT连接
     * @param url :服务器地址
     * @param id：客户端标识符，唯一
     * @throws MqttSecurityException
     * @throws MqttException 
     */
    public void connect(String url, String id) throws MqttSecurityException,MqttException{
        client = mqttClient(url, id);
        options = getOptions();
        client.setCallback(new MyMqttCallback());
        client.connect(options);
    }
/*********************************订阅****************************************/
    /**
     * 功能：订阅一个主题，
     * @param topic：订阅主题
     * @param qos：订阅服务质量
     * @throws MqttException 
     */
    public void subscribe(String topic, int qos) throws MqttException{
        int[] qoss = {qos};
        String[] topics = {topic};
        client.subscribe(topics,qoss);
        System.out.println("订阅topic : :" + topic);
    }
    /**
     * 功能：订阅多个主题，每个主题都可以包含通配符。
     * @param topics ：要订阅的一个或多个主题，其中可以包含通配符。
     * @param qos ： 订阅每个主题的最高服务质量。
     * @throws MqttException 
     */
    public void subscribe(String[] topics, int[] qos) throws MqttException{
        client.subscribe(topics,qos);
        for(String topic : topics){
            System.out.println("订阅topic : :" + topic);
        }
    }
    /**
     * 功能： 订阅主题，其中可能包括使用QoS为1的通配符。
     * @param topic：订阅的主题
     * @param messageListener ：处理传入消息的回调
     * @throws MqttException 
     */
    public void subscribe(String topic,IMqttMessageListener messageListener) throws MqttException{
        client.subscribe(topic, messageListener);
        System.out.println("订阅dintyue  topic : :" + topic);
    }
/***********************************************************************/
    public void publish(String topic, String payload) throws MqttException,MqttPersistenceException{
        client.publish(topic, getMessage(payload));
        
    }
    
    public MqttMessage getMessage(String paylosd){
        MqttMessage message = new MqttMessage(paylosd.getBytes());
        message.setQos(qos);
        message.setRetained(true);
        return  message;
    }
    
    
    /**
     * 功能：返回一个用于控制客户端如何连接到服务器的选项集。
     * @return ：MqttConnectOptions
     */
    public MqttConnectOptions getOptions(){
        /**
         * MqttConnectOptions使用默认值构造一个新对象。默认值为：
         *   存活间隔为60秒
         *   清洁会议是真的
            邮件传递重试间隔为15秒
            连接超时时间为30秒
            未设置意愿消息
            使用标准的SocketFactory
         */
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        return  options;
    }
    /**
     * 
     * @param userName：用户名
     * @param password：密码
     * @return 
     */
    public MqttConnectOptions getOptions(String userName, String password){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        return  options;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
