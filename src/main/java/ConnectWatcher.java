import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConnectWatcher implements Watcher, Closeable {
    private static final int SESSION_TIME_OUT = 5000;
    protected ZooKeeper zk;
    private CountDownLatch connectedSigal = new CountDownLatch(1);

    public void connect(String host) throws InterruptedException, IOException {
        zk = new ZooKeeper(host,SESSION_TIME_OUT,this);
        connectedSigal.await();
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getState());
          if(watchedEvent.getState()== Event.KeeperState.SyncConnected){
             connectedSigal.countDown();
          }
    }

    public void close() throws IOException {
        try {
            zk.close();
        } catch (InterruptedException e) {
           throw new IOException(e);
        }
    }
}
