package com.example.rent_product.service;

import com.example.rent_product.email_sender.EmailSender;
import com.example.rent_product.entity.ApartmentsEntity;
import com.example.rent_product.entity.BookingHistoryEntity;
import com.example.rent_product.entity.ClientEntity;
import com.example.rent_product.entity.ProductEntity;
import com.example.rent_product.repository.ApartmentsRepository;
import com.example.rent_product.repository.BookingHistoryRepository;
import com.example.rent_product.repository.ClientRepository;
import com.example.rent_product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ApartmentsRepository apartmentsRepository;
    @Autowired
    private EmailSender emailSender;

    /**
     * Отправка уведомлений пользователю на почту, бронирование апартаментов подтверждено.
     */
    @Override
    public void sendEmailToClient(Long id) {
        BookingHistoryEntity bookingHistoryEntity = bookingHistoryRepository.findById(id).get();
        ClientEntity c = bookingHistoryEntity.getClientEntity();
        ProductEntity p = bookingHistoryEntity.getProductEntity();
        ApartmentsEntity a = bookingHistoryEntity.getApartmentsEntity();

        String TEXT = "Апартаменты забронированы! \nПо адресу: " + a.getAddressEntity().getCity() +
                ", улица " + a.getAddressEntity().getStreet() + " " +  a.getAddressEntity().getApartmentsNumber() +
                "\nСо скидкой " + p.getDiscountAmount() + "%" +
                "\nБлагодарим, что воспользовались нашим сервисом, приятного отдыха :)";

        String MAIL_NAME = "Бронирование апартаментов";

        emailSender.sendMail(MAIL_NAME, TEXT, c.getUserEmail());
    }
}
