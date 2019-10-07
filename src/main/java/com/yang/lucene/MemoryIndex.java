package com.yang.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 内存索引
 */
public class MemoryIndex {

    public static void main(String[] args) throws Exception {

        Directory directory = new MMapDirectory(new File("D:/work/lucene/indexMMapDir").toPath());
        IndexReader indexReader = null;
        String fieldName = "text";
        String text = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。";
        try {
            // 创建索引
            Analyzer analyzer = new IKAnalyzer();
            IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));
            Document document = new Document();
            document.add(new TextField("ID", "10000", Field.Store.YES));
            document.add(new TextField(fieldName, text, Field.Store.YES));
            indexWriter.addDocument(document);
            indexWriter.close();

            // 查询索引
            indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            String keyword = "中文分词工具包";
            QueryParser queryParser = new QueryParser(fieldName, analyzer);
            Query query = queryParser.parse(keyword);

            TopDocs topDocs = indexSearcher.search(query, 5);
            System.out.println("搜索结果共：" + topDocs.totalHits.value + "条数据");
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            if (scoreDocs != null && scoreDocs.length > 0) {
                for (ScoreDoc scoreDoc : scoreDocs) {
                    int docId = scoreDoc.doc;
                    Document doc = indexSearcher.doc(docId);
                    System.out.println("内容：" + doc.toString());
                }
            }
        } finally {
            if (indexReader != null) {
                indexReader.close();
            }
            directory.close();
        }
    }
}
