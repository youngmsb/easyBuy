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
 * Lucene内置query测试
 */
public class LuceneQueryTest {
    @Test
    public void testTermQuery() throws Exception{
        query(new TermQuery(new Term("title", "苹果")));
    }

    /**
     * 范围搜索
     * @throws Exception
     */
    @Test
    public void testNumericRangeQuery() throws Exception{
        //参数:字段,最小值,最大值,是否包含最小值,是否包含最大值
        Query query = NumericRangeQuery.newLongRange("id", 10L, 13L, true, true);
        query(query);
    }

    @Test
    public void testMatchAllDocsQuery() throws Exception{
        Query query = new MatchAllDocsQuery();
        query(query);
    }

    /**
     * 测试模糊搜索, 字母使用小写搜索
     * @throws Exception
     */
    @Test
    public void testWildcardQuery() throws Exception{
        Query query = new WildcardQuery(new Term("title", "app*"));
        query(query);
    }

    /**
     * 相似度查询测试
     * @throws Exception
     */
    @Test
    public void testFuzzyQuery() throws Exception{
        Query query = new FuzzyQuery(new Term("title", "eppl6"));
        query(query);
    }

    /**
     * 组合搜索
     * @throws Exception
     */
    @Test
    public void testBooleanQuery() throws Exception{
        BooleanQuery query = new BooleanQuery();
        Query fuzzyQuery = new FuzzyQuery(new Term("title", "eppl5"));
        Query wildcardQuery = new WildcardQuery(new Term("title", "传智播*"));
        query.add(fuzzyQuery, BooleanClause.Occur.MUST_NOT);
        query.add(wildcardQuery, BooleanClause.Occur.MUST);
        query(query);
    }

    /**
     * 通用query方法
     * @param query
     */
    private void query(Query query) throws Exception {
        //创建IndexSearcher对象
        Directory directory = FSDirectory.open(new File("/Users/apple/index"));
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

        //执行搜索(搜索前10条), 返回命中数据
        TopDocs topDocs = indexSearcher.search(query, 10);
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
