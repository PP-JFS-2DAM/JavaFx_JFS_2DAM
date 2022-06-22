package com.svalero.TopLaptop.domain;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String id;
    private String name;
    private String surname;
    private String dni;
    private boolean vip;
    private float latitude;
    private float longitud;
    private byte[] userImage;

}
