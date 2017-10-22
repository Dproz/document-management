package com.ceitechs.dproz.documentmanagement.utils;

import com.ceitechs.dproz.documentmanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.shared.utils.DprozUtility;
import org.junit.Test;

import javax.crypto.SecretKey;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * @author  iddymagohe on 10/22/17.
 */
public class DprozUtilityTest extends AbstractDprozServiceIntegrationTest{

    @Test
    public void testSecretKeyEncryptDecrypt() throws Exception {
        SecretKey secretKey = DprozUtility.secretKey();
        String encryptedText = DprozUtility.encrypt("OmG", secretKey);
        assertTrue("OmG".equals(DprozUtility.decrypt(encryptedText, secretKey)));
    }

    @Test
    public void testGenerateIdAsString() {
        String id = DprozUtility.generateIdAsString();
        assertNotNull("Generated Id should not be null", id);
        assertTrue("Generated Id should not contain hyphens", !id.contains("-"));
    }

    @Test
    public void remainingDurationTest(){
        System.out.println(DprozUtility.remainingDurationBtnDateTimes(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        System.out.println(DprozUtility.remainingDurationBtnDateTimes(LocalDateTime.now(), LocalDateTime.now().plusDays(2).plusHours(1)));
        System.out.println(DprozUtility.remainingDurationBtnDateTimes(LocalDateTime.now(), LocalDateTime.now().plusDays(2).plusHours(1).plusMinutes(24)));
    }
}
