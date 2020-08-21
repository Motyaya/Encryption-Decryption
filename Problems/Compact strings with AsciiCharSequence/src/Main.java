import java.util.*;

class AsciiCharSequence implements CharSequence {
    private byte[] byteArray;

    public AsciiCharSequence(byte[] byteArray){
        this.byteArray=byteArray;
    }

    @Override
    public int length() {
        return byteArray.length;
    }

    @Override
    public char charAt(int i) {
        return (char) byteArray[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return  new AsciiCharSequence(Arrays.copyOfRange(byteArray,i,i1));
    }

    @Override
    public String toString() {
        String newString = new String(byteArray);
        return newString;
    }
}