class DummyClass_179477 {
    @Test(expected = PersistenceException.class)
    public void testSafeUpdateNull() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByExample(new Country(), null);
        } finally {
            sqlSession.close();
        }
    }

}