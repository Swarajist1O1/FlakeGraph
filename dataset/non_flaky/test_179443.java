class DummyClass_179443 {
    @Test
    public void testSum() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            AggregateCondition aggregateCondition = AggregateCondition.builder().
                    aggregateBy("id").aliasName("aggregation").aggregateType(AggregateType.SUM);
            Example example = new Example(User.class);
            List<User> m = mapper.selectAggregationByExample(example, aggregateCondition);
            Assert.assertEquals(1, m.size());
            Assert.assertEquals(new Long(21), m.get(0).getAggregation());
        } finally {
            sqlSession.close();
        }
    }

}