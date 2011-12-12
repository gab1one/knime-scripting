package de.mpicbg.tds.knime.knutils;

import org.knime.base.node.preproc.filter.row.rowfilter.AttrValueRowFilter;
import org.knime.base.node.preproc.filter.row.rowfilter.EndOfTableException;
import org.knime.base.node.preproc.filter.row.rowfilter.IncludeFromNowOn;
import org.knime.core.data.*;
import org.knime.core.node.InvalidSettingsException;

/**
 * Created by IntelliJ IDEA.
 * User: Antje Niederlein
 * Date: 12/5/11
 * Time: 3:09 PM
 */
public class RangeRowFilter extends AttrValueRowFilter {

    private DataCell m_lowerBound;

    private DataCell m_upperBound;

    private DataValueComparator m_comparator;

    public RangeRowFilter(String colName, final DataCell lowerBound, final DataCell upperBound) {
        super(colName, true);

        if (lowerBound == null && upperBound == null) {
            throw new NullPointerException("At least one bound of the range"
                    + " must be specified.");
        }

        m_lowerBound = lowerBound;
        m_upperBound = upperBound;
        m_comparator = null;
    }

    @Override
    public boolean matches(DataRow dataRow, int i) throws EndOfTableException, IncludeFromNowOn {

        DataCell theCell = dataRow.getCell(getColIdx());
        boolean match;

        if (theCell.isMissing()) {
            match = false;
        } else {
            if (m_lowerBound != null) {
                match = (m_comparator.compare(m_lowerBound, theCell) <= 0);
            } else {
                // if no lowerBound is specified - its always above the minimum
                match = true;
            }
            if (m_upperBound != null) {
                match &= (m_comparator.compare(theCell, m_upperBound) <= 0);
            }
        }
        return ((getInclude() && match) || (!getInclude() && !match));
    }

    @Override
    public DataTableSpec configure(DataTableSpec inSpec) throws InvalidSettingsException {
        super.configure(inSpec);

        // check if data type of bounds and the column are equal
        DataType colType = inSpec.getColumnSpec(getColIdx()).getType();
        if (m_lowerBound != null) {
            if (!colType.isASuperTypeOf(m_lowerBound.getType())) {
                throw new InvalidSettingsException("Column value filter: "
                        + "Specified lower bound of range doesn't fit "
                        + "column type. (Col#:" + getColIdx() + ",ColType:"
                        + colType + ",RangeType:" + m_lowerBound.getType());
            }
        }
        if (m_upperBound != null) {
            if (!colType.isASuperTypeOf(m_upperBound.getType())) {
                throw new InvalidSettingsException("Column value filter: "
                        + "Specified upper bound of range doesn't fit "
                        + "column type. (Col#:" + getColIdx() + ",ColType:"
                        + colType + ",RangeType:" + m_upperBound.getType());
            }
        }

        m_comparator = colType.getComparator();

        return inSpec;
    }
}
