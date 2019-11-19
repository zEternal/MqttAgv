/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MqttAgv;

import com.cc.mqtt.ServerMQTT;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author eternal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*try {
            ServerMQTT sqMQTT = new ServerMQTT();
            sqMQTT.connect();
            sqMQTT.subscribe("kaiguan", 0);
            sqMQTT.publish("kaiguan", "hellooooo");
        } catch (MqttException ex) {
            System.out.println("com.cc.mqtt.ServerMQTT.main()");
            ex.printStackTrace();
        }*/
         ServerMQTT sqMQTT = new ServerMQTT();
         sqMQTT.tests();
    }
    
}
