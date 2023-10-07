package network;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteOrderTest {
    public static void main(String[] args) {
        // 创建一个ByteBuffer，使用默认的主机字节序
        ByteBuffer buffer = ByteBuffer.allocate(4);

        // 设置字节序为大端字节序（Network Byte Order）
        buffer.order(ByteOrder.BIG_ENDIAN);

        // 写入一个整数值
        int value = 123456789;
        buffer.putInt(value);

        // 获取字节序为大端字节序的字节数组
        byte[] bytes = buffer.array();

        // 打印字节数组内容
        for (byte b : bytes) {
            System.out.printf("%02X ", b);
        }

        // 切换字节序为小端字节序
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        // 获取切换后的字节数组
        bytes = buffer.array();

        System.out.println();

        // 打印切换后的字节数组内容
        for (byte b : bytes) {
            System.out.printf("%02X ", b);
        }
    }
}
