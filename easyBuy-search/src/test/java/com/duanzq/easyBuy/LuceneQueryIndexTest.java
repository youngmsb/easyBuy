package com.duanzq.easyBuy;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

/**
 * Created by duanzq on 16/7/3.
 * 查询索引数据
 */
public class LuceneQueryIndexTest {
    @Test
    public void testQueryIndex() throws Exception{
        //创建IndexSearcher对象
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //创建查询对象(Term(字段名, 字段值))
        Query termQuery = new TermQuery(new Term("title", "对对"));
        //执行搜索(搜索前10条), 返回命中数据
        TopDocs topDocs = indexSearcher.search(termQuery, 10);
        System.out.println("共命中" + topDocs.totalHits + "条数据");
        //遍历展示
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc: scoreDocs) {
            System.out.println("得分:" + scoreDoc.score);
            //获取文档id
            int doc = scoreDoc.doc;
            //根据文档id查询文档结果
            Document document = indexSearcher.doc(doc);
            //从document中获取数据
            System.out.println("id:" + document.get("id"));
            System.out.println("image:" + document.get("image"));
            System.out.println("price:" + document.get("price"));
            System.out.println("status:" + document.get("status"));
            System.out.println("title:" + document.get("title"));
        }
    }
}
