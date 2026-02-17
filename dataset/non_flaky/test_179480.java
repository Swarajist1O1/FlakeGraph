class DummyClass_179480 {
    @Test(expected = PersistenceException.class)
    public void testSafeUpdateByExampleNull() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByExampleSelective(new Country(), null);
        } finally {
            sqlSession.close();
        }
    }

}