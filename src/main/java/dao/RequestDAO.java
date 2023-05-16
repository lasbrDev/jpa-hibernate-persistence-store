package dao;

import model.Request;
import vo.SalesReportVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

    public class RequestDAO {
        private EntityManager em;

        public RequestDAO(EntityManager em) {
            this.em = em;
        }

        public void register(Request request) {
            this.em.persist(request);
        }

        public BigDecimal totalValueSold() {
            String jpql = "SELECT SUM(p.amount) FROM Request p";
            return em.createQuery(jpql, BigDecimal.class).getSingleResult();
        }

        public List<SalesReportVo> salesReport() {
            String jpql = "SELECT new vo.SalesReportVo(product.name, SUM(item.quantity), MAX(request.date)) " +
                    "FROM Request request JOIN request.items item JOIN item.product product " +
                    "GROUP BY product.name ORDER BY item.quantity DESC";
            return em.createQuery(jpql, SalesReportVo.class).getResultList();
        }
    }