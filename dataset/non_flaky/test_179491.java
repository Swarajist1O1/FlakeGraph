class DummyClass_179491 {
    @Test(expected = PersistenceException.class)
    public void testSafeDeleteNull() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.delete(null);
        } finally {
            sqlSession.close();
        }
    }

}