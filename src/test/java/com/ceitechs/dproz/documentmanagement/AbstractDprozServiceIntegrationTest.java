package com.ceitechs.dproz.documentmanagement;

import com.ceitechs.dproz.documentmanagement.config.DatabaseConfiguration;
import com.ceitechs.dproz.documentmanagement.config.ServiceConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author iddymagohe on 10/22/17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://rwUser:rwUserPass@localhost:27017/dproz-dev",
        "documentmanagement.db.host=localhost:27017",
        "documentmanagement.db.user=rwUser",
        "documentmanagement.db.password=rwUserPass",
        "documentmanagement.db.name=dproz-dev",
        "documentmanagement.magickey=5rGXHCU2yoGTn600Gz9i5A==",
        "documentmanagement.accesskey=AKIAJLBAJNUMLHZIUZEA",
        "documentmanagement.secretkey=9xinRhvCzMO10uBnmFa0IqSnQFTuNk9JjFlKxkHHOdYFRFJTagTG8wKs5i4X+dQK",
        "s3.signedurl.timeout.milliseconds=3600000", "s3.attachments.bucketname=dproz-attachments"
})
@ContextConfiguration(classes = {DatabaseConfiguration.class, ServiceConfiguration.class})
@Ignore
public class AbstractDprozServiceIntegrationTest {
    protected static final Resource resource = new ClassPathResource("ceitechs.png");
}
