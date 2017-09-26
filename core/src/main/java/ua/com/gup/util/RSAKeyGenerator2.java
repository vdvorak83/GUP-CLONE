package ua.com.gup.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyGenerator2 {

    public static final int   LENGTH_PUBLIC_KEY = 2048;
    public static final String  ENCRYPTION_TYPE = "SHA-256";
    public static final String FILE_PRIVATE_KEY = "id_rsa";
    public static final String  FILE_PUBLIC_KEY = "id_rsa.pub";
    public static final String DESC_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String  DESC_PUBLIC_KEY = "RSA PUBLIC KEY";

    private KeyPair keyPair;
    private String publicKey;
    private String privateKey;

    public RSAKeyGenerator2()
            throws NoSuchProviderException, NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(LENGTH_PUBLIC_KEY);
        keyPair = generator.generateKeyPair();
    }

    public String getPublicHash(String publicKey)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        if (publicKey!=null){
            MessageDigest   msg = MessageDigest.getInstance(ENCRYPTION_TYPE);
            byte[]   bytePublic = publicKey.getBytes("UTF-8");
            byte[] digestPublic = msg.digest(bytePublic);
            return Hex.toHexString(digestPublic);
        }
        return null;
    }

    public String getPublicKey()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(FILE_PUBLIC_KEY)));
        PemObject obj = new PemObject(DESC_PUBLIC_KEY, keyPair.getPublic().getEncoded());
        writer.writeObject(obj);
        writer.close();

        publicKey = new String(Files.readAllBytes(Paths.get(FILE_PUBLIC_KEY)));
        if (new File(FILE_PUBLIC_KEY).delete()) return publicKey;
        return "Delete operation is failed";
    }

    public String getPrivateKey()
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        PemWriter writer = new PemWriter(new OutputStreamWriter(new FileOutputStream(FILE_PRIVATE_KEY)));
        PemObject obj = new PemObject(DESC_PRIVATE_KEY, keyPair.getPrivate().getEncoded());
        writer.writeObject(obj);
        writer.close();

        privateKey = new String(Files.readAllBytes(Paths.get(FILE_PRIVATE_KEY)));
        if (new File(FILE_PRIVATE_KEY).delete()) return privateKey;
        return "Delete operation is failed";
    }

    public PublicKey getPublicKey(String publicKey)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        if (publicKey!=null){
            PemReader reader = new PemReader( new InputStreamReader(new ByteArrayInputStream(publicKey.getBytes())) );
            PemObject    obj = reader.readPemObject();
            byte[]   content = obj.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return factory.generatePublic(pubKeySpec);
        }
        return null;
    }

    public PrivateKey getPrivateKey(String privateKey)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        if (privateKey!=null){
            PemReader reader = new PemReader( new InputStreamReader(new ByteArrayInputStream(privateKey.getBytes())) );
            PemObject    obj = reader.readPemObject();
            byte[]   content = obj.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return factory.generatePrivate(privKeySpec);
        }
        return null;
    }
}