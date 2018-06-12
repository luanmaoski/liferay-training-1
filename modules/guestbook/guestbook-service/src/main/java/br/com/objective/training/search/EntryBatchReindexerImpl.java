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
import br.com.objective.training.service.EntryLocalService;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.indexer.IndexerDocumentBuilder;
import com.liferay.portal.search.indexer.IndexerWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        service = EntryBatchReindexer.class
)
public class EntryBatchReindexerImpl implements EntryBatchReindexer {

    @Override
    public void reindex(long guestbookId, long companyId) {
        BatchIndexingActionable batchIndexingActionable = indexerWriter.getBatchIndexingActionable();

        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {

            Property guestbookIdPropery = PropertyFactoryUtil.forName("guestbookId");
            dynamicQuery.add(guestbookIdPropery.eq(guestbookId));
            Property statusProperty = PropertyFactoryUtil.forName("status");

        });

        batchIndexingActionable.setCompanyId(companyId);
        batchIndexingActionable.setPerformActionMethod((Entry Entry) -> {

            Document document = indexerDocumentBuilder.getDocument(Entry);
            batchIndexingActionable.addDocuments(document);

        });

        batchIndexingActionable.performActions();
    }

    @Reference
    protected EntryLocalService entryLocalService;

    @Reference(target = "(indexer.class.name=br.com.objective.training.model.Entry)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=br.com.objective.training.model.Entry)")
    protected IndexerWriter<Entry> indexerWriter;

}