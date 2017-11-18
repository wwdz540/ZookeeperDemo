import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.function.Consumer;

public class ConfigReader {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
       final ActiveKeyValueStore store = new ActiveKeyValueStore();
        store.connect("localhost");

        Consumer<Watcher> disPlay = (watcher)->{
            String s= null;
            try {
                s = store.read("/config",watcher);
                System.out.println(s);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
     //  final Reference<Watcher>  watcherRef = new SoftReference<Watcher>();
        final Object[] ref = new Object[1];
        final Watcher watcher = (event)->{
            switch (event.getType()){
                case NodeDataChanged:
                    System.out.println("123");
                    disPlay.accept((Watcher) ref[0]);
                    break;
            }

        };

        ref[0]=watcher;
       // watcherRef.
        disPlay.accept(watcher);

       Thread.sleep(Long.MAX_VALUE);
    }


}
