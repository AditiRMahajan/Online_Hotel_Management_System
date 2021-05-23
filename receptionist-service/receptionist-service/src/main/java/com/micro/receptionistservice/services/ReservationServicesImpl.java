package com.micro.receptionistservice.services;

import com.micro.receptionistservice.config.MessagingConfig;
import com.micro.receptionistservice.exception.IdNotFoundException;
import com.micro.receptionistservice.models.Reservation;
import com.micro.receptionistservice.repository.GuestRepository;
import com.micro.receptionistservice.repository.ReservationRepository;
import com.micro.receptionistservice.repository.RoomRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.*;

@Service
public class ReservationServicesImpl implements ReservationServices{

    Logger logger = LoggerFactory.getLogger(ReservationServicesImpl.class);

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private RabbitTemplate template;

    @Override
    public String makeReservation(int roomNumber, int guestid, int numberOfChildrens, int numberOfAdults, int numberOfDays, double rate,
                  String checkInDate, String checkOutDate) {
            logger.info("Entered Service MakeReservation()");
            Reservation make = new Reservation();
            var reserve = this.reservationRepo.findAll().size()+1;

            make.setReservationid(reserve);
            make.setRoomNumber(roomNumber);
            make.setGuests(this.guestRepository.findByGuestid(guestid));
            make.setNumberOfChildrens(numberOfChildrens);
            make.setNumberOfAdults(numberOfAdults);;
            make.setNumberOfDays(numberOfDays);
            make.setPaymentStatus("pending");
            //Function 7 -Set Rates
            double totalRate = rate * (numberOfAdults+numberOfChildrens+numberOfDays);
            make.setRate(totalRate);

            var fillroom = this.roomRepository.findByRoomNumber(roomNumber);
            fillroom.setCheckInDate(checkInDate);
            fillroom.setCheckOutDate(checkOutDate);
            fillroom.setStatus("Booked");
            this.roomRepository.save(fillroom);

            make.setRooms(fillroom);

            this.reservationRepo.save(make);

            template.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,make);

            return "Reservation is done with reservation id: "+reserve;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Reservation make) {
        System.out.println("Message recieved from reservation queue : " + make);
    }

    @Override
    public List<Reservation> viewAllReservations() {
        logger.info("Entered Service MakeReservation()");
        return this.reservationRepo.findAll();
    }

    @Override
    public Reservation findByReservationid(int reservationid) {
        logger.info("Entered Service findByReservationid()");
        try{
            this.reservationRepo.findByReservationid(reservationid);
        } catch(Exception e) {
            throw new IdNotFoundException("Reservation Id not found");
        }
        return this.reservationRepo.findByReservationid(reservationid);
    }
        
}
