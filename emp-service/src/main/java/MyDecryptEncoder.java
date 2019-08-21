import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyDecryptEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
      System.out.println(encoder.encode("welcome1"));
	}

}
