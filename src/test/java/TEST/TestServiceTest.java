package TEST;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestServiceTest {
    DataSource mockDataSource;

    @InjectMocks
    UserService userService;

    @Test
    public void testGetMessageFromSource() {
        mockDataSource = mock(DataSource.class);
        userService = new UserService();
        doReturn("hello").when(mockDataSource)
                .getMessage("hi");
        // mockDataSoruce 의 getMessage에 "computer"라는 parameter를 주면
        // "hello"라는 문자열을 반환하도록 mock 을 설계하겠다.
        // doReturn은 mockiot의 static method이다.
        String actualMessage = userService.getMessageFromSource();
        assertEquals("hello", actualMessage);
    }
}
