package org.example.manager;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

import org.example.dto.TreeNode;

public interface TraversalManager {
    
    void traverse_path( String path );

    TreeNode prepareNodTreeObject( Path pathObj, ExecutorService executorService );

    }
