package com.example.rent_product.scheduler;

import com.example.rent_product.entity.ClientEntity;
import com.example.rent_product.entity.ProductEntity;
import com.example.rent_product.repository.ClientRepository;
import com.example.rent_product.repository.ProductRepository;
import com.example.rent_product.service.EmailSenderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_product.constant.ConstApplication.MOSCOW;
import static com.example.rent_product.constant.ConstApplication.ST_PETERSBURG;

@Service
@EnableScheduling
@Slf4j
public class EventManagerScheduler {

    private final Logger logger = LoggerFactory.getLogger(EventManagerScheduler.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmailSenderServiceImpl emailSenderService;


    @Scheduled(fixedDelay = 90000)
    public void sendMassageProductInfo() {
        log.info("Шедулер отправил сообщения: " + LocalDateTime.now());

        logger.info("EventManagerScheduler -> sendMassageProductInfo()");

        List<ProductEntity> productList = productRepository.findAllByStatusActiveInactiveIsTrue();

        List<ClientEntity> clientList = clientRepository.findAllByFirstProductIsNull();

        checkTypeProduct(productList, clientList);

        logger.info("EventManagerScheduler <- sendMassageProductInfo()");
    }

    /**
     * распределение скидок для пользователей
     */
    private void checkTypeProduct(List<ProductEntity> productEntities, List<ClientEntity> clientEntities) {


        for (ProductEntity product : productEntities) {
            if (product.getId() == 1) {
                permanentDiscounts(productEntities, clientEntities);

            } else if (product.getId() == 4) {
                permanentDiscounts(productEntities, clientEntities);

            } else if (product.getId() == 5) {
                permanentDiscounts(productEntities, clientEntities);

            } else if (product.getId() == 6) {
                seasonalDiscounts(productEntities, clientEntities);

            } else if (product.getId() == 2) {
                seasonalDiscounts(productEntities, clientEntities);
            }
        }
    }

    /**
     * постоянные скидки для пользователей которые действуют круглый год
     */
    private void permanentDiscounts(List<ProductEntity> productEntities, List<ClientEntity> clientEntities) {
        for (ProductEntity product : productEntities) {
            if (product.getId() == 1) {
                List<ClientEntity> resultClientList = clientEntities.stream()
                        .filter(client -> client.getUsingCount() <= 9)
                        .peek(client -> client.setFirstProduct(product))
                        .collect(Collectors.toList());

                for (ClientEntity client : resultClientList) {
                    clientRepository.save(client);
                }

            } else if (product.getId() == 4) {

                List<ClientEntity> resultClientList = clientEntities.stream()
                        .filter(client -> client.getUsingCount() >= 10 && client.getUsingCount() <= 14)
                        .peek(client -> client.setSecondProduct(product))
                        .collect(Collectors.toList());

                for (ClientEntity client : resultClientList) {
                    clientRepository.save(client);
                }
            } else if (product.getId() == 5) {
                List<ClientEntity> resultClientList = clientEntities.stream()
                        .filter(client -> client.getUsingCount() >= 15)
                        .peek(client -> client.setFirstProduct(product))
                        .collect(Collectors.toList());

                for (ClientEntity client : resultClientList) {
                    clientRepository.save(client);
                }
            }
        }
    }

    /**
     * сезонные скидки для пользователей которые действуют в зависимости от времени года
     */
    private void seasonalDiscounts(List<ProductEntity> productEntities, List<ClientEntity> clientEntities) {
        for (ProductEntity product : productEntities) {
            if (product.getId() == 6) {
                LocalDateTime autumnStart = LocalDateTime.of(2023, Month.AUGUST, 30, 0, 0);
                LocalDateTime autumnStop = LocalDateTime.of(2023, Month.NOVEMBER, 30, 23, 59);


                List<ClientEntity> resultClientList = clientEntities.stream()
                        .filter(client -> client.getDateRegistration().isAfter(autumnStart)
                                && client.getDateRegistration().isBefore(autumnStop))
                        .filter(client -> !client.getParentCity().equals(MOSCOW))
                        .peek(client -> client.setSecondProduct(product))

                        .collect(Collectors.toList());

                for (ClientEntity client : resultClientList) {
                    clientRepository.save(client);
                }
            } else if (product.getId() == 2) {
                LocalDateTime summerStart = LocalDateTime.of(2023, Month.JUNE, 1, 0, 0);
                LocalDateTime summerStop = LocalDateTime.of(2023, Month.AUGUST, 29, 23, 59);

                List<ClientEntity> resultClientList = clientEntities.stream()
                        .filter(client -> client.getDateRegistration().isAfter(summerStart)
                                && client.getDateRegistration().isBefore(summerStop))
                        .filter(client -> !client.getParentCity().equals(ST_PETERSBURG))
                        .peek(client -> client.setSecondProduct(product))
                        .collect(Collectors.toList());

                for (ClientEntity client : resultClientList) {
                    clientRepository.save(client);
                }
            }
        }
    }
}