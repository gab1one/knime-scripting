package de.mpicbg.knime.scripting.r;

import de.mpicbg.knime.scripting.r.node.snippet.RSnippetNodeModel;

/**
 * <code>NodeFactory</code> for the "RSnippet" Node. Improved R Integration for Knime
 *
 * @author Holger Brandl (MPI-CBG)
 */
public class RSnippetNodeFactory21
        extends AbstractRSnippetNodeFactory {

    @Override
    public RSnippetNodeModel createNodeModel() {
        return new RSnippetNodeModel(2, 1);
    }
}
