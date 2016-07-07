package com.duanzq.easyBuy;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by duanzq on 16/7/6.
 * Lucene分页查询
 */
public class LucenePageShowTest {
    @Test
    public void testPageShow() throws IOException, ParseException {
        //建立索引库的目录
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));
        //创建IndexSearcher
        Analyzer analyzer = new IKAnalyzer();
        QueryParser queryParser = new QueryParser("title", analyzer);
        Query query = queryParser.parse("apple");
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        //查询前10条
        TopDocs topDocs = indexSearcher.search(query, 10);
        //查询结果
        System.out.println("共命中:" + topDocs.totalHits + "条");
        //分页展示
        Integer currentPage = 1;
        Integer pageSize = 10;
        Integer start = (currentPage - 1) * pageSize;
        Integer end = start + pageSize;
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = start; i < end; i++){
            System.out.println("--------------第" + i + "条-------------");
            int docId = scoreDocs[i].doc;
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("title"));
            System.out.println(document.get("image"));
        }
    }
}
