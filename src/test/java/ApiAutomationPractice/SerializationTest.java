package ApiAutomationPractice;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.Test;
import pojo.PetStoresPojo;

import java.io.File;
import java.io.IOException;

public class SerializationTest {


    @Test
    public void serializationPractice() throws IOException {
        PetStoresPojo petPojo = new PetStoresPojo();
        petPojo.setName("RainMan");
        petPojo.setId(199);
        petPojo.setStatus("Hold on");

        //SERIALIZATION (CONVERTING FROM JAVA CODE(SETTERS) to JSON
        ObjectMapper objectMapper=new ObjectMapper();

        File file=new File("src/test/resources/pet.json");
        objectMapper.writeValue(file,petPojo);

    }


}
