class DummyClass_179478 {
    @Test(expected = PersistenceException.class)
    public void testSafeUpdateNull2() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByExample(null, null);
        } finally {
            sqlSession.close();
        }
    }

}