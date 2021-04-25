package br.com.fiap.pixvalidator.service;

import br.com.fiap.pixvalidator.client.ChargeClient;
import br.com.fiap.pixvalidator.domain.PixInfo;
import br.com.fiap.pixvalidator.repository.PixInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PixValidatorServiceTest {

    @InjectMocks
    private PixValidatorService pixValidatorService;

    @Mock
    private PixInfoRepository pixInfoRepository;

    @Mock
    private ChargeClient chargeClient;

    private static final String EXPECTED_ID = "972797a6-a52f-11eb-bcbc-0242ac130002";
    private static final String EXPECTED_QRCODE_ID = "QRCODE";
    private static final BigDecimal EXPECTED_AMOUNT = BigDecimal.valueOf(100);
    private static final String EXPECTED_CHARGE_ID = "CHARGE";
    private static final String EXPECTED_STATUS = "VALIDATED";

    @Test
    public void givenValidPixId_whenRunGetById_thenReturnValidPixResponse() {
        PixInfo pixInfo = PixInfo.builder()
                .id(EXPECTED_ID)
                .qrCodeId(EXPECTED_QRCODE_ID)
                .amount(EXPECTED_AMOUNT)
                .chargeId(EXPECTED_CHARGE_ID)
                .status(EXPECTED_STATUS)
                .build();

        Mockito.when(pixInfoRepository.findById(EXPECTED_ID)).thenReturn(Optional.of(pixInfo));

        var pixResponse = pixValidatorService.getPixById(EXPECTED_ID).block();

        assertEquals(EXPECTED_ID, pixResponse.getId());
        assertEquals(EXPECTED_QRCODE_ID, pixResponse.getQrCodeId());
        assertEquals(EXPECTED_AMOUNT, pixResponse.getAmount());
        assertEquals(EXPECTED_CHARGE_ID, pixResponse.getChargeId());
        assertEquals(EXPECTED_STATUS, pixResponse.getStatus());

        Mockito.verify(pixInfoRepository, Mockito.times(1)).findById(EXPECTED_ID);
    }

}
