package task3;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends FilterReader {

    private final char key;

    public DecryptReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {

        int c = super.read();

        if (c == -1) {
            return -1;
        }

        return c - key;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {

        int count = 0;

        for (int i = off; i < off + len; i++) {

            int c = read();

            if (c == -1) {
                return count == 0 ? -1 : count;
            }

            cbuf[i] = (char) c;
            count++;
        }

        return count;
    }
}