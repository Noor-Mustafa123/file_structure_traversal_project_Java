package org.example.tasks;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.example.dto.TreeNode;
import org.example.manager.TraversalManager;
import org.example.manager.traversal.TraversalManagerImpl;

public class TreeNodePreparationTask implements Callable< TreeNode > {

    final Path path;

    public TreeNodePreparationTask( Path path ) {
        this.path = path;
    }
    @Override
    public TreeNode call() throws Exception {
        TraversalManager traversalManager = new TraversalManagerImpl();
        return traversalManager.prepareNodTreeObject( this.path );
    }

}
