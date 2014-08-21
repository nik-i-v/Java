package ProService.Client;
import ru.javatalks.rmi.Server;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Random;

/**
 * Created by 1 on 17.08.2014.
 */
public class ClientFile extends Client implements  Runnable{
   // private static int rCount;
   // private static int wCount;
    //private static int[] idList;


    public ClientFile() throws IOException
    {
        /*Properties props = new Properties();
        String myJarPath = ClientFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File("Parameters.txt");
        String path = file.getAbsolutePath();
        System.out.println(path);
        System.out.println(myJarPath);
        props.load(new FileInputStream(new File(path)));

        rCount = Integer.valueOf(props.getProperty("rCount"));
        wCount = Integer.valueOf(props.getProperty("wCount"));
        System.out.print(rCount + "    " + wCount);

        // Предположим, что в настройках находится список целых через точку с запятой
       try {
           String[] ids = props.getProperty("idList").split(" ");

           idList = new int[ids.length];
           for (int i = 0; i < ids.length; ++i) {
               idList[i] = Integer.valueOf(ids[i]);
         //      System.out.println();
            //   System.out.print(idList[i] + " ");
           }
       } catch(Exception E){
           String[] ids = props.getProperty("idList").split(" - ");
           int minID = Integer.valueOf(ids[0]);
           int maxID = Integer.valueOf(ids[1]);
         //  System.out.println();
         //  System.out.println(minID + "    " + maxID);
           idList = new int[maxID-minID+1];
           int counter = 0;
           for (int i = minID; i <= maxID; i++){
               idList[counter++] = i;
                          }
           for (int count = 0; count <idList.length; count++){
               System.out.println("List" + idList[count] + " ");
           }
       }*/
    }

    public void fileReader() throws IOException{
       // Properties props = new Properties();
        //String myJarPath = ClientFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File("Parameters.txt");
        String path = file.getAbsolutePath();
       // System.out.println(path);
      //  System.out.println(myJarPath);
        FileReader f = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(f);
        String s = br.readLine();
        getParameters(s);
        br.close();
        f.close();
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
            try {
                fileReader();
            }
            catch (IOException E){

            }
        }
    }
@Override
    public void run(){
this.serverConnection();
}
   /* private class testClientThread implements Runnable {
        private Socket socket;
        private int rCounter = 0;
        private int wCounter = 0;
        private int threadCounter = 0;
        private int clientID = 1;
        private BufferedReader in;
        private PrintWriter out;

        public testClientThread(InetAddress addr) {
            System.out.println("Making client " + clientID);
            threadCounter++;
            try {
                socket = new Socket(addr, Server.PORT);
            } catch (IOException e) {
                System.err.println("Socket failed");
            }

            try {
                in = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                // Включаем автоматическое выталкивание:
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream())), true);
                //  start();
            } catch (IOException e) {
                // Сокет должен быть закрыт при любой
                // ошибке, кроме ошибки конструктора сокета:
                try {
                    socket.close();
                } catch (IOException e2) {
                    System.err.println("Socket not closed");
                }
            }
            // В противном случае сокет будет закрыт
            // в методе run() нити.
        }

        public void run() {
        }
    }

    protected static Socket createSocket (int port, InetAddress addr){
        while (true) {
            try {
                return new Socket(addr, port);
            } catch (Exception e) {
                try {
                    Random rand = new Random();
                    int wait = rand.nextInt(100);
                    Thread.sleep(wait);
                } catch (InterruptedException E) {
                    System.err.print(E);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException
    {
        InetAddress addr = InetAddress.getByName("localhost");
        int passNumber;
        try {
new ClientFile();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
    }
}*/}
