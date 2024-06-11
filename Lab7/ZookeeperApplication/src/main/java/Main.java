import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperApp zk = new ZookeeperApp();
        zk.run();
    }
}
