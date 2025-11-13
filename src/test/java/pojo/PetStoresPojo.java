package pojo;

import ApiAutomationPractice.PetStore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetStoresPojo {

    private int id;
    private PetStoreCategoryPojo category;
    private String name;
    private List<String> photoUrls;
    private List<PetStoreTagsPojo> tags;
    private String status;
    private int code;
    private  String type;
    private  String message;




}
