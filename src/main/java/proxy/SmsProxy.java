package proxy;

public class SmsProxy implements SmsService{
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void sendMsg(String msg) {
        // 调用方法之前，我们可以添加自己的操作
        System.out.println("before sendMsg()");
        smsService.sendMsg(msg);
        // 调用方法之后，我们同样可以添加自己的操作
        System.out.println("after sendMsg()");
    }
}
