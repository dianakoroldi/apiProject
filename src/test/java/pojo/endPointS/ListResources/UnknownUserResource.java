package pojo.endPointS.ListResources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnknownUserResource {

        private int id;
        private String name;
        private int year;
        private String color;
        private String pantone_value;
    }

