package pe.edu.unfv.apirest.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import pe.edu.unfv.apirest.dto.payment_mercadopago.CheckoutResponse;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentMercadoPagoService {

    private static final String ACCESS_TOKEN = "APP_USR-1080852080829015-032312-6373e7cb23df1e06fb7bf90765b4512f-3288178496";
    private static final String NGROK_URL = "https://unethereal-pedate-azucena.ngrok-free.dev";

    public CheckoutResponse createCheckoutPreference(){
        try{
            MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title("Parlantes")
                    .quantity(3)
                    .currencyId("PEN")
                    .unitPrice(BigDecimal.valueOf(250))
                    .build();

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success(NGROK_URL + "/payment/success")
                    .failure(NGROK_URL + "/payment/failure")
                    .pending(NGROK_URL + "/payment/pending")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(List.of(itemRequest))
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .notificationUrl(NGROK_URL + "/api/webhooksmercadopago")
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return new CheckoutResponse(preference.getInitPoint());

        } catch (Exception e) {
            throw new RuntimeException("No se pudo generar la preferencia de pago: " + e);
        }
    }
}
