package jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtTest {

    private static final Long userId=12345678987654321L;
    @Test
    public void testCreate() throws InterruptedException {
        String token = JwtToken.createToken(userId);
        System.out.println(token);

        TimeUnit.SECONDS.sleep(2);

        System.out.println(JwtToken.isExpire(token));
        if(JwtToken.isExpire(token)){
            System.out.println(JwtToken.isExpire(token));
        }else {
            Map<String, Claim> result = JwtToken.verifyToken(token);
            System.out.println(result.get("userId").asString());
            System.out.println(result.get("exp").asLong());
            result.keySet().forEach(key -> System.out.println(key));
        }
    }

    @Test
    public void t(){
        DecodedJWT decode = JwtToken.decode(null);
        System.out.println(decode);
    }

}
