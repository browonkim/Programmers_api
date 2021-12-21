package TEST;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String getMessage(){
        return "THIS IS TEST~";
    }
}
