package clapp.run.http;

public interface IKrypter {

  public byte[] encrypt(String string);
  public byte[] encrypt(byte[] bytes);

  public String decrypt(StringBuffer buffer);
}
