package de.mpicbg.tds.knime.scripting.r.genericr;

import de.mpicbg.tds.knime.scripting.r.R4KnimeBundleActivator;
import de.mpicbg.tds.knime.scripting.r.RSnippetNodeDialog;
import de.mpicbg.tds.knime.scripting.r.prefs.RPreferenceInitializer;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "RSnippet" Node. Improved R Integration for Knime
 *
 * @author Holger Brandl (MPI-CBG)
 */
public class GenericOpenInRNodeFactory extends NodeFactory<GenericOpenInRNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericOpenInRNodeModel createNodeModel() {
        return new GenericOpenInRNodeModel();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 0;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<GenericOpenInRNodeModel> createNodeView(final int viewIndex,
                                                            final GenericOpenInRNodeModel nodeModel) {
        throw null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        String templateResources = R4KnimeBundleActivator.getDefault().getPreferenceStore().getString(RPreferenceInitializer.R_OPENINR_TEMPLATES);
        return new RSnippetNodeDialog("", templateResources, false, true);
    }

}