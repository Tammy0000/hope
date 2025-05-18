package org.isandy.hope.Utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpUtil {
    /**
     * 获取本机局域网 IPv4 地址（非回环、非虚拟、已启用）
     * @return 局域网 IP 地址，例如 192.168.x.x，如果获取失败则返回 127.0.0.1
     */
    public static String getLocalLanIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                // 排除回环接口、虚拟接口和未启用接口
                if (ni.isLoopback() || ni.isVirtual() || !ni.isUp()) continue;

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();

                    // 只返回 IPv4 且为内网地址
                    if (address instanceof Inet4Address && isPrivateIp(address.getHostAddress())) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // 如果没找到，返回回环地址
        return "127.0.0.1";
    }

    /**
     * 判断是否为常见的内网 IPv4 地址
     */
    private static boolean isPrivateIp(String ip) {
        return ip.startsWith("10.") ||
                ip.startsWith("172.") ||
                ip.startsWith("192.168.");
    }
}
