package in.poc.nio;

import java.nio.file.*;

/**
 * Created with IntelliJ IDEA.
 * User: bala
 * Date: 18/07/2013
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class Watcher {

    public static void main(String arg[]){
        Path this_dir = Paths.get(".");
        System.out.println("Now watching the current directory ..." + this_dir.toAbsolutePath());

        try {

            WatchService watchService = this_dir.getFileSystem().newWatchService();
            this_dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watchKey = watchService.take();

            for (WatchEvent event: watchKey.pollEvents()){
                System.out.println("Someone just created the file '" + event.context().toString() + "'.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }
}
