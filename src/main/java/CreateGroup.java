import org.apache.zookeeper.*;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateGroup extends ConnectWatcher {

    public void create(String groupname) throws KeeperException, InterruptedException {
        String path = groupname;
        String createPath = zk.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(createPath);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        CreateGroup group = new CreateGroup();
        group.connect("localhost");
        group.create("/wzp/ab");
        group.close();

       // BufferedInputStream


    }

}
