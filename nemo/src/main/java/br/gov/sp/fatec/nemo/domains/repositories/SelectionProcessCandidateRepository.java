package br.gov.sp.fatec.nemo.domains.repositories;

import br.gov.sp.fatec.nemo.domains.entities.SelectionProcessCandidate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class SelectionProcessCandidateRepository {

    @PersistenceContext
    EntityManager entityManager;

    public static <T> List<T> map(Class<T> type, List<Object[]> records) {
        List<T> result = new LinkedList<>();
        for (Object[] record : records) {
            result.add(map(type, record));
        }
        return result;
    }

    public static <T> List<T> getResultList(Query query, Class<T> type) {
        @SuppressWarnings("unchecked")
        List<Object[]> records = query.getResultList();
        return map(type, records);
    }

    public static <T> T map(Class<T> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        int i = 0;
        Field[] fields = SelectionProcessCandidate.class.getFields();
        for (Object field : tuple) {
            tupleTypes.add(fields[i].getType());
            i++;
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SelectionProcessCandidate> findCandidateInJobOpportunity(Long id) {
        Query q = entityManager.createNativeQuery("select can_id, can_name, email, cpf, phone, gender, birthday, country, availability, city, neighborhood, street, home_number, complement, zip_code, latitude, longitude, desired_journey, work_modality, pretension_salary, fk_status_name, obs from selection_process s " +
                "join candidate c on s.fk_can_id = c.can_id " +
                "where fk_jo_id = :jobId").setParameter("jobId", id);
        return getResultList(q, SelectionProcessCandidate.class);
    }
}
