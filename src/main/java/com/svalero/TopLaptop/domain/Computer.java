package com.svalero.TopLaptop.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Computer {

        private String id;
        private String brand;
        private String model;
        private String ram;
        private byte[] computerImage;
        private User user;

}
