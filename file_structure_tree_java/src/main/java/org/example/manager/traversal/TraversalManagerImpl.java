package org.example.manager.traversal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.extern.flogger.Flogger;
import lombok.extern.jackson.Jacksonized;
import org.example.dto.NodeDTO;
import org.example.manager.TraversalManager;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JacksonModule;
import tools.jackson.databind.ObjectMapper;


@NoArgsConstructor
public class TraversalManagerImpl implements TraversalManager {

    static final String ROOT_PATH = "/";

    static final String DIR = "Directory";

    static final String OUTPUT_FILE_PATH = "/home/noor/IdeaProjects/file_structure_traversal_project_Java/output.txt";

    @Override
    public void traverse_path(String path) {

        try {
            Path unixPathObj = parse_path(path);
            // get directory files of the root path first
            Stream<Path> stream = Files.list(unixPathObj);
            // only works for the files in the path now the whole path
            List<NodeDTO> nodeList = stream.map(this::prepareNodTreeObject).toList();
            //! should be outputed to a file
            //! use threads to optimize
            var objectMapper = new ObjectMapper();
            Files.createFile(Path.of(OUTPUT_FILE_PATH),)
            System.out.println( objectMapper.writeValueAsString(nodeList) );
        } catch (IOException e) {
            throw new RuntimeException("error during directory traversal",e);
        }
    }

    private Path parse_path(String path) {

        try {
            Path pathObj = Path.of(path);
//       if path does not start with root
            if (!pathObj.startsWith(ROOT_PATH)) {
                pathObj = pathObj.toAbsolutePath();
            }
//      resolving absolute path
            return pathObj.toRealPath();
        } catch (IOException e) {
            // does not need to be caught
            throw new RuntimeException("path passed could not be resolved");
        }

    }

    private NodeDTO prepareNodTreeObject(Path pathObj) {
        try {
            File file = pathObj.toFile();
            NodeDTO node = new NodeDTO();
            node.setId(UUID.randomUUID());
            node.setName(file.getName());
            node.setPath(file.getPath());
            node.setType(file.isDirectory() ? DIR : Files.probeContentType(pathObj));

                List<Path> subDirList = null;
                try {
                    subDirList = Files.list(pathObj).toList();
                } catch (Exception e) {
                    // ignore in case of permission issues
                }
                if ( subDirList == null || subDirList.isEmpty() ) {
                    node.setChildrenNodes(null);
                } else {
                    List<NodeDTO> listOfNodes = new ArrayList<NodeDTO>();
                    for (Path dirItem : subDirList) {
                        listOfNodes.add(prepareNodTreeObject(dirItem));
                    }
                }
            return node;

        } catch (Exception e) {
            throw new RuntimeException("exception during node tree preparation",e);
        }

    }


}
