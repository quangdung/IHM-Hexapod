import com.phidgets.*;
import com.phidgets.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        try {
            InterfaceKitPhidget device = new InterfaceKitPhidget();

            device.addAttachListener(new AttachListener() {
                public void attached(AttachEvent event) {
                    try {
                        System.out.println("A new device has been plugged in");
//                        System.out.println("InputCount : " + device.getInputCount());
//                        System.out.println("OuputCount : " + device.getOutputCount());
//                        System.out.println("SensorCount : " + device.getSensorCount());

                        device.setDataRate(0, 992);

                        System.out.println("DataRate : " + device.getDataRate(0));
                        System.out.println("DataRateMin : " + device.getDataRateMin(0));
                        System.out.println("DataRateMax : " + device.getDataRateMax(0));
//                        System.out.println("InputState : " + device.getInputState(0));

//                        System.out.println("Ratiometric : " + device.getRatiometric());
                        device.setSensorChangeTrigger(0, 10);
                        System.out.println("SensorChangeTrigger : " + device.getSensorChangeTrigger(0));

                    }
                    catch (PhidgetException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            device.addDetachListener(new DetachListener() {
                public void detached(DetachEvent event) {
                    System.out.println("Device has been plugged out");
                }
            });

            device.addSensorChangeListener(new SensorChangeListener() {
                int count = 0;

                public void sensorChanged(SensorChangeEvent sensorEvent) {
//                    System.out.println("New Value: " + sensorEvent.getValue());
                    try {
                        int sensorValue = device.getSensorValue(0);

                        if (sensorValue > 510) {
                            System.out.println("port 0 - value #" + (++count) + " : " + sensorValue);
                            System.out.println("I'm scared -_-' !!");
                        }

//                        System.out.println("port 5 : " + device.getSensorValue(5));
                    }
                    catch (PhidgetException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

            device.openAny();

            System.out.println("starting detect new sensor...");
            System.in.read();

            device.close();

//            System.out.println("new sensor connected");
//
            System.out.println("close sensor");
        }
        catch (PhidgetException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
