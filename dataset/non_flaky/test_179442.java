class DummyClass_179442 {
    @Test
    public void testAvg() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            AggregateCondition aggregateCondition = AggregateCondition.builder().
                    aggregateBy("id").aggregateType(AggregateType.AVG);
            Example example = new Example(User.class);
            List<User> m = mapper.selectAggregationByExample(example, aggregateCondition);
            Assert.assertEquals(1, m.size());
            Assert.assertEquals(new Long(3), m.get(0).getId());
        } finally {
            sqlSession.close();
        }
    }

}