package com.micro.receptionistservice.services;

import org.slf4j.*;
import java.util.Properties;
import java.util.Random;

import com.micro.receptionistservice.models.Payment;
import com.micro.receptionistservice.repository.PaymentRepository;
import com.micro.receptionistservice.repository.ReservationRepository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReservationRepository reservationRepo;

    @Override
    public String addPayment(int reservationid, String payDate, String cardName, String cardNumber,
            String expiryDate, int cvv) {
                logger.info("Entered Service addPayment()");

                var make = this.reservationRepo.findByReservationid(reservationid);

                if(make.getPaymentStatus().equalsIgnoreCase("paid")) {
                    return "Payment is already done for given reservation id";

                }
                else{
            
                Payment pay = new Payment();   
                Random random = new Random();
                var random1 = random.nextInt(50-1)+1;
                var random2 = random.nextInt(122-97)+97;
                var transactionid = random1+Character.forDigit(10, random2);//((char)random2);
                //System.out.println(transactionid);      

                pay.setPaymentid(transactionid);
                pay.setReservationid(reservationid);

                pay.setTotalRate(make.getRate());
                pay.setPayDate(payDate);;     
                pay.setCardName(cardName);
                pay.setCardNumber(cardNumber);
                pay.setExpiryDate(expiryDate);
                pay.setCvv(cvv);
                make.setPaymentStatus("paid");
                this.reservationRepo.save(make);
                pay.setReservation(make);

                this.paymentRepository.save(pay);

                

            String email = "adihotels.book@gmail.com";
            String sender = "adihotels.book@gmail.com";
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
    
                protected PasswordAuthentication getPasswordAuthentication() {
    
                    return new PasswordAuthentication("adihotels.book@gmail.com", "bookhotel@789");
    
                }
    
            });
    
            session.setDebug(true);
    
            try 
            {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(sender));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
               message.setSubject("Booking Confirmation");
               message.setText("Hello, \nYour booking is confirmed at hotel AdiHotels, Pune. \nReference number is "+ reservationid+ "\n Room Details are: " + make.getRooms() + "\nAlso, your payment of Rs." + pay.getTotalRate() + " is successful! \nHAPPY STAY!!\n\nBest Regards.");
               Transport.send(message);
               System.out.println("Mail successfully sent");

        
            }
            catch (MessagingException mex) 
            {
               mex.printStackTrace();
            }
        
        return "Payment is successful for reservation id "+ reservationid + " with payment id: " + transactionid;
        }
    }
    
}
