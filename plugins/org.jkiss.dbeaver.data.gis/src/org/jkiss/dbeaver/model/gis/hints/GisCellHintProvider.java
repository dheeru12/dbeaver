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
package org.jkiss.dbeaver.model.gis.hints;

import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDResultSetModel;
import org.jkiss.dbeaver.model.data.DBDValueRow;
import org.jkiss.dbeaver.model.data.hints.DBDCellHintProvider;
import org.jkiss.dbeaver.model.data.hints.DBDValueHint;
import org.jkiss.dbeaver.model.data.hints.ValueHintText;
import org.jkiss.dbeaver.model.gis.DBGeometry;

import java.util.EnumSet;

/**
 * GIS hint provider
 */
public class GisCellHintProvider implements DBDCellHintProvider {

    @Nullable
    @Override
    public DBDValueHint[] getCellHints(
        @NotNull DBDResultSetModel model,
        @NotNull DBDAttributeBinding attribute,
        @NotNull DBDValueRow row,
        @Nullable Object value,
        @NotNull EnumSet<DBDValueHint.HintType> types,
        int options
    ) {
        if (value instanceof DBGeometry geometry) {
            int srid = geometry.getSRID();
            if (srid > 0) {
                return new DBDValueHint[]{
                    new ValueHintText(
                        "SRS:" + srid,
                        "SRID", null)
                };
            }
        }
        return null;
    }

}
