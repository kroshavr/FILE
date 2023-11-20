package hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class InfoService {
    @Value("${server.port}")
    private String port;

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public String getPort() {
        logger.info("Запущен метод получения порта");
        return port;
    }

    public Integer getIntegerValue() {
        long start = System.currentTimeMillis();
        logger.info("Запущен метод получения целочисленнго значения");
        int sum = IntStream.range(1, 1_000_000+1)
                .parallel()
                .boxed()
                .limit(1_000_000)
                .reduce(0, Integer::sum);
        long finish = System.currentTimeMillis()-start;
        logger.info("Время выполнения метода получения целочисленного значения: " + finish);
        return sum;
    }
}
