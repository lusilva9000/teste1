package br.com.fiap.pixvalidator.repository;

import br.com.fiap.pixvalidator.PixvalidatorApplication;
import br.com.fiap.pixvalidator.domain.PixInfo;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = PixvalidatorApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=fakeMyKeyId",
        "amazon.aws.secretkey=fakeSecretAccessKey" })
public class PixInfoRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    PixInfoRepository repository;

    private static final String EXPECTED_ID = "972797a6-a52f-11eb-bcbc-0242ac130002";
    private static final String EXPECTED_QRCODE_ID = "QRCODE";
    private static final BigDecimal EXPECTED_AMOUNT = BigDecimal.valueOf(100);
    private static final String EXPECTED_CHARGE_ID = "CHARGE";
    private static final String EXPECTED_STATUS = "VALIDATED";

    @BeforeTestMethod
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(PixInfo.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        repository.deleteAll();
    }

    @Test
    public void givenItemWithValidFields_whenRunFindAll_thenItemIsFound() {
        repository.deleteAll();

        repository.save(PixInfo.builder()
                .id(EXPECTED_ID)
                .qrCodeId(EXPECTED_QRCODE_ID)
                .amount(EXPECTED_AMOUNT)
                .chargeId(EXPECTED_CHARGE_ID)
                .status(EXPECTED_STATUS)
                .build());

        List<PixInfo> result = (List<PixInfo>) repository.findAll();

        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getId(), is(equalTo(EXPECTED_ID)));
        assertThat(result.get(0).getQrCodeId(), is(equalTo(EXPECTED_QRCODE_ID)));
        assertThat(result.get(0).getAmount(), is(equalTo(EXPECTED_AMOUNT)));
        assertThat(result.get(0).getChargeId(), is(equalTo(EXPECTED_CHARGE_ID)));
        assertThat(result.get(0).getStatus(), is(equalTo(EXPECTED_STATUS)));
    }
}