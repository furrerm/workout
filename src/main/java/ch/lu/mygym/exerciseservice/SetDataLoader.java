package ch.lu.mygym.exerciseservice;


public class SetDataLoader {
/*
    public List<SetsEntity> findById (int modelId, Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SetsEntity> mainQuery = criteriaBuilder.createQuery(SetsEntity.class);
        Root<SetsEntity> model1 = mainQuery.from(SetsEntity.class);

        CriteriaQuery<SetsEntity> subquery1 = criteriaBuilder.createQuery(SetsEntity.class);
        Root<SetsEntity> model2 = subquery1.from(SetsEntity.class);

        Subquery<LocalDate> maxSubQuery = subquery1.subquery(LocalDate.class);
        Root<SetsEntity> fromEntityX = maxSubQuery.from(SetsEntity.class);

        maxSubQuery.select(criteriaBuilder.greatest(fromEntityX.<LocalDate>get("time"))).where(criteriaBuilder.equal(fromEntityX.get("exercise_id"), modelId));
        //maxSubQuery.select(criteriaBuilder.greatest(fromEntityX.<LocalDate>get("time")));
        subquery1.where(criteriaBuilder.equal(model2.get("time"), maxSubQuery));
        TypedQuery<SetsEntity> maxTime = session.createQuery(subquery1);


        Predicate[] predicates = new Predicate[2];
        predicates[0] = criteriaBuilder.equal(model1.get("exercise_id"), modelId);
        try {
            predicates[1] = criteriaBuilder.equal(model1.get("time"), maxTime.getResultList().get(0).getTime());
        } catch (Exception e){
            System.out.println(e);
        }

        mainQuery.select(model1).where(predicates);

        List<SetsEntity> setsEntities;
        try {
            TypedQuery<SetsEntity> q = session.createQuery(mainQuery);
            setsEntities = q.getResultList();
        } catch (Exception e){
            setsEntities = new ArrayList<>();
        }
        return setsEntities;
    }

 */
}
