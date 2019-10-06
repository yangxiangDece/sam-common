package com.yang.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 查询解析器
 */
public class QueryParserIndex {

    public static void main(String[] args) throws Exception {
        // 1、创建一个Directory对象，也就是索引存放的位置
        Directory directory = FSDirectory.open(new File("D:/work/lucene/indexDir").toPath());
        // 2、创建一个indexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        // 3、创建一个indexSearcher对象，需要指定indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建查询解析器
        // QueryParser（单一字段的查询解析器） 两个参数：默认要查询的字段名称，分词器
        QueryParser queryParser = new QueryParser("fileContent", new IKAnalyzer());
        // MultiFieldQueryParser（多字段的查询解析器） 两个参数：默认要查询的字段名称数组，分词器
//        QueryParser multiFieldQueryParser = new MultiFieldQueryParser(new String[]{"fileName"}, new IKAnalyzer());
        Query query = queryParser.parse("corresponding");
        // 5、执行查询
        TopDocs topDocs = indexSearcher.search(query, 5);

        // 高亮显示
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer scorer = new QueryScorer(query);
        // 准备高亮工具
        Highlighter highlighter = new Highlighter(formatter, scorer);

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

                // 高亮显示
                String highFileContent = highlighter.getBestFragment(new IKAnalyzer(), "fileContent", fileContent);
                System.out.println(highFileContent);
            }
        }
        // 7、关闭indexReader对象
        indexReader.close();
    }
}
