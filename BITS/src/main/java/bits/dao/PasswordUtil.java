package bits.dao;
import javax.crypto.SecretKeyFactory; import javax.crypto.spec.PBEKeySpec; import java.security.SecureRandom; import java.util.Base64;
final class PasswordUtil {
 private PasswordUtil(){}
 static String hash(String password){try{byte[] salt=new byte[16];new SecureRandom().nextBytes(salt);byte[] hash=derive(password.toCharArray(),salt);return Base64.getEncoder().encodeToString(salt)+":"+Base64.getEncoder().encodeToString(hash);}catch(Exception e){throw new IllegalStateException("Could not hash password",e);}}
 static boolean verify(String password,String stored){try{String[] p=stored.split(":");byte[] salt=Base64.getDecoder().decode(p[0]);byte[] expected=Base64.getDecoder().decode(p[1]);return java.security.MessageDigest.isEqual(expected,derive(password.toCharArray(),salt));}catch(Exception e){return false;}}
 private static byte[] derive(char[] p,byte[] salt)throws Exception{PBEKeySpec spec=new PBEKeySpec(p,salt,120000,256);try{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();}finally{spec.clearPassword();}}
}
