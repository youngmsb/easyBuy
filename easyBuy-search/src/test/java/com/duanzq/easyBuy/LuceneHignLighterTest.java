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
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by duanzq on 16/7/6.
 * Lucene高亮显示
 */
public class LuceneHignLighterTest {
    @Test
    public void testLuceneHignLighter() throws IOException, ParseException, InvalidTokenOffsetsException {
        //建立索引库对应的目录
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));
        //创建分析器
        Analyzer analyzer = new IKAnalyzer();
        QueryParser queryParser = new QueryParser("title", analyzer);
        Query query = queryParser.parse("apple");
        //创建IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));
        TopDocs topDocs = indexSearcher.search(query, 10);
        //定义高亮组件(分别为高亮词语两边的标签)
        Formatter formatter = new SimpleHTMLFormatter("<span class='red'>", "</sapn>");
        //构建Scorer, 用于选取最佳切片
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);
        //构建Fragmenter, 用于文档切片
        highlighter.setTextFragmenter(new SimpleFragmenter(100));
        highlighter.setEncoder(new SimpleHTMLEncoder());

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc: scoreDocs){
            int docId = scoreDoc.doc;
            Document document = indexSearcher.doc(docId);
            String title = document.get("title");
            System.out.println("未高亮title:" + title);
            //高亮显示
            String fragmentTitle = highlighter.getBestFragment(analyzer, "title", title);
            System.out.println("高亮title:" + fragmentTitle);
        }
    }
}
