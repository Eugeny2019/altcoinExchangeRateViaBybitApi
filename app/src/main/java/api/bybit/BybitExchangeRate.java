package api.bybit;

import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.market.InstrumentStatus;
import com.bybit.api.client.domain.market.request.MarketDataRequest;
import com.bybit.api.client.service.BybitApiClientFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BybitExchangeRate {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/api.key"));

        var client = BybitApiClientFactory.newInstance(properties.getProperty("API_KEY"), "").newAsyncMarketDataRestClient();
        var instrumentInfoRequest = MarketDataRequest.builder()
                .category(CategoryType.LINEAR)
                .symbol("BTCUSD")
                .instrumentStatus(InstrumentStatus.TRADING)
                .limit(500)
                .build();
        client.getMarketTickers(instrumentInfoRequest, System.out::println);
    }
}