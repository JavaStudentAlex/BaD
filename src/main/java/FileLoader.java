package main.java;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileLoader {
    private String name;
    private BinaryTree numbers;

    public FileLoader(){
        numbers=new BinaryTree();
        readFileName();
        readFile();
    }

    private void readFileName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inout please the file name : ");
        name = scanner.nextLine();
    }

    private void readFile(){
        try(BufferedReader reader = getReader()) {
            readNumbers(reader);
        } catch (IOException e) {
            System.out.println("Problems with file reading");
            e.printStackTrace();
        }
    }

    private BufferedReader getReader() throws IOException{
        return new BufferedReader(
                new InputStreamReader(
                    new BZip2CompressorInputStream(
                            new BufferedInputStream(
                                    Files.newInputStream(Paths.get(name))
                            )
                        )
                )
        );
    }

    private void readNumbers(BufferedReader reader) throws IOException{
        String buffer;
        int number;
        while((buffer=reader.readLine())!=null){
            number=Integer.parseInt(buffer);
            numbers.addData(number);
        }
    }

    public BinaryTree getNumbers(){
        return numbers;
    }
}
