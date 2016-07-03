package com.duanzq.easyBuy;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * Created by duanzq on 16/7/3.
 * 分词搜索
 */
public class LuceneAnalyzerQueryTest {
    @Test
    public void testAnalyzerQuery() throws Exception{
        //创建索引对象
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //定义分词器
        Analyzer analyzer = new IKAnalyzer();
        //定义分析器, 指定分析的域和分词器
        QueryParser queryParser = new QueryParser("title", analyzer);
        //构造查询对象,分词查询
        Query query = queryParser.parse("传智播客");

        //执行indexSearcher
        TopDocs topDocs = indexSearcher.search(query, 10);
        //获取打分数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc: scoreDocs) {
            System.out.println("获得的分数:" + scoreDoc.score);
            //获取文档id
            int doc = scoreDoc.doc;
            //根据文档id获取文档对象
            Document document = indexSearcher.doc(doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("image:" + document.get("image"));
            System.out.println("price:" + document.get("price"));
            System.out.println("status:" + document.get("status"));
            System.out.println("title:" + document.get("title"));
        }
    }
}
