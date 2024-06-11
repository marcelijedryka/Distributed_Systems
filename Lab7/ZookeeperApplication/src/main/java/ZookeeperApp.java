import org.apache.zookeeper.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class ZookeeperApp implements Watcher {

    private ZooKeeper zookeeper;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZookeeperApp(){};

    @Override
    public void process(WatchedEvent event) {
        try {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                System.out.println("Connected to ZooKeeper");
                connectedSignal.countDown();
            }
            else if (event.getType() == Event.EventType.NodeCreated && event.getPath().equals("/a")) {
                runPaint();
                System.out.println("Node created: " + event.getPath());
            } else if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals("/a")) {
                stopPaint();
                System.out.println("Node deleted: " + event.getPath());
                stopPaint();
            }
            if (event.getType() == Event.EventType.NodeChildrenChanged && event.getPath().equals("/a")) {
                List<String> children = zookeeper.getChildren(event.getPath(), this);
                System.out.println("Current number of children: " + children.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException, InterruptedException, KeeperException {
        zookeeper = new ZooKeeper("localhost:2181", 3000, this);
        connectedSignal.await();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            if (command.equals("add")) {
                System.out.print("Enter nodes's name: ");
                String nodeName = scanner.nextLine();
                addNode(nodeName);
            } else if (command.equals("delete")) {
                System.out.print("Enter nodes's name to delete: ");
                String nodeName = scanner.nextLine();
                deleteNode(nodeName);
            } else if (command.equals("show")) {
                showTree("/");
            } else if (command.equals("exit")) {
                zookeeper.close();
                System.out.println("Exiting application...");
                break;
            } else {
                System.out.println("Unknown command");
            }
        }

    }

    private void addNode(String path) throws KeeperException, InterruptedException {
        if (zookeeper.exists(path, false) == null) {
            zookeeper.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("New node has been created");
            if(path.equals("/a")){
                runPaint();
            }
            String[] fam = path.split("/");
            if(fam[1].equals("a")){
                List<String> children = zookeeper.getChildren("/a", this);
                System.out.println("Current number of children: " + children.size());
            }
            zookeeper.exists(path, this);
        } else {
            System.out.println("Node already exists");
        }
    }

    private void deleteNode(String path) throws KeeperException, InterruptedException {
        if (zookeeper.exists(path, false) != null) {
            deleteRecursively(path);
            System.out.println("Node and its children has been deleted");
        } else {
            System.out.println("Node does not exist");
        }
    }

    private void deleteRecursively(String path) throws KeeperException, InterruptedException {
        if(path.equals("/a")){
            stopPaint();
        }
        List<String> children = zookeeper.getChildren(path, false);
        for (String child : children) {
            deleteRecursively(path + "/" + child);
        }
        zookeeper.delete(path, -1);
    }


    private void showTree(String path) throws KeeperException, InterruptedException {
        List<String> children = zookeeper.getChildren(path, false);
        for (String child : children) {
            String childPath = path.equals("/") ? "/" + child : path + "/" + child;
            System.out.println(childPath);
            showTree(childPath);
        }
    }

    private static void runPaint(){
        try {
            Runtime.getRuntime().exec("mspaint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void stopPaint() {
        try {
            Runtime.getRuntime().exec("taskkill /f /im mspaint.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

