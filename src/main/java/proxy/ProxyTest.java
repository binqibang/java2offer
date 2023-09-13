package proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        // ------------------ 静态代理 -------------------- //
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.sendMsg("java");
        System.out.println();

        // ------------------ JDK 动态代理 -------------------- //
        // 1. 封装工厂类
        SmsService smsService2 = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService2.sendMsg("python");
        System.out.println();
        // 2. 直接调用 Proxy 对应方法
        SmsService smsService3 = (SmsService) Proxy.newProxyInstance(
                smsService.getClass().getClassLoader(),
                smsService.getClass().getInterfaces(),
                new SmsInvocationHandler(smsService)
        );
        smsService3.sendMsg("golang");
        System.out.println();

        // ------------------ CGLIB 动态代理 -------------------- //
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.sendMsg("c++");
    }
}
