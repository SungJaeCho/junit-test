package site.coding.junitproject.util;


// 추구후에 Mail 클래스가 완성되면 코드를 완성
public class MailSenderAdapter implements MailSender {

//    private Mail mail;
//
//    public MailSenderAdapter(Mail mail) {
//        this.mail = mail;
//    }

    @Override
    public boolean send() {
        return true;
    }
}
