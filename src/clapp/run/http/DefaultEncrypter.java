package clapp.run.http;

public class DefaultEncrypter implements IKrypter {

  @Override
  public byte[] encrypt(String string) {
    return string.getBytes();
  }

  @Override
  public byte[] encrypt(byte[] in) {
    return in;
  }

  @Override
  public String decrypt(StringBuffer buffer) {
    return buffer.toString();
  }
}
