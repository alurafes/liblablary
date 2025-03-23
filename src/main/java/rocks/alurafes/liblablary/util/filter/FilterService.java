package rocks.alurafes.liblablary.util.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class FilterService {
    private final ObjectMapper objectMapper;

    public FilterService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Filter createFromString(String filterString) {
        try {
            return objectMapper.readValue(filterString, Filter.class);
        } catch (Exception e) {
            return new Filter();
        }
    }
}
