public class ECB {
    private byte[] toByte(int[] data){
        byte[] bytes = new byte[data.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) data[i];
        }
        return bytes;
    }

    private int[] toInt(byte[] data) {
        int intArr[] = new int[data.length];
        for(int i = 0; i < intArr.length; i++) {
            int ye = data[i];
            if(ye < 0) {
                ye = 256 + ye;
            }
            intArr[i] = ye;
        }
        return intArr;
    }

    public byte[] enc(byte[] msg, String key) {
        String msgDt = new String(msg);
        String keyDt = key;
        if (msgDt.length() > key.length()) {
            for (int y=0;y < Math.floor(msgDt.length()/key.length())+1;y++) {
                keyDt += key;
            }
        }
        keyDt = keyDt.substring(0, msgDt.length());
        int[] bin = new int[msgDt.length()];
        for(int y=0;y < msgDt.length();y++) {
            long msgb = (long)msgDt.codePointAt(y);
            long kyb = (long)keyDt.codePointAt(y);
            String kyk = String.format("%8s",Long.toBinaryString(msgb ^ kyb)).replaceAll(" ","0");
//            System.out.println(kyk);
            kyk += (kyk.charAt(0) == '0')?'0':'1';
            bin[y] = (int)Long.parseLong(kyk.substring(kyk.length()-8,9),2);
//            System.out.println((int)Long.parseLong(kyk.substring(kyk.length()-8,9),2));
        }

        return toByte(bin);
    }

    public String dec(byte[] msg, String key) {
        int[] msgDt = toInt(msg);
        String keyDt = key;
        if (msgDt.length > key.length()) {
            for (int y=0;y < Math.floor(msgDt.length/key.length())+1;y++) {
                keyDt += key;
            }
        }
        keyDt = keyDt.substring(0, msgDt.length);
        char[] bin = new char[msgDt.length];
        for(int y=0;y < msgDt.length;y++) {
            String msgu = String.format("%8s",Long.toBinaryString(msgDt[y])).replaceAll(" ","0");
            msgu = ((msgu.charAt(msgu.length()-1) == '0')?'0':'1') + msgu;
//            System.out.println(msgu.substring(0,8));
            long msgb = Long.parseLong(msgu.substring(0,8),2);
            long kyb = keyDt.codePointAt(y);
            bin[y] = (char)(msgb ^ kyb);
//            System.out.println(Long.parseLong(kyk,2));
        }
        String hj = "";
        for(char b : bin) {
            hj += b;
        }
        return hj;
    }
}
