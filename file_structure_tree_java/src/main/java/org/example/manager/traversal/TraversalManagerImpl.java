package org.example.manager.traversal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.example.manager.TraversalManager;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class TraversalManagerImpl implements TraversalManager {

    static final String ROOT_PATH = "/";

    @Override
    public void traverse_path( String path ) {

        try {
            Path unixPathObj = parse_path( path );
            // get directory files of the root path first
            Stream< Path > stream = Files.list(unixPathObj);
            List<Path> listOfFiles = stream.toList();

        } catch ( IOException e ) {
            throw new RuntimeException( "error during directory traversal" );
        }

    }
    private Path parse_path( String path ) {

        try {
            Path pathObj= Path.of( path );
//       if path does not start with root
            if ( !pathObj.startsWith( ROOT_PATH ) ) {
                pathObj = pathObj.toAbsolutePath();
            }
//      resolving absolute path
           return pathObj.toRealPath();
        } catch ( IOException e ) {
            // does not need to be caught
            throw new RuntimeException( "path passed could not be resolved" );
        }

    }


}
