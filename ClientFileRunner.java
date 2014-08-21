package ProService.Client;

/**
 * Created by 1 on 20.08.2014.
 */
public class ClientFileRunner {
    public static void main(String[] args) {

        int amount = 15;

        //long start = System.currentTimeMillis();
        for(int i = 0; i < amount; i++) {
            try {
                new Thread(new ClientFile()).start();
		/*long end = System.currentTimeMillis();
		System.out.println("Time " + (end - start));*/
            } catch (Exception E) {
            }
        }
            }
}
