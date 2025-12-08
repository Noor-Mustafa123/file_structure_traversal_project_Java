package org.example.manager;

import java.nio.file.Path;

import org.example.dto.TreeNode;

public interface TraversalManager {
    
    public void traverse_path( String path );

    public TreeNode prepareNodTreeObject( Path pathObj );

    }
