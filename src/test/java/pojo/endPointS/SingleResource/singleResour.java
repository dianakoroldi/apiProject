package pojo.endPointS.SingleResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class singleResour {


        private int id;
        private String name;
        private String year;
        private String color;
        private String support;
    }

