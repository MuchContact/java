package security;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Salt {
    public void run() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 读入账号口令
        String name = "lbwleon.info";
        String passwd = "lbwleon.info";
        // 生成盐
        Random rand = new Random();
        byte[] salt = new byte[12];
        rand.nextBytes(salt);
        // 计算消息摘要
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(salt);
        m.update(passwd.getBytes("UTF8"));
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
                    .substring(6);
        }
//        // 保存账号、盐和消息摘要
//        PrintWriter out = new PrintWriter(new FileOutputStream(
//                "salt.txt"));
        PrintStream out = System.out;

        out.println(name);
        for (int i = 0; i < salt.length; i++) {
            out.print(salt[i] + ",");
        }
        out.println("");

        out.println(result);
        out.close();

    }
}
