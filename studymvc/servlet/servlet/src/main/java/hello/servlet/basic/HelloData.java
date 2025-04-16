package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter // 이 어노테이션이 아래 게터세터 다 해줌
public class HelloData {

    private String userName;
    private int age;


//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }


}
