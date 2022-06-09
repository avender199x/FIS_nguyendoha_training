package testNIO;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class NIOTest {
    @Test
    public void tryFileList(){
        try(Stream<Path> files = Files.list(Paths.get("."))){
            files.forEach(line -> System.out.println(line));
        } catch (IOException e){
            System.out.println("Message: " + e.getMessage());
        }
    }

    @Test
    public void tryFileWalk(){
        try(Stream<Path> files = Files.walk(Paths.get("D:/temp"))){
            files
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        }catch (IOException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                    });
        }
        catch (Exception e){
            System.out.println("Message: " + e.getMessage());
        }
    }

    @Test
    public void tryBufferedRead(){
        try(BufferedReader bReader =
            new BufferedReader(new FileReader("./tempest.txt"))){
            bReader.lines().forEach(line -> System.out.println("Line: " + line));
        } catch (IOException e){
            System.out.println("Message: " + e.getMessage());
        }
    }

    @Test
    public void tryNIORead(){
        try(Stream<String> lines = Files.lines(Paths.get("./tempest.txt"))){
            lines.forEach(line ->
                System.out.println("Line: " + line));
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    public void tryNIOList(){
        Path file = Paths.get("./tempest.txt");
        List<String> fileArr;

        try{
            fileArr = Files.readAllLines(file);
            fileArr.stream()
                .filter(line -> line.contains("Demo"))
                .forEach(line -> System.out.println(line));

        } catch (IOException e){
            System.out.println("Message: " + e.getMessage());
        }
    }
}
