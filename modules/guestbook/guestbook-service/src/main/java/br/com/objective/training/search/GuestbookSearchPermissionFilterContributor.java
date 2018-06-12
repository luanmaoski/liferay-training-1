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

package com.liferay.calendar.internal.search;

import br.com.objective.training.model.Entry;
import br.com.objective.training.model.Guestbook;
import com.liferay.portal.search.permission.SearchPermissionFilterContributor;
import org.osgi.service.component.annotations.Component;

import java.util.Optional;

/**
 * @author lucas
 * @author luan
 */
@Component(immediate = true, service = SearchPermissionFilterContributor.class)
public class GuestbookSearchPermissionFilterContributor implements SearchPermissionFilterContributor {

    @Override
    public Optional<String> getParentEntryClassNameOptional(String entryClassName) {

        if (entryClassName.equals(Entry.class.getName())) {
            return Optional.of(Guestbook.class.getName());
        }

        return Optional.empty();
    }

}