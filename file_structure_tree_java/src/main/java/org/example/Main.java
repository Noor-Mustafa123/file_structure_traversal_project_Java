package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.example.manager.TraversalManager;
import org.example.manager.traversal.TraversalManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static final String DEFAULT_PATH = "/home/noor/Downloads";

    private static final Logger log = LoggerFactory.getLogger( Main.class );

    public static void main( String[] args ) {
       LocalDateTime startTime = LocalDateTime.now();
        String path = null;
        if ( args.length == 0 ){
            // defaults to /home
            path = DEFAULT_PATH;
        } else {
            path = args[0];
        }
        TraversalManagerImpl traversalManager = new TraversalManagerImpl();

        traversalManager.traverse_path( path );
        LocalDateTime endTime = LocalDateTime.now();
        log.info(
                String.format(
                        "|| Directory Traversal Complete ||%nStart Time: %s%nEnd Time: %s %n Total time: %s seconds",
                        startTime.format(DateTimeFormatter.ISO_DATE_TIME),
                        endTime.format(DateTimeFormatter.ISO_DATE_TIME), Duration.between(startTime,endTime)

                )
        );
    }

}