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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        property = "indexer.class.name=br.com.objective.training.model.Guestbook",
        service = ModelSummaryContributor.class
)
public class GuestbookModelSummaryContributor implements ModelSummaryContributor {

    @Override
    public Summary getSummary(Document document, Locale locale, String snippet) {

        String prefix = Field.SNIPPET + StringPool.UNDERLINE;

        String title = document.get(prefix + Field.NAME, Field.NAME);
        String content = document.get(prefix + Field.DESCRIPTION, Field.DESCRIPTION);

        Summary summary = new Summary(title, content);

        summary.setMaxContentLength(200);

        return summary;
    }

}