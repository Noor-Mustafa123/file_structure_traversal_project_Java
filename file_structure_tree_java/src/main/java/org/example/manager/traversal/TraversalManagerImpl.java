package org.example.manager.traversal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.example.dto.TreeNode;
import org.example.manager.TraversalManager;
import org.example.tasks.TreeNodePreparationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NoArgsConstructor;

import tools.jackson.databind.ObjectMapper;

@NoArgsConstructor
public class TraversalManagerImpl implements TraversalManager {

    static final String ROOT_PATH = "/";

    static final String DIR = "Directory";

    static final String OUTPUT_FILE_PATH = "/home/sces82/GIT/file_structure_traversal_project_Java/file_structure_tree_java/traversal_output.txt";

    private static final Logger log = LoggerFactory.getLogger( TraversalManagerImpl.class );

    @Override
    public void traverse_path( String path ) {
        Path unixPathObj = parse_path( path );
        ExecutorService executorService = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
        try ( Stream< Path > stream = Files.list( unixPathObj ) ) {
            // only works for the files in the path now the whole path
            List< TreeNode > nodeList = stream.map( item -> {
                TreeNodePreparationTask treeNodePreparationTask = new TreeNodePreparationTask( item );
                return executorService.submit( treeNodePreparationTask );
            } ).map( treeNodeFuture -> {
                try {
                    return treeNodeFuture.get();
                } catch ( Exception e ) {
                    throw new RuntimeException( e );
                }
            } ).toList();
            executorService.shutdownNow();
            var objectMapper = new ObjectMapper();
            OutputStream outputPath = Files.newOutputStream( Path.of( OUTPUT_FILE_PATH ) );
            outputPath.write( objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes( nodeList ) );
            outputPath.close();
        } catch ( IOException e ) {
            throw new RuntimeException( "error during directory traversal", e );
        }
    }

    private Path parse_path( String path ) {

        try {
            Path pathObj = Path.of( path );
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

    public TreeNode prepareNodTreeObject( Path pathObj ) {
        try {
            File file = pathObj.toFile();
            TreeNode node = new TreeNode();
            node.setId( UUID.randomUUID() );
            node.setName( file.getName() );
            node.setPath( file.getPath() );
            node.setType( file.isDirectory() ? DIR : Files.probeContentType( pathObj ) );

            List< Path > subDirList = null;
            try ( Stream< Path > stream = Files.list( pathObj ) ) {
                subDirList = stream.toList();
            } catch ( Exception e ) {
                // warn in case of permission issues for directories
                log.warn( e.getMessage() );
            }
            if ( subDirList == null || subDirList.isEmpty() ) {
                node.setChildrenNodes( null );
            } else {
                List< TreeNode > listOfNodes = new ArrayList< TreeNode >();
                for ( Path dirItem : subDirList ) {
                    listOfNodes.add( prepareNodTreeObject( dirItem ) );
                }
                node.setChildrenNodes( listOfNodes );
            }
            return node;

        } catch ( Exception e ) {
            throw new RuntimeException( "exception during node tree preparation", e );
        }

    }

}
