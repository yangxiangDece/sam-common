package com.yang.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 创建索引
 */
public class CreateIndex {

    public static void main(String[] args) throws Exception {
        // 1、指定索引库存放位置Directory对象
        Directory directory = FSDirectory.open(new File("D:/work/lucene/indexDir").toPath());
        // 2、指定分析器，对文档内容进行分析 官方推荐分词器
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 3、创建一个indexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 原始文档
        File[] files = new File("D:/work/lucene/sourceDir").listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                // 4、创建document对象
                Document document = new Document();
                // 5、创建field对象，将field添加到document对象
                // 文件名称
                String fileName = file.getName();
                // 参数：字段的名称、字段的值、是否存储，这里选Store.YES代表存储到文档列表，Store.NO代表不存储
                // TextField 要分词、要索引
                Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
                // 文件大小
                long fileSize = FileUtils.sizeOf(file);
                // LongPoint 要分词、要索引 但是不存储索引
                Field fileSizeField = new LongPoint("fileSize", fileSize);
                // 文件路径
                String filePath = file.getPath();
                // StoredField 不分词、不索引
                Field filePathField = new StoredField("filePath", filePath);
                // 文件内容
                String fileContent = FileUtils.readFileToString(file, "UTF-8");
                // TextField 要分词、要索引
                Field fileContentField = new TextField("fileContent", fileContent, Field.Store.YES);
                document.add(fileNameField);
                document.add(fileSizeField);
                document.add(filePathField);
                document.add(fileContentField);
                // 使用indexWriter对象将document对象写入索引库
                indexWriter.addDocument(document);
            }
        }
        // 关闭indexWriter对象
        indexWriter.close();
    }
}
