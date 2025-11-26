package org.example;

import org.example.manager.TraversalManager;
import org.example.manager.traversal.TraversalManagerImpl;

public class Main {

    static final String DEFAULT_PATH = "/home";
    public static void main( String[] args ) {

        String path = args[0];

        if( path == null ) {
            // defaults to /home
            path = DEFAULT_PATH;
        }
        TraversalManagerImpl traversalManager = new TraversalManagerImpl();

        traversalManager.traverse_path( path );

        for ( int i = 1; i <= 5; i++ ) {
            System.out.println( "i = " + i );
        }
    }

}