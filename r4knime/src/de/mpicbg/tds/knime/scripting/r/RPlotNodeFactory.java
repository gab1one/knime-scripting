package de.mpicbg.tds.knime.scripting.r;

import de.mpicbg.tds.knime.scripting.r.plots.AbstractRPlotNodeFactory;


/**
 * @author Holger Brandl (MPI-CBG)
 */
public class RPlotNodeFactory extends AbstractRPlotNodeFactory<RPlotNodeModel> {

    @Override
    public RPlotNodeModel createNodeModel() {
        return new RPlotNodeModel();
    }
}