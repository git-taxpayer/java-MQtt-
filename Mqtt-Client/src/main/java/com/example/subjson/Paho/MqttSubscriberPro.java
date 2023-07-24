//package com.example.subjson.Paho;
//
//import org.bouncycastle.openssl.PEMKeyPair;
//import org.bouncycastle.openssl.PEMParser;
//import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.security.*;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//
//@Component
//public class MqttSubscriberPro implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//        String broker = "ssl://47.98.253.213:8883";
//        String clientId = "paho";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            // 创建 MQTT 客户端
//            MqttClient client = new MqttClient(broker, clientId, persistence);
//            MqttConnectOptions options = new MqttConnectOptions();
//
//            // 加载证书和私钥文件
//            String caCrtFile = "SSL\\root.crt";  // 替换为实际的CA证书文件路径
//            String crtFile = "SSL\\client.crt";  // 替换为实际的客户端证书文件路径
//            String keyFile = "SSL\\client.key";  // 替换为实际的客户端私钥文件路径
//            String password = "123456";  // 替换为实际的私钥密码
//            options.setSocketFactory(getSocketFactory(caCrtFile, crtFile, keyFile, password));
//            // 连接 MQTT 代理服务器并订阅主题
//            client.connect(options);
//            client.subscribe("#");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private SSLSocketFactory getSocketFactory(String caCrtFile, String crtFile, String keyFile, String password) throws Exception {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        // 加载CA证书
//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        FileInputStream caInputStream = new FileInputStream(caCrtFile);
//        X509Certificate caCert = (X509Certificate) cf.generateCertificate(caInputStream);
//
//        // 加载客户端证书
//        FileInputStream crtInputStream = new FileInputStream(crtFile);
//        X509Certificate cert = (X509Certificate) cf.generateCertificate(crtInputStream);
//
//        // 加载客户端私钥
//        PEMParser pemParser = new PEMParser(new InputStreamReader(new FileInputStream(keyFile)));
//        Object keyObject = pemParser.readObject();
//        pemParser.close();
//
//        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
//        KeyPair keyPair;
//        if (keyObject instanceof PEMKeyPair) {
//            keyPair = converter.getKeyPair((PEMKeyPair) keyObject);
//        } else if (keyObject instanceof KeyPair) {
//            keyPair = (KeyPair) keyObject;
//        } else {
//            throw new IllegalArgumentException("Invalid private key format");
//        }
//        // 创建SSL上下文
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, null, null);
//        context.init(null, new TrustManager[]{new CustomTrustManager(caCert)}, new SecureRandom());
//
//// 获取私钥
//        PrivateKey privateKey = keyPair.getPrivate();
//
//        return context.getSocketFactory();
//    }
//
//    private static class CustomTrustManager implements javax.net.ssl.X509TrustManager {
//        private final X509Certificate caCert;
//
//        public CustomTrustManager(X509Certificate caCert) {
//            this.caCert = caCert;
//        }
//
//        @Override
//        public void checkClientTrusted(X509Certificate[] chain, String authType) {
//            // 不验证客户端证书
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] chain, String authType) {
//            // 验证服务器证书是否由可信的CA签发
//            try {
//                for (X509Certificate cert : chain) {
//                    cert.verify(caCert.getPublicKey());
//                }
//            } catch (Exception e) {
//                throw new SecurityException("Invalid server certificate", e);
//            }
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[]{caCert};
//        }
//    }
//}
