package com.example.rent_basic_apartment.service;

import com.example.rent_basic_apartment.model.dto.UserSession;
import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.model.entity.BookingHistoryEntity;
import com.example.rent_basic_apartment.model.entity.ClientEntity;
import com.example.rent_basic_apartment.product_manager.ProductManager;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import com.example.rent_basic_apartment.repository.BookingHistoryRepository;
import com.example.rent_basic_apartment.repository.ClientRepository;
import com.example.rent_basic_apartment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrepareToBookingService {

    private final ClientRepository clientRepository;
    private final BookingHistoryRepository bookingHistoryRepository;
    private final ProductManager productManager;
    private final UserSession session;


    /**
     * сохраняем в БД booking_history(дата начала и дата конец бронирования), так же apartment_id, client_id, product_id
     */
    public void writeToBookingHistory(ApartmentsEntity apartmentsEntity, LocalDateTime startBookingDate,
                                      LocalDateTime endBookingDate) {
        ClientEntity client = clientRepository.getClientEntityByUserEmail(session.getUserEmail());
        client.setUsingCount(client.getUsingCount() + 1);
        clientRepository.save(client);
        BookingHistoryEntity integrationValue = saveBookingToBase(apartmentsEntity, client,
                startBookingDate, endBookingDate);
        productManager.getProductForBooking(integrationValue.getId());
    }

    /**
     * подготавливаем объекты для БД booking_history
     */
    private BookingHistoryEntity saveBookingToBase(ApartmentsEntity apartmentsEntity,
                                                   ClientEntity clientEntity,
                                                   LocalDateTime startBookingDate,
                                                   LocalDateTime endBookingDate) {

        BookingHistoryEntity bookingHistoryEntity = new BookingHistoryEntity();


        bookingHistoryEntity.setApartmentsEntity(apartmentsEntity);
        bookingHistoryEntity.setClientEntity(clientEntity);
        bookingHistoryEntity.setStartBookingDate(startBookingDate);
        bookingHistoryEntity.setEndBookingDate(endBookingDate);

        bookingHistoryRepository.save(bookingHistoryEntity);

        return bookingHistoryEntity;
    }

    public List<BookingHistoryEntity> getListInfoForFileXL() {

        return bookingHistoryRepository.findAll();
    }
}
