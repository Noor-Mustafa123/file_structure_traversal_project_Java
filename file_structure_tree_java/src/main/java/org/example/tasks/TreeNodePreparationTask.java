package org.example.tasks;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import org.example.dto.TreeNode;
import org.example.manager.TraversalManager;
import org.example.manager.traversal.TraversalManagerImpl;

public class TreeNodePreparationTask implements Callable< TreeNode > {

    final Path path;

    final ExecutorService executorService;

    public TreeNodePreparationTask( Path path, ExecutorService executorService ) {
        this.path = path;
        this.executorService = executorService;
    }
    @Override
    public TreeNode call() throws Exception {
        TraversalManager traversalManager = new TraversalManagerImpl();
        return traversalManager.prepareNodTreeObject( path, executorService );
    }

}
