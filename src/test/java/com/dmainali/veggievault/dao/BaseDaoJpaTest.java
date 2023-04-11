package com.dmainali.veggievault.dao;

import jakarta.persistence.EntityManager;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(EasyMockExtension.class)
public class BaseDaoJpaTest {

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindAll() {
    }
}
