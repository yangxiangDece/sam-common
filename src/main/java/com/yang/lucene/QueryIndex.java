package com.yang.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

/**
 * 查询索引
 */
public class QueryIndex {

    public static void main(String[] args) throws Exception {
        // 1、创建一个Directory对象，也就是索引存放的位置
        Directory directory = FSDirectory.open(new File("D:/work/lucene/indexDir").toPath());
        // 2、创建一个indexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 3、创建一个indexSearcher对象，需要指定indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        // 4、创建一个TermQuery对象，指定查询的域和查询的关键词
        // 4.1 构建文档域 查询名称为lucene的数据
        Term fileNameTerm = new Term("fileName", "java");
        // TermQuery 通过项查询 不会使用分析器，所以建议匹配不分词的Field域查询
//        Query query = new TermQuery(fileNameTerm);
        // MatchAllDocsQuery 查询所有
//        Query query = new MatchAllDocsQuery();
        // LongPoint.newRangeQuery() 按区间范围查询
        Query query = LongPoint.newRangeQuery("fileSize",200,1000);
//        Query query = new BooleanQuery();
        // 5、执行查询
        TopDocs topDocs = indexSearcher.search(query, 5);
        // 6、返回查询结果 这里取出来的是文档id的集合 倒排索引
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        if (scoreDocs != null && scoreDocs.length > 0) {
            for (ScoreDoc scoreDoc : scoreDocs) {
                int docId = scoreDoc.doc;
                Document doc = indexSearcher.doc(docId);
                String fileName = doc.get("fileName");
                String fileSize = doc.get("fileSize");
                String filePath = doc.get("filePath");
                String fileContent = doc.get("fileContent");
                System.out.println("文档id：" + docId + "：" +
                        "，fileName：" + fileName +
                        "，fileSize：" + fileSize +
                        "，filePath：" + filePath +
                        "，fileContent：" + fileContent);
            }
        }
        // 7、关闭indexReader对象
        indexReader.close();
    }
}
