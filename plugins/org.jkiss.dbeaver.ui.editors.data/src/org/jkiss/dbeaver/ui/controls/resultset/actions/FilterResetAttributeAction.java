/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2025 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.controls.resultset.actions;

import org.eclipse.osgi.util.NLS;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeConstraint;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.ui.UIIcon;
import org.jkiss.dbeaver.ui.controls.resultset.ResultSetViewer;
import org.jkiss.dbeaver.ui.controls.resultset.internal.ResultSetMessages;

public class FilterResetAttributeAction extends AbstractResultSetViewerAction {
    private final DBDAttributeBinding attribute;

    public FilterResetAttributeAction(ResultSetViewer resultSetViewer, DBDAttributeBinding attribute) {
        super(resultSetViewer, NLS.bind(ResultSetMessages.controls_resultset_viewer_action_filter_remove, attribute.getName()), UIIcon.REVERT);
        this.attribute = attribute;
    }

    @Override
    public void run() {
        DBDDataFilter dataFilter = new DBDDataFilter(getResultSetViewer().getModel().getDataFilter());
        DBDAttributeConstraint constraint = dataFilter.getConstraint(attribute);
        if (constraint != null) {
            constraint.setCriteria(null);
            getResultSetViewer().setDataFilter(dataFilter, true);
        }
    }
}
