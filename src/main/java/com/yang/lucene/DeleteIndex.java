package com.yang.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 删除索引
 */
public class DeleteIndex {

    public static void main(String[] args) throws Exception {
        deleteAll();
//        deleteByQuery();
    }

    private static void deleteAll() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteAll();
        indexWriter.close();
    }

    private static void deleteByQuery() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        Term fileNameTerm = new Term("fileName", "java.txt");
        indexWriter.deleteDocuments(fileNameTerm);
        indexWriter.close();
    }

    private static IndexWriter getIndexWriter() throws Exception {
        Directory directory = FSDirectory.open(new File("D:/work/lucene/indexDir").toPath());
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        return new IndexWriter(directory, config);
    }
}
