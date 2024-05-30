package org.example;

import java.io.*;
import java.nio.charset.Charset;

public class IOTooll {

    public static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void write(String data, OutputStream output, Charset encoding) throws IOException {
        output.write(data.getBytes(encoding));
    }

    public static void closeQuietly(Closeable closeable){
        if(null == closeable){
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {

        }
    }

   public static String toString(final InputStream input, final Charset inputEncoding) throws IOException {
        final StringWriter output = new StringWriter();
        final InputStreamReader in = new InputStreamReader(input, inputEncoding);
        copy(in, output);
        return output.toString();
    }

    public static int copy(final Reader input, final StringWriter output) throws IOException {
        final long count = copyLarge(input, output, new char[DEFAULT_BUFFER_SIZE]);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(final Reader input, final StringWriter output, final char[] buffer) throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

}
