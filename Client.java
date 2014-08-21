package ProService.Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client {

    protected static final int port = 2313;
   BufferedReader in = null;
   PrintWriter out = null;
    private static Random rand = new Random();
    Socket socket;

    protected static int rCount;
    protected static int wCount;
    List<Integer> idList = new ArrayList<Integer>();


    protected Socket newSocket(int port) {
        while (true) {
            try {
                System.out.println("Current port is " + port);
                return new Socket("localhost", port);
            } catch (IOException e) {
                System.out.println("Socket fail " + port);
                try {
                    int wait = rand.nextInt(50);
                    Thread.sleep(wait);
                } catch (InterruptedException E) {
                    System.err.println(E);
                }
            }
        }
    }

    private int newPort() {
        try {
            try {
                Socket socket = newSocket(port);
                return new DataInputStream(socket.getInputStream()).readInt();
                // socket.close();
            } catch (Exception e) {
                try {
                    Thread.sleep(waitingTime());
                } catch (InterruptedException E) {
                    System.err.println(E);
                }
            }
        } finally {
            try {
                socket.close();
            } catch (IOException E) {
                System.out.println("Can't close the socket");
            }
        }
        return -1;
    }

    private int waitingTime() {
        Random random = new Random();
        return random.nextInt(50);
    }

    private void keyboardReader() {


        System.out.println("Correct input formats is \n 1.'rCount' 'wCount' 'elements of idList, split by space', if you have list of id \n" +
                "2.'rCount' 'wCount' 'minID' - 'maxID', if you have range if id. ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String string = br.readLine();
            getParameters(string);
            br.close();
        } catch (IOException E) {

        }
        /*Scanner in = new Scanner(System.in);
        int rCount = in.nextInt();
        int wCount = in.nextInt();
        List<Integer> idList = new ArrayList<Integer>();
        while (in.hasNextInt() && !in.next().equals(" - ")) {
            idList.add(in.nextInt());
        }
        // String delim = in.next();

        if (in.hasNext() && in.next().equals(" - ")) {
            in.skip("-");
            for (int i = idList.get(idList.size() - 1); i <= in.nextInt(); i++) {
                idList.add(i);
            }
        }*/
    }

    private void getParameters(String string){
        if (checkString(string) > 0) {
            String[] elem = string.split(" ");
            rCount = Integer.parseInt(elem[0]);
            wCount = Integer.parseInt(elem[1]);
            if (checkString(string) == 1){
                for (int i = Integer.parseInt(elem[2]); i <= Integer.parseInt(elem[4]); i++){
                    idList.add(i);
                }
            }
            else{
                for (int i = 2; i < elem.length; i++){
                    idList.add(Integer.parseInt(elem[i]));
                }
            }
          //  br.close();
        }
        else {
          //  br.close();
            System.out.println("Illegal number format. please try again.");
            keyboardReader();
        }
    }

    public boolean isInteger(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException E) {
            return false;
        }
    }

    public int checkString(String s) {
        int minlen = 3;
        String[] elem = s.split(" ");
        if (elem.length >= minlen && isInteger(elem[0]) && isInteger(elem[1]) && isInteger(elem[2])) {
            if (elem.length == minlen + 2 && elem[3].equals("-") && isInteger(elem[4])) {
                if (Integer.parseInt(elem[4]) > Integer.parseInt(elem[2])) {
                    return 1;
                }
            } else {
                for (int i = 3; i < elem.length; i++) {
                    if (isInteger(elem[i])) {
                        return 2;
                    }
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    protected void serverConnection(){
        try {
            keyboardReader();
            System.out.println("Client started");

            socket = newSocket(newPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();

            while((message != null) && message.length() != 0){
                out.println(message);
                System.out.println("Server says: " + in.readLine());
                message = in.readLine();
            }
        }
        catch(IOException e){
            System.out.println("Exception in client work");
            e.printStackTrace();
        }
        finally{
            try{
                in.close();
                out.flush();
                socket.close();
            } catch (IOException e) {
                System.out.println("Closing problems");
            }
        }
    }



   }
// проверку строки на правильность сделать
