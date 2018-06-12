/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package br.com.objective.training.search;

import br.com.objective.training.model.Guestbook;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Field;
import org.osgi.service.component.annotations.Component;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        property = "model.class.name=br.com.objective.training.model.Guestbook",
        service = BaseSearcher.class
)
public class GuestbookSearcher extends BaseSearcher {

    public static final String CLASS_NAME = Guestbook.class.getName();

    public GuestbookSearcher() {
        setDefaultSelectedFieldNames(Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.UID);
        setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.NAME, "resourceName");
        setFilterSearch(true);
        setPermissionAware(true);
        setSelectAllLocales(true);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

}