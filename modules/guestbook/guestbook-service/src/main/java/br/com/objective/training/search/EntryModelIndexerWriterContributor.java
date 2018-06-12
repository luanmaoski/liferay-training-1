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
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author lucas
 * @author luan
 */
@Component(
        immediate = true,
        property = "indexer.class.name=br.com.objective.training.model.Entry",
        service = ModelIndexerWriterContributor.class
)
public class EntryModelIndexerWriterContributor implements ModelIndexerWriterContributor<Entry> {

    @Override
    public void customize(BatchIndexingActionable batchIndexingActionable, ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
            batchIndexingActionable.addDocuments(modelIndexerWriterDocumentHelper.getDocument(entry));
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.
                getBatchIndexingActionable(entryLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Entry entry) {
        return entry.getCompanyId();
    }


    @Reference
    protected EntryLocalService entryLocalService;

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
            dynamicQueryBatchIndexingActionableFactory;

}