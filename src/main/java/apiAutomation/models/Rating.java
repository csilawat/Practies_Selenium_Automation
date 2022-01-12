package apiAutomation.models;

import lombok.*;
//
//import javax.net.ssl.*;
//        javax.net.ssl.trustStorePassword

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {

    private double rate;
    private int count;

}
