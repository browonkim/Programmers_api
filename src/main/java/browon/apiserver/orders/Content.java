package browon.apiserver.orders;

import java.util.Objects;

//현재는 설정이 바뀌어서 object를 못받아냄
// 그런데 spring boot에서는 알아서 처리한다는 이야기가 있어서 찾아보기로 했음
public class Content {
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public Content() {
    }

    public Content(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()){
            return false;
        }
        //Type casting
        Content targetContent = (Content) o;
        //Objects.equals(a,b) 는 만약 a와 b 둘다 *null* 이면 true를 반환하고
        //a가 null이 아닌 경우 a.equals(b)를 수행한다.
        //즉 a가 null 일 수 있는 상황에서 쓰면 유용할 것이다.
        return Objects.equals(content, targetContent.getContent());

    }
}
