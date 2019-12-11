package edu.ustcwugroup.database.service;

import edu.ustcwugroup.database.dao.SearchDAO;
import edu.ustcwugroup.database.model.Molecule;
import edu.ustcwugroup.database.model.ViewObject;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Haozk on 2019/12/5
 */
@Service
public class SearchService {
    @Autowired
    SearchDAO searchDAO;

    public List<Molecule> selectTest(List<Integer> culeIds,int count){
        return searchDAO.selectCules(culeIds,count);
    }

    private static final String SOLR_UPL="http://47.101.159.122:8983/solr/group";
    private HttpSolrClient client =new HttpSolrClient.Builder(SOLR_UPL).build();
    private static final String Formula = "formula";
    private static final String Elements = "elements";

    public ViewObject searchMolecules(String keyword, int offset, int count, String hlPre, String hlPos) throws Exception{
        ViewObject vo =new ViewObject();
        List<Molecule> culeList = new ArrayList<>();
        SolrQuery query = new SolrQuery(keyword);
        query.setRows(count);
        query.setStart(offset);
        query.setHighlight(true);
        query.setHighlightSimplePre(hlPre);
        query.setHighlightSimplePost(hlPos);
        query.set("hl.fl", Elements + "," + Formula);
        QueryResponse response = client.query(query);
        vo.set("count",response.getResults().getNumFound());
        for (Map.Entry<String, Map<String, List<String>>> entry : response.getHighlighting().entrySet()) {
            Molecule cule = new Molecule();
            cule.setId(Integer.parseInt(entry.getKey()));
            cule = searchDAO.selectMolecule(cule.getId());
            if (entry.getValue().containsKey(Elements)) {
                List<String> eleList = entry.getValue().get(Elements);
                if (eleList.size() > 0) {
                    cule.setElements(eleList.get(0));
                }
            }
            if (entry.getValue().containsKey(Formula)) {
                List<String> formList = entry.getValue().get(Formula);
                if (formList.size() > 0) {
                    cule.setFormula(formList.get(0));
                }
            }
            culeList.add(cule);
        }
        vo.set("cules",culeList);
        return vo;
    }


    public boolean indexQuestion(int qid, String formula, String elements) throws Exception {
        SolrInputDocument doc =  new SolrInputDocument();
        doc.setField("id", qid);
        doc.setField(Formula, formula);
        doc.setField(Elements, elements);
        UpdateResponse response = client.add(doc, 1000);
        return response != null && response.getStatus() == 0;
    }
}

