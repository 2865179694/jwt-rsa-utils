package org.heimi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.Data;
import org.heimi.model.Payload;
import org.heimi.model.User;
import org.heimi.utils.JWT;
import org.heimi.utils.KeyGenerateUtils;
import org.heimi.utils.RsaUtils;

import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testGenerateKeyFile() throws NoSuchAlgorithmException, IOException {
        String pubFilename = "G:/key/rsa_key.pub";
        String priFilename = "G:/key/rsa_key";
        KeyGenerateUtils.generateKeyFile(pubFilename, priFilename, "heimibanfan", 2048);
    }

    public void testGetKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String pubFilename = "G:/key/rsa_key.pub";
        String priFilename = "G:/key/rsa_key";
        PublicKey publicKeyFromFile = RsaUtils.getPublicKeyFromFile(pubFilename);
        PrivateKey privateKeyFromFile = RsaUtils.getPrivateKeyFromFile(priFilename);
        System.out.println(publicKeyFromFile);
        System.out.println(privateKeyFromFile);
    }

    public void testGenerateToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String priFilename = "G:/key/rsa_key";
        PrivateKey privateKeyFromFile = RsaUtils.getPrivateKeyFromFile(priFilename);
        User user = new User();
        user.setUsername("heimibanfan");
        user.setRole("role_admin");
        String token = JWT.generateTokenSeconds(user, privateKeyFromFile, 60 * 60 * 24, "user");
        System.out.println(token);
    }

    public void testGetPayload() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoie1widXNlcm5hbWVcIjpcImhlaW1pYmFuZmFuXCIsXCJyb2xlXCI6XCJyb2xlX2FkbWluXCJ9IiwianRpIjoiWkRNellURXdZemN0TWpneE1DMDBOakE0TFRnME5qVXRaakV3WWpFek9UUTRZbU0zIiwiZXhwIjoxNjk5MzU2ODE1fQ.bpDrj_fEYtwP_e2KLnqdvHx53Sa53UQVSnmuveQVjW2UNiCKc1Pdb2W-l0pASYzVqTteGpttbyM54J8XPbr7zpxPKimjCS-G2KjM8r3OHlf2BJGmtYGuyvE5-Kb-JxKVT1XtraR9iZttalPxRJ3HeYZETdnRe6xpTfPc9pKFvjirGHSFD1supd6wjxq3vOKSa1ALRur6hV_qPIYtAANltNePSv_yq12AmKmxpV5XnoGPhKwzjM5Fsj0J06MelUjjPfqsx_zP6G-TKesJdj7jy-OdXHPO-qUmKT7Im3aSX2IolDLafaalosl_stUmjR0-oxlnkarxZdqviNhBaYAF5g";
        String pubFilename = "G:/key/rsa_key.pub";
        PublicKey publicKeyFromFile = RsaUtils.getPublicKeyFromFile(pubFilename);
        Payload<User> user = JWT.getPayload(token, publicKeyFromFile, User.class, "user");
        System.out.println(user);
    }


}
