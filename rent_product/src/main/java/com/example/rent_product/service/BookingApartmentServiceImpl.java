package com.example.rent_product.service;

import com.example.rent_product.entity.ApartmentsEntity;
import com.example.rent_product.entity.BookingHistoryEntity;
import com.example.rent_product.entity.ClientEntity;
import com.example.rent_product.entity.ProductEntity;
import com.example.rent_product.repository.ApartmentsRepository;
import com.example.rent_product.repository.BookingHistoryRepository;
import com.example.rent_product.repository.ClientRepository;
import com.example.rent_product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.rent_product.constant.ConstApplication.*;
import static java.time.Month.*;

@Service
@RequiredArgsConstructor
public class BookingApartmentServiceImpl implements BookingApartmentService {

    private final Logger logger = LoggerFactory.getLogger(BookingApartmentServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ApartmentsRepository apartmentsRepository;
    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;
    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    private BookingHistoryEntity bookingHistoryEntity;


    /**
     * определяем скидку, максимально подходящую для пользователя
     */
    @Override
    public void productDiscount(Long id) {

        BookingHistoryEntity bookingHistoryEntity = bookingHistoryRepository.findById(id).get();

        ApartmentsEntity apartments = apartmentsRepository.findById(bookingHistoryEntity.getApartmentsEntity().getId())
                .orElseThrow(() -> new RuntimeException(NO_APARTMENTS));


        ClientEntity clientDiscount = clientRepository.findById(bookingHistoryEntity.getClientEntity().getId())
                .orElseThrow(() -> new RuntimeException(NO_SUCH_USER));

        int productDiscount = Math.max(clientDiscount.getFirstProduct().getDiscountAmount(),
                clientDiscount.getSecondProduct().getDiscountAmount());
        if (clientDiscount.getFirstProduct().getDiscountAmount() == productDiscount) {
            ProductEntity productEntity = productRepository.findById(clientDiscount.getFirstProduct().getId()).get();
            bookingHistoryEntity.setProductEntity(productEntity);
            bookingHistoryRepository.save(bookingHistoryEntity);
        } else {
            ProductEntity productEntity = productRepository.findById(clientDiscount.getSecondProduct().getId()).get();
            bookingHistoryEntity.setProductEntity(productEntity);
            bookingHistoryRepository.save(bookingHistoryEntity);
        }


        List<ProductEntity> resultDiscountList = productRepository.findAll().stream()
                .filter(p -> p.getDiscountAmount() > productDiscount)
                .collect(Collectors.toList());
        if (resultDiscountList.isEmpty()) {
            if (clientDiscount.getFirstProduct().getDiscountAmount() == productDiscount) {
                ProductEntity productEntity = productRepository.findById(clientDiscount.getFirstProduct().getId()).get();
                bookingHistoryEntity.setProductEntity(productEntity);
            } else {
                ProductEntity productEntity = productRepository.findById(clientDiscount.getSecondProduct().getId()).get();
                bookingHistoryEntity.setProductEntity(productEntity);
            }
        } else {
            for (ProductEntity discount : resultDiscountList) {
                if (discount.getSeasonalityOfOffers().equals(AUTUMN)) {
                    checkDiscountForAutumn(discount, clientDiscount);
                }
                if (discount.getSeasonalityOfOffers().equals(SUMMER)) {
                    checkDiscountsForSummer(discount, clientDiscount);
                }
                if (discount.getId() == 4) {
                    if (clientDiscount.getUsingCount() >= 10 && clientDiscount.getUsingCount() <= 14) {
                        bookingHistoryEntity.setProductEntity(discount);
                        bookingHistoryRepository.save(bookingHistoryEntity);
                        emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                    }
                }
                if (discount.getId() == 5) {
                    if (clientDiscount.getUsingCount() >= 15) {
                        bookingHistoryEntity.setProductEntity(discount);
                        bookingHistoryRepository.save(bookingHistoryEntity);
                        clientDiscount.setUsingCount(clientDiscount.getUsingCount() + 1);
                        emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                    }
                }
                if (discount.getId() == 8) {
                    Duration between = Duration.between(bookingHistoryEntity.getStartBookingDate(),
                            bookingHistoryEntity.getEndBookingDate());
                    long days = between.toDays();
                    if (days >= 5 & days <= 14) {
                        bookingHistoryEntity.setProductEntity(discount);
                        bookingHistoryRepository.save(bookingHistoryEntity);
                        emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                    }
                }
                if (discount.getId() == 9) {
                    Duration between = Duration.between(bookingHistoryEntity.getStartBookingDate(),
                            bookingHistoryEntity.getEndBookingDate());
                    long days = between.toDays();
                    if (days >= 14 && days <= 30) {
                        bookingHistoryEntity.setProductEntity(discount);
                        bookingHistoryRepository.save(bookingHistoryEntity);
                        emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                    }
                }
                if (discount.getId() == 10) {
                    Duration between = Duration.between(bookingHistoryEntity.getStartBookingDate(),
                            bookingHistoryEntity.getEndBookingDate());
                    long days = between.toDays();
                    if (days >= 30) {
                        bookingHistoryEntity.setProductEntity(discount);
                        bookingHistoryRepository.save(bookingHistoryEntity);
                        clientDiscount.setUsingCount(clientDiscount.getUsingCount() + 1);
                        clientRepository.save(clientDiscount);
                        emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                    }
                }
            }
        }
    }
    /**
     * проверяем сезонную скидку на осень
     */
    private void checkDiscountForAutumn(ProductEntity discount, ClientEntity client) {

        logger.info("BookingApartmentServiceImpl -> checkDiscountForAutumn()");

        Month month = LocalDate.now().getMonth();
        if (month.equals(SEPTEMBER) || month.equals(OCTOBER) || month.equals(NOVEMBER)) {
            if (discount.getId() == 6) {
                if (!client.getParentCity().equals(MOSCOW)) {
                    bookingHistoryEntity.setProductEntity(discount);
                    bookingHistoryRepository.save(bookingHistoryEntity);
                    emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
                }
            }
        }
        logger.info("BookingApartmentServiceImpl <- checkDiscountForAutumn()");
    }

    /**
     * проверяем сезонную скидку на лето
     */
    private void checkDiscountsForSummer(ProductEntity discount, ClientEntity client) {

        logger.info("BookingApartmentServiceImpl -> checkDiscountsForSummer()");

        Month month = LocalDate.now().getMonth();
        if (month.equals(JUNE) || month.equals(JULY) || month.equals(AUGUST)) {
            bookingHistoryEntity.setProductEntity(discount);
            bookingHistoryRepository.save(bookingHistoryEntity);
            client.setUsingCount(client.getUsingCount() + 1);
            emailSenderService.sendEmailToClient(bookingHistoryEntity.getId());
        }
        logger.info("BookingApartmentServiceImpl <- checkDiscountsForSummer()");
    }


}


