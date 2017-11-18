import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConfigUpdater {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ActiveKeyValueStore store = new ActiveKeyValueStore();
        store.connect("localhost");

        Random rand = new Random();
        while(true){
            String value = rand.nextInt()+"";
            store.write("/config",value);
            System.out.printf("Set %s to %s\n","/config",value);
           // store.write();
            TimeUnit.SECONDS.sleep(1);
        }

        //store.close();
    }
}
