package org.example;

import java.io.*;
import java.nio.charset.Charset;

public class FileTooll
{
    public static final char EXTENSION_SEPARATOR = '.';

    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * 原理：FileOutputStream output.write
     * 强烈建议文件使用utf8
     * @param file
     * @param data
     * @param encoding 强烈建议使用utf8
     * @throws IOException
     */
    public static void writeStringToFile(final File file, final String data, final Charset encoding, final boolean append)
            throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file, append);
            IOTooll.write(data, out, encoding);
            out.close(); // don't swallow close Exception if copy completes normally
        } finally {
            IOTooll.closeQuietly(out);
        }
    }

    public static String readFileToString(final File file, final Charset encoding) throws IOException {
        // 使用了缓冲StringBuilderWriter
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return IOTooll.toString(in, encoding);
        } finally {
            IOTooll.closeQuietly(in);
        }
    }

    public static String getExtension(String filename) {
        // D:\名单\名册.txt
        // windows下默认反斜线作为路径分隔符，但也支持正斜线。
        filename = filename.replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR);
        // final int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        final int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
        return filename.substring(extensionPos + 1);
    }

    /**
     * a/b/c.txt --> c.txt
     * 原理：filename.substring(indexOfLastSeparator + 1)
     * @param filename
     * @return
     */
    public static String getName(String filename) {
        filename = filename.replace(WINDOWS_SEPARATOR, UNIX_SEPARATOR);
        final int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        return filename.substring(lastUnixPos + 1);
    }

    public static String[] listSubFileNameExcludeEmpty(File dir) {
        // 没有递归
        return dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                if (file.isDirectory()) {
                    final File[] files = file.listFiles();
                    return files == null || files.length == 0;
                } else {
                    return file.length() == 0;
                }
            }
        });
    }



    public static File create(String path, boolean isDir) throws IOException {
        File file = new File(path);

        if(isDir){
            if(file.exists()){
                if(!file.isDirectory()){
                    final String message =
                            "File "
                                    + path
                                    + " exists and is "
                                    + "not a directory. Unable to create directory.";
                    throw new IOException(message);
                }
                return file;
            } else {
                if (!file.mkdirs()) {
                    // Double-check that some other thread or process hasn't made
                    // the directory in the background
                    if (!file.isDirectory()) {
                        final String message =
                                "Unable to create directory " + path;
                        throw new IOException(message);
                    }
                }
                return file;
            }
            /*
            if (!file.mkdirs() && !file.isDirectory()) {
                throw new IOException("'" + path + "' directory cannot be created");
            }
             */
        }else {
            if (file.exists()) {
                if (!file.isFile()) {
                    final String message =
                            "File "
                                    + path
                                    + " exists and is "
                                    + "not a file. Unable to create file.";
                    throw new IOException(message);
                }
                return file;
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                }
                if (!file.isFile()) {
                    final String message =
                            "Unable to create file " + path;
                    throw new IOException(message);
                }
                return file;
            }
            /*
            if (!file.createNewFile() && !file.isFile()) {
                throw new IOException("'" + path + "' file cannot be created");
            }
            */
        }

    }

    public static void forceDelete(final File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.delete()) {
                if (!file.exists()) {
                    return;
                } else {
                    final String message =
                            "Unable to delete file: " + file;
                    throw new IOException(message);
                }
            }
        }
    }

    private static void deleteDirectory(final File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        //if (!isSymlink(directory)) {
        cleanDirectory(directory);
        //}

        if (!directory.delete()) {
            if(directory.exists()){
                final String message =
                        "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }

    public static void cleanDirectory(final File directory) throws IOException {
        final File[] files = directory.listFiles();

        IOException exception = null;
        for (final File file : files) {
            try {
                forceDelete(file);
            } catch (final IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }



}
