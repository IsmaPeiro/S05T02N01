package cat.itacademy.s05.t02.n01.S05T02N01.services;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

public interface IJWTUtilityService {
    public String generateJWT (String nickname, String role) throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, JOSEException;
    
    public JWTClaimsSet parseJWT (String jwt) throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ParseException, JOSEException;

}
