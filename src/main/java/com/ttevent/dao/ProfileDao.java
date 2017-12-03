/*
 * © 2016 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package com.ttevent.dao;

import com.ttevent.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huseyin.kilic
 */
@Repository("profile")
public class ProfileDao extends BaseDao<UserEntity> {

  public UserEntity findByTwitterId(long twitterId) {
    Criteria criteria = ((Session) em.getDelegate()).createCriteria(UserEntity.class);
    return (UserEntity) criteria.add(Restrictions.eq("twitterId", twitterId))
            .uniqueResult();
  }

  public List<UserEntity> getActiveUsers() {
    Criteria criteria = ((Session) em.getDelegate()).createCriteria(UserEntity.class);
    return criteria.add(Restrictions.eq("receiveNotifications", true)).list();
  }

}
