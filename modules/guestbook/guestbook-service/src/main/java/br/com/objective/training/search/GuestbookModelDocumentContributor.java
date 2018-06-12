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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        property = "indexer.class.name=br.com.objective.training.model.Guestbook",
        service = ModelDocumentContributor.class
)
public class GuestbookModelDocumentContributor implements ModelDocumentContributor<Guestbook> {

    @Override
    public void contribute(Document document, Guestbook guestbook) {

        document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

        Locale defaultLocale = null;
        try {
            defaultLocale = PortalUtil.getSiteDefaultLocale(guestbook.getGroupId());
            String localizedField = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());

            document.addText(localizedField, guestbook.getName());
        } catch (PortalException e) {
            e.printStackTrace();
        }
    }

}