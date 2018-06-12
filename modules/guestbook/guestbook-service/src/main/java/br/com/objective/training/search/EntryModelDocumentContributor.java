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

import br.com.objective.training.model.Entry;
import br.com.objective.training.model.Guestbook;
import br.com.objective.training.service.GuestbookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        property = "indexer.class.name=br.com.objective.training.model.Entry",
        service = ModelDocumentContributor.class
)
public class EntryModelDocumentContributor implements ModelDocumentContributor<Entry> {

    @Override
    public void contribute(Document document, Entry entry) {
        try {
            document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
            document.addText("email", entry.getEmail());

            Locale defaultLocale = PortalUtil.getSiteDefaultLocale(entry.getGroupId());
            String localizedTitle = LocalizationUtil.getLocalizedName(Field.TITLE, defaultLocale.toString());
            String localizedMessage = LocalizationUtil.getLocalizedName(Field.CONTENT, defaultLocale.toString());

            document.addText(localizedTitle, entry.getName());
            document.addText(localizedMessage, entry.getMessage());

            long guestbookId = entry.getGuestbookId();
            Guestbook guestbook = _guestbookLocalService.getGuestbook(guestbookId);
            String guestbookName = guestbook.getName();
            String localizedGbName = LocalizationUtil.getLocalizedName("guestbookName", defaultLocale.toString());

            document.addText(localizedGbName, guestbookName);
        } catch (PortalException e) {
            e.printStackTrace();
        }

    }

    protected String[] getLanguageIds(String defaultLanguageId, String content) {

        String[] languageIds = LocalizationUtil.getAvailableLanguageIds(content);

        if (languageIds.length == 0) {
            languageIds = new String[]{defaultLanguageId};
        }

        return languageIds;
    }

    @Reference
    protected ClassNameLocalService classNameLocalService;

    @Reference
    private GuestbookLocalService _guestbookLocalService;


}