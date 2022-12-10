# ECB-Implementation-java
ECB algorithm Implementation with java language

# How To Use It
```
String keys = "keys$#^js12";
ECB gd = new ECB();
byte[] enc = gd.enc("Example Plain Text1845$%&1927".getBytes(StandardCharsets.UTF_8),keys);

//To get Encryption data to string
System.out.println(new String(enc));

//Decrypt
System.out.println(gd.dec(enc,keys));
```
