class DummyClass_179471 {
    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            TimeModel3Mapper mapper = sqlSession.getMapper(TimeModel3Mapper.class);
            TimeModel3 timeModel = new TimeModel3();
            timeModel.setId(3);
            Date now = new Date();
            timeModel.setTestDate(now);
            timeModel.setTestTime(now);
            timeModel.setTestDatetime(now);
            /*
                insert æ¥å¿è½ææ¾çå°å¶å® jdbcType åçåºå«

                DEBUG [main] - ==>  Preparing: INSERT INTO test_timestamp ( id,test_date,test_time,test_datetime ) VALUES( ?,?,?,? )
                DEBUG [main] - ==> Parameters: 3(Integer), 2018-02-25(Date), 11:50:18(Time), 2018-02-25 11:50:18.263(Timestamp)
             */
            Assert.assertEquals(1, mapper.insert(timeModel));

            timeModel = mapper.selectByPrimaryKey(3);

            //ä¿å­åæ°æ®åºä¸­ä¸å­å¨æ¶é´é¨å
            Assert.assertEquals(toDate(now), toDate(timeModel.getTestDate()));
            Assert.assertEquals(toDate(now) + " 00:00:00", toDatetime(timeModel.getTestDate()));

            //æ¶é´
            Assert.assertEquals(toTime(now), toTime(timeModel.getTestTime()));
            //ç±äºæå¥æ°æ®åºæ¶æå®ç jdbcType=TIMEï¼æä»¥ä¸é¢æ¯æ²¡ææ¥æé¨åç
            Assert.assertEquals("1970-01-01 " + toTime(now), toDatetime(timeModel.getTestTime()));

            Assert.assertEquals(toDatetime(now), toDatetime(timeModel.getTestDatetime()));
        } finally {
            sqlSession.close();
        }
    }

}