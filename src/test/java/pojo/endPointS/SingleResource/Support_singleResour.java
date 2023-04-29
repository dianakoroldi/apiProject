package pojo.endPointS.SingleResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Support_singleResour {

        private String url;
        private String text;
    }
