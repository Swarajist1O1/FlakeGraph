class DummyClass_88815 {
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        ClientCacheConfiguration target = new ClientCacheConfiguration().setName("Person")
            .setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL)
            .setBackups(3)
            .setCacheMode(CacheMode.PARTITIONED)
            .setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC)
            .setEagerTtl(false)
            .setGroupName("FunctionalTest")
            .setDefaultLockTimeout(12345)
            .setPartitionLossPolicy(PartitionLossPolicy.READ_WRITE_ALL)
            .setReadFromBackup(true)
            .setRebalanceBatchSize(67890)
            .setRebalanceBatchesPrefetchCount(102938)
            .setRebalanceDelay(54321)
            .setRebalanceMode(CacheRebalanceMode.SYNC)
            .setRebalanceOrder(2)
            .setRebalanceThrottle(564738)
            .setRebalanceTimeout(142536)
            .setKeyConfiguration(new CacheKeyConfiguration("Employee", "orgId"))
            .setQueryEntities(new QueryEntity(int.class.getName(), "Employee")
                .setTableName("EMPLOYEE")
                .setFields(
                    Stream.of(
                        new SimpleEntry<>("id", Integer.class.getName()),
                        new SimpleEntry<>("orgId", Integer.class.getName())
                    ).collect(Collectors.toMap(
                        SimpleEntry::getKey, SimpleEntry::getValue, (a, b) -> a, LinkedHashMap::new
                    ))
                )
                .setKeyFields(Collections.singleton("id"))
                .setNotNullFields(Collections.singleton("id"))
                .setDefaultFieldValues(Collections.singletonMap("id", 0))
                .setIndexes(Collections.singletonList(new QueryIndex("id", true, "IDX_EMPLOYEE_ID")))
                .setAliases(Stream.of("id", "orgId").collect(Collectors.toMap(f -> f, String::toUpperCase)))
            );

        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

        ObjectOutput out = new ObjectOutputStream(outBytes);

        out.writeObject(target);
        out.flush();

        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(outBytes.toByteArray()));

        Object desTarget = in.readObject();

        assertTrue(Comparers.equal(target, desTarget));
    }

}