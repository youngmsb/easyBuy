package com.duanzq.easyBuy;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * Created by duanzq on 16/7/3.
 * Lucene创建索引
 */
public class LuceneCreateIndexTest {
    @Test
    public void testCreateIndex() throws Exception{
        //创建文档, 并添加商品数据
        Document document = new Document();
        //添加一个long类型的id, 字段名,字段值,是否存储
        document.add(new LongField("id", 12L, Field.Store.YES));
        //StringField:做索引,不分词;TextField:做索引,分词
        document.add(new TextField("title", "苹果apple5", Field.Store.YES));
        document.add(new LongField("price", 4900L, Field.Store.YES));
        //不需要作为结果展示到页面的内容就不需要存储
        document.add(new StringField("image", "http://image", Field.Store.NO));
        document.add(new IntField("status", 1, Field.Store.YES));

        //创建索引位置
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));

        //定义IndexWriter,并写入索引数据
        //标准分析器
        //Analyzer analyzer = new StandardAnalyzer();
        //IK分词器
        Analyzer analyzer = new IKAnalyzer();
        //索引写入配置类
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);
        //设置写入模式
        //IndexWriterConfig.OpenMode.APPEND在原有索引数据后添加
        //IndexWriterConfig.OpenMode.CREATE删除原有索引再新建索引(没有索引就会报错)
        //IndexWriterConfig.OpenMode.CREATE_OR_APPEND没有索引就创建,有就添加
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        //索引写入对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //写入索引
        indexWriter.addDocument(document);
        indexWriter.close();
    }
}
