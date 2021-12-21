package TEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    DataSource datasource;
    public String getMessageFromSource(){
        return datasource.getMessage("browser");
    }
}
