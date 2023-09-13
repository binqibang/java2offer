package proxy;

public class SmsServiceImpl implements SmsService{
    @Override
    public void sendMsg(String msg) {
        System.out.println("sending [" + msg + "]");
    }
}
