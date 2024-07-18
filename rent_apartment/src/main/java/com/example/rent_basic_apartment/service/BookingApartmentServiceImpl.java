package com.example.rent_basic_apartment.service;


import com.example.rent_basic_apartment.model.entity.ApartmentsEntity;
import com.example.rent_basic_apartment.model.entity.BookingHistoryEntity;
import com.example.rent_basic_apartment.repository.ApartmentsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.rent_basic_apartment.constant.ConstApplication.APARTMENT_BOOKED;
import static com.example.rent_basic_apartment.constant.ConstApplication.APARTMENT_NOT_AVAILABLE;

@Service
@RequiredArgsConstructor
public class BookingApartmentServiceImpl implements BookingApartmentService {

    private final Logger logger = LoggerFactory.getLogger(BookingApartmentServiceImpl.class);

    private final ApartmentsRepository apartmentsRepository;
    private final PrepareToBookingService prepareToBookingService;


    /**
     * бронирование апартаментов
     */
    @Override
    public String bookingApartmentsAndPayment(Long bookingApartmentId,
                                              LocalDateTime startBookingDate,
                                              LocalDateTime endBookingDate) {
        logger.info("BookingApartmentServiceImpl -> bookingApartmentsAndPayment()");

        ApartmentsEntity apartmentsEntity = apartmentsRepository.findById(bookingApartmentId).get();
        if (!apartmentsEntity.getAvailable()) {
            return APARTMENT_NOT_AVAILABLE;
        }
        apartmentsEntity.setBookingTo(endBookingDate);
        apartmentsEntity.setAvailable(false);
        apartmentsRepository.save(apartmentsEntity);

        prepareToBookingService.writeToBookingHistory(apartmentsEntity, startBookingDate, endBookingDate);

        logger.info("BookingApartmentServiceImpl <- bookingApartmentsAndPayment()");

        return APARTMENT_BOOKED;
    }

//    private List<BookingHistoryEntity> getListInfo() {
//
//        return prepareToBookingService.getListInfoForFileXL();
//
//    }

    @Override
    public String getBookingReports() {

        List<BookingHistoryEntity> listInfoForFileXL = prepareToBookingService.getListInfoForFileXL();

        File file = new File("C:\\Templates\\rent_apartment_report_temlates.xlsx");

        try (FileInputStream fileInputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = 1;

            for (BookingHistoryEntity b : listInfoForFileXL) {
                    Row row = sheet.createRow(rowNumber++);
                    row.createCell(0).setCellValue(b.getId());
                row.createCell(1).setCellValue(b.getStartBookingDate());
                row.createCell(2).setCellValue(b.getEndBookingDate().toString());
                row.createCell(3).setCellValue(b.getApartmentsEntity().toString());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
